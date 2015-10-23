package srms.acquisition.forms.inwi;

import it.pride.eat.core.EntityManagerUtils;
import it.pride.eat.jpa.MForm;
import it.pride.eat.json.JsonRecord;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.acquisition.DocsSrms;

import org.slf4j.Logger;

import srms.exception.SiteNotExsitsException;
import srms.granite.UtilGraniteOperation;
import srms.services.GraniteFormService;
import srms.util.SRMSConstants;
import srms.util.UtilSrmsAttachment;
import srms.util.UtilsString;

import com.ericsson.granite.oss.core.framework.dto.Attachment;
import com.ericsson.granite.oss.core.inventory.dto.Site;
import com.ericsson.granite.oss.core.inventory.key.SiteKey;
import com.ericsson.granite.oss.core.inventory.service.SiteService;
import com.ericsson.granite.oss.srms.dto.SiteRolloutData;
import com.telcordia.granite.sdk.exceptions.GraniteException;
import com.telcordia.granite.sdk.object.dto.DynamicAttribute;
import com.telcordia.granite.sdk.object.dto.ManagedObjectFetchSpec;

public abstract class CommonGraniteFormService extends GraniteFormService {

	// String msg_err = "";
	JsonRecord jsonRecord = new JsonRecord();
	UtilGraniteOperation ugo = null;
	protected SiteRolloutData siteNgo = null;
	// protected EntityManager em = null;
	protected DynamicAttribute attr = null;
	protected SiteService siteService = null;

	Site siteCand = null;
	Site siteNom = null;

	public CommonGraniteFormService() {

	}

	public void initializeServices() throws Exception {
		ugo = new UtilGraniteOperation(this.graniteServiceFactory);
		siteService = graniteServiceFactory.getService(SiteService.class);
	}

	public Object loadValueForm(HttpServletRequest request, HttpServletResponse response, boolean hasAttachment) throws Exception {
		EntityManager em = null;
		try {

			em = EntityManagerUtils.getEntityManagerFactory("EAT").createEntityManager();

			String formId = request.getParameter("formid");

			HashMap<String, Object> map = new HashMap<String, Object>();
			jsonRecord.setData(map);

			MForm form = (MForm) em.find(MForm.class, formId);

			Object jpa = Class.forName(form.getClassName()).newInstance();

			if(hasAttachment){
			loadValueForm(request, form, jpa, map, true, true);
			}else{
				
			}

			return jpa;

		} finally {
			if (em != null) {
				EntityManagerUtils.releaseConnection(em);
			}
		}

	}

	public void fetchSites(HttpServletRequest request, HttpServletResponse response/* , Object jpa */) throws Exception {

		Long siteInstId = Long.parseLong(request.getParameter("siteInstId"));
		Long nomSiteInstId = Long.parseLong(request.getParameter("nomSiteInstId"));
		Long ngoSrmsInstId = Long.parseLong(request.getParameter("ngoSrmsInstId"));

		SiteKey keyCand = new SiteKey(siteInstId);
		SiteKey keyNom = new SiteKey(nomSiteInstId);

		siteCand = siteService.fetchByKey(keyCand, new ManagedObjectFetchSpec());
		if (siteCand == null) {
			throw new SiteNotExsitsException("There is no site Candidate with key : " + siteInstId);
		}

		siteNom = siteService.fetchByKey(keyNom, new ManagedObjectFetchSpec());
		if (siteNom == null) {
			throw new SiteNotExsitsException("There is no site Nominal with key : " + nomSiteInstId);
		}

		siteNgo = new SiteRolloutData();
		siteNgo.setObject_inst_id(ngoSrmsInstId);
		siteNgo = ugo.manageSiteRolloutData(siteNgo, "GET");

		if (siteNgo == null) {
			throw new SiteNotExsitsException("The NGO associated  to the site Candidate with key : " + siteCand.getSiteKey().getKeyValue() + " not  exist");
		}

	}

	public abstract HashMap<String, String> getMandatoryFieldsMap();

	public boolean checkMandatoryFields(HttpServletRequest request, HttpServletResponse response) throws IOException {

		Map<String, String> mapMandatoryFieldMsg = getMandatoryFieldsMap();

		for (String fieldName : mapMandatoryFieldMsg.keySet()) {
			String reject_reason = request.getParameter(fieldName);
			if (UtilsString.isEmpty(reject_reason)) {
				sendResponseError(response, mapMandatoryFieldMsg.get(fieldName));
				return false;
			}
		}

		return true;

	}

	public DynamicAttribute createDA(String grpName, String attrName, String val) {
		DynamicAttribute attr = new DynamicAttribute();
		attr.setGroupName(grpName);
		attr.setAttributeName(attrName);
		attr.setAttributeValue(val);
		return attr;
	}

	public void updateAndFetch(HttpServletResponse response/*, CommonSiteModel jpabean*/) throws GraniteException, Exception {

		siteCand = siteService.updateAndFetch(siteCand, new ManagedObjectFetchSpec());
		// siteNgo.setProjectInstId(jpabean.getNgoSrmsInstId());
		ugo.manageSiteRolloutData(siteNgo, "UPD");

		commitTransaction();
		jsonRecord.setSuccess(true);
		sendResponse(jsonRecord, response);
	}

	public void exception(HttpServletResponse response, Exception e, Logger log) throws IOException {
		e.printStackTrace();
		log.error("", e);
		rollbackTransaction();
		sendResponseException(response, e);
	}

	public void downloadGeneric(HttpServletRequest request, HttpServletResponse response, Logger log) throws Exception {
		String formId = request.getParameter("formid");

		EntityManager em = null;
		JsonRecord jsonRecord = new JsonRecord();

		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			jsonRecord.setData(map);

			em = EntityManagerUtils.getEntityManagerFactory("EAT").createEntityManager();

			MForm form = (MForm) em.find(MForm.class, formId);

			DocsSrms jpabean = new DocsSrms();

			request.getSession().removeAttribute(SRMSConstants.BEAN_SESSION_DOC_DOWNLOAD);

			String srms_inst_id = (String) request.getParameter("ngoSrmsInstId");

			if (UtilsString.isEmpty(srms_inst_id)) {
				String msg_err = "The Key Srms siteInst id is empty";
				sendResponseError(response, msg_err);
				return;

			}
			Attachment att = UtilSrmsAttachment.getSiteAttachment(Long.parseLong(srms_inst_id), SRMSConstants.KEY_ATTR_ATTACH_DOC_TSS, true, true);

			if (att == null || att.getData() == null || att.getData().getData() == null) {
				String msg_err = "The Attachment Doc Tss associated  to site Candidate is Empty";
				sendResponseError(response, msg_err);
				return;
			}

			jpabean.setBinaryFile(att.getData().getData());
			jpabean.setBinaryNameFile(att.getFileName());
			jpabean.setContentTypeFile(SRMSConstants.SARF_CONTENT_TYPE);

			request.getSession().setAttribute(SRMSConstants.BEAN_SESSION_DOC_DOWNLOAD, jpabean);

			jsonRecord.setSuccess(true);
			sendResponse(jsonRecord, response);

		} catch (Exception e) {
			e.printStackTrace();
			log.error("", e);
			sendResponseException(response, e);
		} finally {
			if (em != null) {
				EntityManagerUtils.releaseConnection(em);
			}
		}
	}

}
