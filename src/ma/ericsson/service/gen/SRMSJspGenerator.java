package ma.ericsson.service.gen;

import java.io.File;
import java.io.IOException;

import ma.ericsson.granite.cli.model.GUI;
import ma.ericsson.utils.Utils;

import org.apache.log4j.Logger;

public class SRMSJspGenerator {

	protected static final Logger log = Logger.getLogger(SRMSJspGenerator.class);
	// protected static final Log log = LogFactory.getLog(SRMSJspGenerator.class);

	private static String FILTER_HEIGHT = "300";

	private final static String MODEL_JSP_PATH = "../SRMS/WebContent/apps/srms/inwi";
	private final static String MODEL_JSP = "./grid_with_filter_template.jsp";

	public static void createJSP(GUI gui) throws IOException {
		String jspFileName = gui.getJSPName();
		
		

		String jspContent = Utils.getFileContent(MODEL_JSP);
		jspContent = jspContent.replace("{**DATAGRID-NAME**}", gui.getName());
		jspContent = jspContent.replace("{**FILTERFORM-NAME**}", gui.getFormName());
		jspContent = jspContent.replace("{**TITLE**}", gui.getName());
		jspContent = jspContent.replace("{**FILTER_HEIGHT**}", FILTER_HEIGHT);
		jspContent = jspContent.replace("datagridId", gui.getGridName());
		jspContent = jspContent.replace("formId", gui.getFormName());
		Utils.setFileContent(new File(MODEL_JSP_PATH + "/" + jspFileName), jspContent);
		System.out.println(jspFileName + " generated !");

	}

	public static void main(String[] args) throws IOException {
		GUI gui = new GUI();
		gui.setName("APDValidationRadioGrid");
		createJSP(gui);
	}

}
