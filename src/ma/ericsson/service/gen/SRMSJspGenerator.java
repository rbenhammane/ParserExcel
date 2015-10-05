package ma.ericsson.service.gen;

import java.io.File;
import java.io.IOException;

import ma.ericsson.granite.cli.model.GUI;
import ma.ericsson.utils.Utils;

public class SRMSJspGenerator {

	private static String FILTER_HEIGHT = "300";

	private final static String MODEL_JSP_PATH = "template/";
	private final static String MODEL_JSP = MODEL_JSP_PATH + "grid_with_filter_template.jsp";

	public static void createJSP(GUI gui) throws IOException {
		String jspFileName = gui.getName() + ".jsp";

		String jspContent = Utils.getFileContent(MODEL_JSP);
		jspContent = jspContent.replace("{**DATAGRID-NAME**}", gui.getName());
		jspContent = jspContent.replace("{**FILTERFORM-NAME**}", gui.getName());
		jspContent = jspContent.replace("{**TITLE**}", gui.getName());
		jspContent = jspContent.replace("{**FILTER_HEIGHT**}", FILTER_HEIGHT);
		jspContent = jspContent.replace("datagridId", gui.getName());
		Utils.setFileContent(new File(MODEL_JSP_PATH + "\\jsp-gen\\" + jspFileName), jspContent);

	}

	public static void main(String[] args) throws IOException {
		GUI gui = new GUI();
		gui.setName("APDValidationRadioGrid");
		createJSP(gui);
	}

}
