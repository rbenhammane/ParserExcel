package ma.ericsson.service.gen;

import java.io.File;
import java.io.IOException;

import ma.ericsson.granite.cli.model.GUI;
import ma.ericsson.granite.cli.model.GUIAttribute;
import ma.ericsson.granite.cli.model.GUIOperation;
import ma.ericsson.utils.Utils;

import org.apache.log4j.Logger;

public class SRMSJspGenerator {

	protected static final Logger log = Logger.getLogger(SRMSJspGenerator.class);
	// protected static final Log log = LogFactory.getLog(SRMSJspGenerator.class);

	private static String FILTER_HEIGHT = "300";

	private final static String MODEL_JSP_PATH = "../SRMS/WebContent/apps/srms/inwi-gen";
	private final static String MODEL_JSP = "./generic.jsp";

	public static void createJSP(GUI gui) throws IOException {
		String jspFileName = gui.getJSPName();

		String jspContent = Utils.getFileContent(MODEL_JSP);
		jspContent = jspContent.replace("{**DATAGRID-NAME**}", gui.getGridName());
		jspContent = jspContent.replace("{**FILTERFORM-NAME**}", gui.getFormName());
		jspContent = jspContent.replace("{**FORM-NAME**}", gui.getFormName());

		jspContent = jspContent.replace("{**TITLE**}", gui.getName());
		jspContent = jspContent.replace("{**FILTER_HEIGHT**}", FILTER_HEIGHT);
		jspContent = jspContent.replace("datagridId", gui.getGridName());
		jspContent = jspContent.replace("formId", gui.getFormName());

		String ops = "";
		for (GUIOperation op : gui.getOperations()) {
			ops += String.format("function %s_%s(){\n}\n", gui.getFormName(), op.getOperationName());
		}
		jspContent = jspContent.replace("{**OPERATIONS**}", ops);

		String getters = "";
		for (GUIAttribute attr : gui.getAttributes()) {
			getters += String.format("///Ext.getCmp('%s_%s').getValue();\n", gui.getFormName(), attr.getAttributeName());
		}
		jspContent = jspContent.replace("{**GETTERS**}", getters);

		String setters = "";
		for (GUIAttribute attr : gui.getAttributes()) {
			setters += String.format("// Ext.getCmp('%s_%s').setValue('');\n", gui.getFormName(), attr.getAttributeName());
		}
		jspContent = jspContent.replace("{**SETTERS**}", setters);

		Utils.setFileContent(new File(MODEL_JSP_PATH + "/" + jspFileName), jspContent);
		System.out.println(jspFileName + " generated !");

	}

	public static void main(String[] args) throws IOException {
		GUI gui = new GUI();
		gui.setName("APDValidationRadioGrid");
		createJSP(gui);
	}

}
