package ma.ericsson.service.gen;

import java.io.File;
import java.util.List;

import ma.ericsson.granite.cli.model.GUI;
import ma.ericsson.granite.cli.model.GUIAttribute;
import ma.ericsson.granite.cli.util.ParserConstants;
import ma.ericsson.utils.Utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;

public class SRMSConstantsGenerator {

	protected static final Log log = LogFactory.getLog(SRMSConstantsGenerator.class);

	private static final JavaClassSource javaClass = Roaster.create(JavaClassSource.class);
	private static String className = "SRMSConstants";
	{
		javaClass.setPackage("srms.util").setName(className);
	}

	public static void createConstants(GUI gui) {

		javaClass.addField() //
				.setName("___________________" + gui.getFormName() + "___________________") //
				.setType("String") //
				.setPublic() //
				.setStatic(true) //
				.setFinal(true).setStringInitializer("___________________");

		List<GUIAttribute> attributes = gui.getAttributes();
		for (GUIAttribute attr : attributes) {
			// String GROUP = Utils.clean(attr.getMapping().split("/")[1].split("\\.")[0], "_").toUpperCase();
			// String key = Utils.clean(attr.getMapping().split("/")[1].split("\\.")[0], "_").toUpperCase();
			if (attr.getMapping().contains("/") && attr.getMapping().contains(".")) {
				String key = attr.getMapping().replaceFirst("[^.]*\\.", "");
				key = Utils.cleanSpecialChar(key).toUpperCase();
				String val = attr.getMapping().replaceFirst("[^.]*\\.", "").replace("  ", " ").trim();
				if (!javaClass.hasField(key)) {
					javaClass.addField() //
							.setName(key) //
							.setType("String") //
							.setPublic() //
							.setStatic(true) //
							.setFinal(true).setStringInitializer(val);
				}
			}
		}

	}

	public static void close() {
		Utils.setFileContent(new File(ParserConstants.SOURCE_PATH_SERVICE + "/" + className + ".java"), javaClass.toString());
		System.out.println(className + ".java generated !");
	}

	public static void main(String[] args) {
		// createClassService("srms.acquisition.forms.apd ",
		// "ValidateApdRadioFormService");

		String key = "Dynamic attributes/SRMSData.Resp. Acquisition DJ Name";
		String val = key.replaceFirst("[^.]*\\.", "");
		System.out.println(val);
		System.out.println(Utils.cleanSpecialChar(val).toUpperCase());

	}

}
