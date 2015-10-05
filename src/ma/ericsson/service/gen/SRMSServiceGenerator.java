package ma.ericsson.service.gen;

import java.io.File;

import ma.ericsson.utils.Utils;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.MethodSource;

public class SRMSServiceGenerator {

	private final static String SOURCE_PATH = "template/src-gen/service";

	public static void createClassService(String pkgName, String classeName) {

		final JavaClassSource javaClass = Roaster.create(JavaClassSource.class);
		javaClass.setPackage(pkgName).setName(classeName);

		javaClass.setSuperType("srms.services.GraniteFormService");

		/*************/
		/*************/

		MethodSource methodDoAction = javaClass.addMethod() //
				.setPublic() //
				.setName("doAction") //
				.setBody(String.format("", classeName, classeName));
		methodDoAction.addParameter(String.class, "command");
		methodDoAction.addParameter("javax.servlet.http.HttpServletRequest", "request");
		methodDoAction.addParameter("javax.servlet.http.HttpServletResponse", "response");
		methodDoAction.addAnnotation(Override.class);

		/*************/
		/*************/

		MethodSource methodAfterLoad = javaClass.addMethod() //
				.setPublic() //
				.setName("afterLoad") //
				.setBody(String.format("", classeName, classeName));
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
				.setFinal(true).setStringInitializer("LoggerFactory.getLogger(" + classeName + ".class.getName())");

		// FieldSource<JavaClassSource> loggerProperty = javaClass.addField("log")//
		// .setType("org.slf4j.Logger")//
		// .setStatic(true);
		// loggerProperty.setStringInitializer("LoggerFactory.getLogger(" + classeName + ".class.getName())");

		/********************/
		/** PRINT CODE SRC **/
		/********************/
//		System.out.println(javaClass);
		Utils.setFileContent(new File(SOURCE_PATH + "/" + classeName + ".java"), javaClass.toString());

	}

	public static void main(String[] args) {
		createClassService("srms.acquisition.forms.apd", "ValidateApdRadioFormService");

	}

}
