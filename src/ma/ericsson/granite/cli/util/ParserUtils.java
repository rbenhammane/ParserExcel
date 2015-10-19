package ma.ericsson.granite.cli.util;

import static ma.ericsson.granite.cli.util.ParserConstants.DOMAIN;
import static ma.ericsson.granite.cli.util.ParserConstants.SIC_EN;

import java.util.ArrayList;
import java.util.List;

import ma.ericsson.granite.cli.model.GUI;
import ma.ericsson.granite.cli.model.GUIOperation;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

public class ParserUtils {

	public static int next(Sheet sheet, int row, int col, boolean vertical) {
		CellRangeAddress range = getCellRange(sheet, row, col, vertical);
		return (range != null ? (vertical ? range.getLastRow() : range
				.getLastColumn()) : (vertical ? row : col)) + 1;
	}

	public static CellRangeAddress getCellRange(Sheet sheet, int row, int col,
			boolean vertical) {
		CellRangeAddress range = null;
		for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
			range = sheet.getMergedRegion(i);
			if (range.getFirstRow() == row
					&& range.getFirstColumn() == col
					&& (vertical ? range.getLastColumn() == col : range
							.getLastRow() == row)) {
				break;
			} else {
				range = null;
			}
		}
		return range;
	}

	public static String generateSicQuery(String sic, String sicValue) {
		String q = "INSERT INTO EAT.I_DICTIONARY (ID, SIC, LANG_ID, MESSAGE, DOMAIN) VALUES ("
				+ "(select max(ID) from EAT.I_DICTIONARY)+1,"
				+ "'"
				+ sic
				+ "','"
				+ SIC_EN
				+ "','"
				+ sicValue.replaceAll("'", " ") 
				+ "', '" 
				+ DOMAIN + "');";
		return q;
	}

	public static String generateButtonFormQuery(GUIOperation op, GUI gui) {
		String q = "INSERT INTO EAT.M_FORM_BUTTON (BUTTON_NAME, FORM_NAME, BUTTON_LABEL, ACTION, FORM_BIND, PRESENTATION_INDEX, BUTTON_SIC) VALUES ('%s', '%s', '%s', 'javascript:%s_%s()', 'N', '1', '%s');";
		// DowloadAPD
		// APDValidationCWForm
		// Dowload APD
		// javascript:DowloadAPD()
		// btn.inwi.sic.DowloadAPD
		return String.format(q, op.getOperationName(), gui.getFormName(),
				op.getName(), gui.getFormName(), op.getOperationName(),
				"btn.inwi.sic." + op.getOperationName());
	}

	public static List<String> createGraniteView(List<String> attrs, GUI gui) {
		List<String> outputViewCReation = new ArrayList<String>();
		outputViewCReation.add("CREATE OR REPLACE FORCE VIEW \"INSTALL\".\""
				+ gui.getGraniteViewName() + "\" (\"ROW_NUM\", ");
		outputViewCReation.add(ParserConstants.SQL_BLANK_LINE);
		String select = "";
		for (String attr : attrs) {
			if (attr.equals(attrs.get(attrs.size() - 1))) {
				outputViewCReation.add("\"" + attr + "\"");
			} else {
				outputViewCReation.add("\"" + attr + "\",");
			}
			select = select + "'" + attr + "',";
		}
		select = select.substring(0, select.length() - 1);

		outputViewCReation.add(") as ");
		outputViewCReation.add(ParserConstants.SQL_BLANK_LINE);
		outputViewCReation.add("SELECT 1," + select + " FROM DUAL");
		outputViewCReation.add(ParserConstants.SQL_BLANK_LINE);
		outputViewCReation.add("UNION");
		outputViewCReation.add(ParserConstants.SQL_BLANK_LINE);
		outputViewCReation.add("SELECT 2," + select + " FROM DUAL");
		outputViewCReation.add(ParserConstants.SQL_BLANK_LINE);
		outputViewCReation.add("UNION");
		outputViewCReation.add(ParserConstants.SQL_BLANK_LINE);
		outputViewCReation.add("SELECT 3," + select + " FROM DUAL;");
		outputViewCReation.add(ParserConstants.SQL_BLANK_LINE);
		outputViewCReation.add(ParserConstants.SQL_BLANK_LINE);
		return outputViewCReation;
	}
}
