package ma.ericsson.service.gen;

import java.io.File;
import java.io.IOException;

import ma.ericsson.granite.cli.model.Button;
import ma.ericsson.granite.cli.model.Field;
import ma.ericsson.granite.cli.model.Form;
import ma.ericsson.granite.cli.model.GUI;
import ma.ericsson.utils.Utils;

import org.apache.log4j.Logger;

public class SRMSJspGenerator {

	protected static final Logger log = Logger.getLogger(SRMSJspGenerator.class);
	// protected static final Log log = LogFactory.getLog(SRMSJspGenerator.class);

	private static String FILTER_HEIGHT = "300";

	private final static String MODEL_JSP_PATH = "../SRMS/WebContent/apps/srms/inwi-gen";
	private final static String MODEL_JSP = "./generic.jsp";

	public static void createJSP(Form gui) throws IOException {
		String jspFileName = gui.getDescription().replace(" ", "_").toLowerCase()+".jsp";

		String jspContent = Utils.getFileContent(MODEL_JSP);
		jspContent = jspContent.replace("{**DATAGRID-NAME**}", "CandidateListAssociated");
		jspContent = jspContent.replace("{**FILTERFORM-NAME**}", gui.getName());
		jspContent = jspContent.replace("{**FORM-NAME**}", gui.getName());

		jspContent = jspContent.replace("{**TITLE**}", gui.getName());
		jspContent = jspContent.replace("{**FILTER_HEIGHT**}", FILTER_HEIGHT);
		jspContent = jspContent.replace("datagridId", "datagridId");
		jspContent = jspContent.replace("formId", gui.getName());

		String ops = "";
		for (Button op : gui.getButtonList()) {
			ops += String.format("function %s_%s(){\n}\n", gui.getName(), op.getOperation());
		}
		jspContent = jspContent.replace("{**OPERATIONS**}", ops);

		String getters = "";
		for (Field attr : gui.getFieldList()) {
			getters += String.format("///Ext.getCmp('%s_%s').getValue();\n", gui.getName(), attr.getName());
		}
		jspContent = jspContent.replace("{**GETTERS**}", getters);

		String setters = "";
		for (Field attr : gui.getFieldList()) {
			setters += String.format("// Ext.getCmp('%s_%s').setValue('');\n", gui.getName(), attr.getName());
		}
		jspContent = jspContent.replace("{**SETTERS**}", setters);

		Utils.setFileContent(new File(MODEL_JSP_PATH + "/" + jspFileName), jspContent);
		System.out.println(jspFileName + " generated !");

	}

	public static void main(String[] args) throws IOException {
		GUI gui = new GUI();
		gui.setName("APDValidationRadioGrid");
		// createJSP(gui);
	}

}
