package ma.ericsson.service.gen;

import java.io.File;

import ma.ericsson.granite.cli.model.GUI;
import ma.ericsson.utils.Utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.MethodSource;

public class SRMSServiceGenerator {

	protected static final Log log = LogFactory.getLog(SRMSServiceGenerator.class);

	private final static String SOURCE_PATH = "../SRMS/src-gen-service/srms/acquisition/forms/";

	private static void createClassService(String pkgName, String className) {

		final JavaClassSource javaClass = Roaster.create(JavaClassSource.class);
		javaClass.setPackage(pkgName).setName(className);

		javaClass.setSuperType("srms.services.GraniteFormService");
		javaClass.addImport("org.slf4j.LoggerFactory");

		/*************/
		/*************/

		MethodSource methodDoAction = javaClass.addMethod() //
				.setPublic() //
				.setName("doAction") //
				.setBody(String.format("", className, className));
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
		// PropertySource<JavaClassSource> loggerProperty =
		// javaClass.addProperty("org.slf4j.Logger","log");
		// loggerProperty.setStringInitializer("LoggerFactory.getLogger(" + classeName + ".class.getName())");

		// javaClass.addInterface(Serializable.class);
		javaClass.addField()//
				.setName("log")//
				.setType("org.slf4j.Logger")//
				.setPrivate()//
				.setStatic(true)//
				.setFinal(true).setLiteralInitializer("LoggerFactory.getLogger(" + className + ".class.getName())");

		// FieldSource<JavaClassSource> loggerProperty = javaClass.addField("log")//
		// .setType("org.slf4j.Logger")//
		// .setStatic(true);
		// loggerProperty.setStringInitializer("LoggerFactory.getLogger(" + classeName + ".class.getName())");

		/********************/
		/** PRINT CODE SRC **/
		/********************/
		// System.out.println(javaClass);
		Utils.setFileContent(new File(SOURCE_PATH + "/" + className + ".java"), javaClass.toString());
		System.out.println(className + ".java generated !");

	}

	public static void main(String[] args) {
		createClassService("srms.acquisition.forms.apd ", "ValidateApdRadioFormService");

	}

	public static void createClassService(GUI gui) {
		createClassService("srms.acquisition.forms", gui.getFormManagerClass() + "FormService");
	}

}
