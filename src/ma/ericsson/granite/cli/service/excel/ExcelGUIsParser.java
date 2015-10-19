package ma.ericsson.granite.cli.service.excel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import ma.ericsson.granite.cli.exception.ExcelSheetException;
import ma.ericsson.granite.cli.exception.GUIParserException;
import ma.ericsson.granite.cli.exception.GUIParserInputException;
import ma.ericsson.granite.cli.model.GUI;
import ma.ericsson.granite.cli.service.SRMSParser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelGUIsParser implements SRMSParser<InputStream, List<GUI>> {

	protected final Log log = LogFactory.getLog(getClass());

	private ExcelSheetParser<Sheet, GUI> sheetParser;
	private ExcelParserContext context;

	public void setSheetParser(ExcelSheetParser<Sheet, GUI> sheetParser) {
		this.sheetParser = sheetParser;
	}

	public void setContext(ExcelParserContext context) {
		this.context = context;
	}

	public List<GUI> parseGUIs(InputStream input) throws GUIParserException {
		try {
			// Creates the workbook from the input stream
			Workbook wb = new XSSFWorkbook(input);

			boolean error = false;

			// Loops over the workbook sheets, and try to parse them one by one
			for (int i = 1; i < wb.getNumberOfSheets(); i++) {
				String sheetName = wb.getSheetAt(i).getSheetName();
				if(sheetName.toLowerCase().startsWith("gen-")){
					sheetName = sheetName.replace("gen-", "");
				}else{
					continue;					
				}
				
				log.info("################################################################################");
				log.info("Parsing sheet \"" + sheetName + "\"");
				GUI gui = sheetParser.parse(wb.getSheetAt(i));

				if (gui == null) {
					log.warn("Sheet \"" + sheetName + "\" contains errors");
					error = true;
				} else {
					context.getData().add(gui);
					log.info("Sheet \"" + sheetName + "\" parsed successfully");
				}
			}

			// An error occurred while parsing the input
			if (error) {
				OutputStream out = new ByteArrayOutputStream();
				wb.write(out);
				out.close();

				throw new GUIParserInputException(out);
			}

		} catch (IOException e) {
			// An error occurred while trying to load the workbook
			throw new GUIParserException(e.toString());
		} catch (ExcelSheetException e) {
			// An error occurred while parsing workbook sheets
			log.error("Error parsing sheet");
			throw new GUIParserException(e.toString());
		} catch (Exception e) {
			e.printStackTrace();
			if (GUIParserInputException.class.isInstance(e)) {
				throw (GUIParserInputException) e;
			}
			// A unknown error occurred
			throw new GUIParserException("Error parsing GUIs : " + e.toString());
		}
		return context.getData();
	}
}
