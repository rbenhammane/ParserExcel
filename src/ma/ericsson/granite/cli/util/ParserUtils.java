package ma.ericsson.granite.cli.util;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

public class ParserUtils {

	public static int next(Sheet sheet, int row, int col, boolean vertical) {
		CellRangeAddress range = getCellRange(sheet, row, col, vertical);
		return (range != null ? (vertical ? range.getLastRow() : range.getLastColumn()) : (vertical ? row : col)) + 1;
	}

	public static CellRangeAddress getCellRange(Sheet sheet, int row, int col, boolean vertical) {
		CellRangeAddress range = null;
		for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
			range = sheet.getMergedRegion(i);
			if (range.getFirstRow() == row && range.getFirstColumn() == col
					&& (vertical ? range.getLastColumn() == col : range.getLastRow() == row)) {
				break;
			} else {
				range = null;
			}
		}
		return range;
	}
}
