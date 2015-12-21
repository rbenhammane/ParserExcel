package ma.ericsson.service.gen;

import static ma.ericsson.granite.cli.util.ParserConstants.SERVICE_PACKAGE;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import ma.ericsson.granite.cli.model.Button;
import ma.ericsson.granite.cli.model.Field;
import ma.ericsson.granite.cli.model.Form;
import ma.ericsson.granite.cli.util.ParserConstants;
import ma.ericsson.utils.Utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.MethodSource;

public class SRMSServiceGenerator {

	protected static final Log log = LogFactory.getLog(SRMSServiceGenerator.class);

	public static void main(String[] args) {
		// createClassService("srms.acquisition.forms.apd ", "ValidateApdRadioFormService");
	}

	public static void createClassService(Form form) {
		List<Field> listField = form.getFieldList();
		for (Field field : listField) {
			field.getGroup();
			field.getAttributeKey();
			field.getAttributeValue();
		}

		String className = form.getName() + "FormService";

		final JavaClassSource javaClass = Roaster.create(JavaClassSource.class);
		javaClass.setPackage(SERVICE_PACKAGE).setName(className);

		// javaClass.setSuperType("srms.services.GraniteFormService");
		javaClass.setSuperType("srms.acquisition.forms.inwi.CommonGraniteFormService");
		javaClass.addImport("org.slf4j.LoggerFactory");
		javaClass.addImport("it.pride.eat.core.EntityManagerUtils");
		javaClass.addImport("srms.util.UtilsGraniteService");
		javaClass.addImport("srms.exception.SiteNotExsitsException");
		javaClass.addImport("it.pride.eat.authentication.AuthenticatedUser");
		javaClass.addImport(Map.class);
		
		javaClass.addImport(java.lang.reflect.Method.class);
		javaClass.addImport("srms.util.SRMSConstants.*").setStatic(true);

		/*************/
		/*************/

		String methodDetMandatoryFieldsBody = "Map<String, String> fields = new HashMap<>();";
		for (Field field : form.getFieldList()) {
			if (field.isMandatory() && !field.isReadOnly()) {
				methodDetMandatoryFieldsBody += "fields.put(\"" + field.getName() + "\", \"" + field.getDescription() + " est vide\");";
			}
		}
		methodDetMandatoryFieldsBody += "return fields;";

		MethodSource methodGetMandatoryFields = javaClass.addMethod() //
				.setProtected() //
				.setName("getMandatoryFields") //
				.setBody(methodDetMandatoryFieldsBody);
		methodGetMandatoryFields.addAnnotation(Override.class);
		methodGetMandatoryFields.setReturnType("Map<String, String>");

		/*************/
		/*************/

		MethodSource methodDoAction = javaClass.addMethod() //
				.setPublic() //
				.setName("doAction") //
				.setBody(String.format("UtilsGraniteService.exec(this, command, request, response);"));
		methodDoAction.addParameter(String.class, "command");
		methodDoAction.addParameter("javax.servlet.http.HttpServletRequest", "request");
		methodDoAction.addParameter("javax.servlet.http.HttpServletResponse", "response");
		methodDoAction.addAnnotation(Override.class);

		/*************/
		/*************/

		MethodSource methodAfterLoad = javaClass.addMethod() //
				.setPublic() //
				.setName("afterLoad") //
				.setBody(String.format("", className, className));
		methodAfterLoad.addParameter("javax.servlet.http.HttpServletRequest", "request");
		methodAfterLoad.addParameter("java.util.HashMap<String, Object>", "map");
		methodAfterLoad.addAnnotation(Override.class);

		/********************/
		/***** LOGGER *******/
		/********************/
		javaClass.addField()//
				.setName("log")//
				.setType("org.slf4j.Logger")//
				.setPrivate()//
				.setStatic(true)//
				.setFinal(true).setLiteralInitializer("LoggerFactory.getLogger(" + className + ".class.getName())");

		/********************/
		/***** OPERATION ****/
		/********************/
		String body = "";
		// List<GUIAttribute> attrs = gui.getAttributes();

		body += "try{";

		body += "startGraniteService();";
		body += "initializeServices();";
		body += "startTransaction();";
		body += "AuthenticatedUser user = AuthenticatedUser.getUser(request.getSession());";

		if (form.hasAttachment()) {
			body += "DiskFileUpload upload = new DiskFileUpload();";
			body += "List itemsFields = upload.parseRequest(request);";
		}

		body += "if (!checkMandatoryFields(request, response, " + (form.hasAttachment() ? "itemsFields" : "null") + ")){return;}";

		body += "Object jpa = loadValueForm(request, " + (form.hasAttachment() ? "itemsFields" : "null") + ");";

		body += form.getModel() + " jpabean = (" + form.getModel() + ") jpa;";

		body += "fetchSites(request, jpabean.getSiteInstId(), jpabean.getNomSiteInstId(), jpabean.getNgoSrmsInstId());";

		String siteType;
		for (Field field : form.getFieldList()) {

			if (field.getGroup() != null && !field.getGroup().equals("") && !field.getGroup().equals("-") && !field.getGroup().equals("ATTACHEMENT") && !field.isReadOnly()) {
				switch (field.getGroup()) {

					case "CANDIDATE-INFO":
						javaClass.addImport("srms.util.GraniteAttributes.CandidateInfo.*").setStatic(true);
						siteType = "siteCand";
						break;

					case "CANDIDATE-SITE-DATES":
						javaClass.addImport("srms.util.GraniteAttributes.CandidateSiteDates.*").setStatic(true);
						siteType = "siteCand";
						break;

					case "ROLLOUT-DATA":
						javaClass.addImport("srms.util.GraniteAttributes.RolloutData.*").setStatic(true);
						siteType = "siteNgo";
						break;

					case "SRMS-DATA":
						javaClass.addImport("srms.util.GraniteAttributes.SrmsData.*").setStatic(true);
						siteType = "siteNgo";
						break;

					case "SITE-INFO":
						javaClass.addImport("srms.util.GraniteAttributes.SiteInfo.*").setStatic(true);
						siteType = "siteNom";
						break;

					default:
						siteType = "siteNom";
				}

				body += siteType + ".getDynamicAttributes().setObjectAttributeValue(createDA(" + field.getGroup().replaceAll("-", "_")
						+ ", " + field.getAttributeKey() + ", jpabean.get" + Utils.upperCaseFirst(field.getName()) + "()));";
			}
		}

		// for (GUIAttribute attr : attrs) {
		// body += "String " + attr.getAttributeName() + " = request.getParameter(\"" + attr.getAttributeName() + "\");";
		// }
		// for (GUIAttribute attr : attrs) {
		// if (attr.getMapping().contains("/") && attr.getMapping().contains(".")) {
		// String GROUP = Utils.clean(attr.getMapping().split("/")[1].split("\\.")[0], "_").toUpperCase();
		// // String ATTR_NAME = Utils.clean(attr.getMapping().replaceFirst("[^.]*\\.", ""), "_").toUpperCase();
		// String ATTR_NAME = attr.getMapping().replaceFirst("[^.]*\\.", "");
		// ATTR_NAME = Utils.cleanSpecialChar(ATTR_NAME).toUpperCase();
		//
		// if (GROUP.equals("SRMSDATA")) {
		// GROUP = "SRMS_DATA";
		// }
		//
		// GROUP = "GROUP_NAME_" + GROUP;
		//
		// body += "srms_cand.getDynamicAttributes().setObjectAttributeValue(getDA(" + GROUP + ", " + ATTR_NAME + ", " + attr.getAttributeName() + "));";
		// }
		// }

		// TODO sendResponseError(response, msg_err);

		body += "commitTransaction();";
		body += "committAllTransaction(graniteEM);";
		body += "jsonRecord.setSuccess(true);";
		if (form.hasAttachment()) {
			body += "sendResponseUpload(response, false, null);";
		} else {
			body += "sendResponse(jsonRecord, response);";
		}

		body += "}catch(SiteNotExsitsException sne){sendResponseError(response, sne.getMessage());}";
		body += "catch(Exception e){exception(response,e,log);}finally{stopServiceGranite();}";

		/********************/
		/***** OPERATION ****/
		/********************/
		List<Button> buttons = form.getButtonList();
		for (Button button : buttons) {
			if (button.getOperation() != null) {
				String opName = button.getOperation();

				// body = "int a=2;";
				MethodSource<JavaClassSource> methodAction = javaClass.addMethod() //
						.setPublic() //
						.setName(opName); //
				methodAction.setBody(body);//
				methodAction.addParameter("javax.servlet.http.HttpServletRequest", "request");//
				methodAction.addParameter("javax.servlet.http.HttpServletResponse", "response");
				methodAction.addThrows(IOException.class);

				MethodSource<JavaClassSource> method = Roaster.create(JavaClassSource.class).addMethod("public void test(){}");
				// Assert.assertFalse(method.isNative());
				method.setNative(true);
				// Assert.assertTrue(method.isNative());
				// Assert.assertThat(method.toString(), containsString("public native void test();"));
				// Assert.assertNull(method.getBody());

				method.setNative(false).setBody(body);
				// Assert.assertFalse(method.isNative());
				// Assert.assertEquals(body, method.getBody());
				System.out.println("");
			}

		}

		// FieldSource<JavaClassSource> loggerProperty = javaClass.addField("log")//
		// .setType("org.slf4j.Logger")//
		// .setStatic(true);
		// loggerProperty.setStringInitializer("LoggerFactory.getLogger(" + classeName + ".class.getName())");

		/********************/
		/** PRINT CODE SRC **/
		/********************/
		// System.out.println(javaClass);
		Utils.setFileContent(new File(ParserConstants.SOURCE_PATH_SERVICE + "/" + className + ".java"), javaClass.toString());
		System.out.println(className + ".java generated !");
	}

}
