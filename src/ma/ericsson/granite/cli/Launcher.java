package ma.ericsson.granite.cli;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import ma.ericsson.granite.cli.exception.GUIParserException;
import ma.ericsson.granite.cli.exception.GUIParserInputException;
import ma.ericsson.granite.cli.model.Form;
import ma.ericsson.granite.cli.model.GUI;
import ma.ericsson.granite.cli.service.GUIBuilder;
import ma.ericsson.granite.cli.service.SRMSParser;
import ma.ericsson.service.gen.SRMSConstantsGenerator;
import ma.ericsson.service.gen.SRMSJspGenerator;
import ma.ericsson.service.gen.SRMSModelGenerator;
import ma.ericsson.service.gen.SRMSServiceGenerator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Launcher {

	protected static final Log log = LogFactory.getLog(Launcher.class);
	
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		// String file = "SRMS_ScheduleAndEffort_v02_formatted.xlsx";
		String file = "gui.xlsx";

		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "applicationResources.xml", "applicationContext.xml" });

		SRMSParser<InputStream, Form> parser = (SRMSParser<InputStream, Form>) context.getBean("singleGUIParser");

		GUIBuilder<Form, List<String>> builder = (GUIBuilder<Form, List<String>>) context.getBean("singleFormBuilder");

		Form form;
		List<String> output;

		try {
			FileInputStream in = new FileInputStream(file);
			form = parser.parseGUIs(in);

			try {
//				SRMSConstantsGenerator.createConstants(form);
//				SRMSJspGenerator.createJSP(form);
//				SRMSModelGenerator.createClassModel(form);
				SRMSServiceGenerator.createClassService(form);
			} catch (Exception e) {
				e.printStackTrace();
			}

//			SRMSConstantsGenerator.close();

			output = builder.build(form);

			PrintWriter writer = new PrintWriter("gui.sql");

			for (int i = 0; i < output.size(); i++) {
				writer.write(output.get(i));
			}

			writer.flush();
			writer.close();

		} catch (GUIParserInputException e) {
			String errorFile = file.substring(0, file.lastIndexOf('.')) + "_errors.xlsx";
			log.error("Error validating GUIs from file '" + file + "', see the file '" + errorFile + "' for more details");
			try {
				FileOutputStream out = new FileOutputStream(errorFile);
				out.write(((ByteArrayOutputStream) e.getOutStream()).toByteArray());
				out.flush();
				out.close();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e2) {
				e.printStackTrace();
			}
		} catch (GUIParserException e) {
			log.error("Error parsing GUIs from file '" + file + "' : " + e.toString());
		} catch (IOException e) {
			log.error("Unknown error occured while parsing file '" + file + "' : " + e.toString());
		}

	}
	
	
}
