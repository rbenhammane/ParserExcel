package ma.ericsson.service.gen;

import static ma.ericsson.granite.cli.util.ParserConstants.SERVICE_PACKAGE;

import java.io.File;
import java.io.IOException;
import java.util.List;

import ma.ericsson.granite.cli.model.GUI;
import ma.ericsson.granite.cli.model.GUIAttribute;
import ma.ericsson.granite.cli.model.GUIOperation;
import ma.ericsson.granite.cli.util.ParserConstants;
import ma.ericsson.utils.Utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.MethodSource;

public class SRMSServiceGenerator {

	protected static final Log log = LogFactory.getLog(SRMSServiceGenerator.class);

	public static void createClassService(GUI gui) {

		String className = gui.getFormManagerClass() + "FormService";

		final JavaClassSource javaClass = Roaster.create(JavaClassSource.class);
		javaClass.setPackage(SERVICE_PACKAGE).setName(className);

		// javaClass.setSuperType("srms.services.GraniteFormService");
		javaClass.setSuperType("srms.acquisition.forms.inwi.GenGraniteFormService");
		javaClass.addImport("org.slf4j.LoggerFactory");
		javaClass.addImport("model.inwi.generic.GenSiteModel");
		javaClass.addImport("it.pride.eat.core.EntityManagerUtils");
		javaClass.addImport("srms.util.UtilsGraniteService");
		javaClass.addImport("srms.util.SRMSConstants.*").setStatic(true);
		javaClass.addImport(java.lang.reflect.Method.class);

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
		List<GUIAttribute> attrs = gui.getAttributes();

		body += "try{";
		for (GUIAttribute attr : attrs) {
			if (attr.getAccess()!=null && attr.getAccess().contains("write") && !attr.getIsHidden()) {
				body += "if(checkMandatory(request, response,\"" + attr.getAttributeName() + "\", \"" + attr.getName() + " est vide\")){return;}";
			}
		}

		body += "Object jpa = loadValueForm(request, response);";

		for (GUIAttribute attr : attrs) {
			body += "String " + attr.getAttributeName() + " = request.getParameter(\"" + attr.getAttributeName() + "\");";
		}
		for (GUIAttribute attr : attrs) {
			if (attr.getMapping().contains("/") && attr.getMapping().contains(".")) {
				String GROUP = Utils.clean(attr.getMapping().split("/")[1].split("\\.")[0], "_").toUpperCase();
				// String ATTR_NAME = Utils.clean(attr.getMapping().replaceFirst("[^.]*\\.", ""), "_").toUpperCase();
				String ATTR_NAME = attr.getMapping().replaceFirst("[^.]*\\.", "");
				ATTR_NAME = Utils.cleanSpecialChar(ATTR_NAME).toUpperCase();
				
				
				if (GROUP.equals("SRMSDATA")) {
					GROUP = "SRMS_DATA";
				}

				GROUP = "GROUP_NAME_" + GROUP;

				body += "srms_cand.getDynamicAttributes().setObjectAttributeValue(getDA(" + GROUP + ", " + ATTR_NAME + ", " + attr.getAttributeName() + "));";
			}
		}

		body += "GenSiteModel jpabean = fetchSites(request, response, jpa);";

		body += "}catch(Exception e){exception(response,e,log);}finally{stopServiceGranite();if(em!=null){EntityManagerUtils.releaseConnection(em);}}";

		/********************/
		/***** OPERATION ****/
		/********************/
		List<GUIOperation> operations = gui.getOperations();
		for (GUIOperation operation : operations) {
			String opName = operation.getOperationName();

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

	public static void main(String[] args) {
		// createClassService("srms.acquisition.forms.apd ", "ValidateApdRadioFormService");
	}

}
