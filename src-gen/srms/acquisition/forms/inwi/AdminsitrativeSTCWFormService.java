package srms.acquisition.forms.inwi;

import srms.acquisition.forms.inwi.CommonGraniteFormService;
import org.slf4j.LoggerFactory;
import it.pride.eat.core.EntityManagerUtils;
import srms.util.UtilsGraniteService;
import srms.exception.SiteNotExsitsException;
import java.util.Map;
import java.lang.reflect.Method;
import static srms.util.SRMSConstants.*;
import java.lang.Override;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import org.slf4j.Logger;
import static srms.util.GraniteAttributes.CandidateInfo.*;
import static srms.util.GraniteAttributes.CandidateInfo.*;
import static srms.util.GraniteAttributes.RolloutData.*;
import static srms.util.GraniteAttributes.RolloutData.*;
import static srms.util.GraniteAttributes.SiteInfo.*;
import static srms.util.GraniteAttributes.SiteInfo.*;
import static srms.util.GraniteAttributes.SiteInfo.*;
import static srms.util.GraniteAttributes.SiteInfo.*;
import static srms.util.GraniteAttributes.SiteInfo.*;
import static srms.util.GraniteAttributes.SiteInfo.*;
import java.io.IOException;

public class AdminsitrativeSTCWFormService extends CommonGraniteFormService
{

   private static final Logger log = LoggerFactory
         .getLogger(AdminsitrativeSTCWFormService.class.getName());

   @Override
	protected Map<String, String> getMandatoryFields() {
		Map<String, String> fields = new HashMap<>();
		fields.put("attchConstructReq",
				"Charger la demande<br>construction est vide");
		fields.put("attchConstructAuth",
				"Charger l'autorisation<br>construction est vide");
		fields.put("attchConnectionReq",
				"Charger la demande<br>branchement est vide");
		fields.put("attchConnectionAuth",
				"Charger l'autorisation<br>branchement est vide");
		return fields;
	}

   @Override
   public void doAction(String command, HttpServletRequest request,
         HttpServletResponse response)
   {
      UtilsGraniteService.exec(this, command, request, response);
   }

   @Override
   public void afterLoad(HttpServletRequest request,
         HashMap<String, Object> map)
   {
   }

   public void confirm(HttpServletRequest request, HttpServletResponse response)
         throws IOException
   {
      try
      {
         initializeServices();
         startGraniteService();
         startTransaction();
         if (!checkMandatoryFields(request, response))
         {
            return;
         }
         Object jpa = loadValueForm(request, response, true);
         model.inwi.AdminsitrativeSTCW jpabean = (model.inwi.AdminsitrativeSTCW) jpa;
         fetchSites(request, response);
         siteCand.getDynamicAttributes().setObjectAttributeValue(
               createDA(CANDIDATE_INFO, CONSTRUCT_REQUEST_DATE,
                     jpabean.getConstructReqDepositDate()));
         siteCand.getDynamicAttributes().setObjectAttributeValue(
               createDA(CANDIDATE_INFO, CONSTRUCT_AUTHORISATION_DATE,
                     jpabean.getConstructReqObtentionDate()));
         siteNgo.getDynamicAttributes().setObjectAttributeValue(
               createDA(ROLLOUT_DATA, M2_10_DEMANDE_DE_BRANCHEMENT,
                     jpabean.getConnectionReqDepositDate()));
         siteNgo.getDynamicAttributes().setObjectAttributeValue(
               createDA(ROLLOUT_DATA, M2_15_AUT_BRANCHEMENT_OBTENUE,
                     jpabean.getConnectionReqObtentionDate()));
         siteNom.getDynamicAttributes().setObjectAttributeValue(
               createDA(SITE_INFO, RESPONSABLE_CW,
                     jpabean.getCwResponsible()));
         siteNom.getDynamicAttributes().setObjectAttributeValue(
               createDA(SITE_INFO, RESPONSABLE_CW_PHONE,
                     jpabean.getCwResponsiblePhone()));
         siteNom.getDynamicAttributes().setObjectAttributeValue(
               createDA(SITE_INFO, RESPONSABLE_CW_EMAIL,
                     jpabean.getCwResponsibleEmail()));
         siteNom.getDynamicAttributes().setObjectAttributeValue(
               createDA(SITE_INFO, ADMIN_ST_CW, jpabean.getAdminSTCW()));
         siteNom.getDynamicAttributes().setObjectAttributeValue(
               createDA(SITE_INFO, ADMIN_ST_CW_PHONE,
                     jpabean.getAdminSTCWPhone()));
         siteNom.getDynamicAttributes().setObjectAttributeValue(
               createDA(SITE_INFO, ADMIN_ST_CW_EMAIL,
                     jpabean.getAdminSTCWEmail()));
         commitTransaction();
         committAllTransaction(graniteEM);
         jsonRecord.setSuccess(true);
         sendResponseUpload(response, false, null);
      }
      catch (SiteNotExsitsException sne)
      {
         sendResponseError(response, sne.getMessage());
      }
      catch (Exception e)
      {
         exception(response, e, log);
      }
      finally
      {
         stopServiceGranite();
      }
   }
}
