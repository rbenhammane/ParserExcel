package ma.ericsson.granite.cli.util;

import java.util.Date;

import ma.ericsson.granite.cli.dao.OfferDAO;
import ma.ericsson.granite.cli.service.excel.ExcelParserContext;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;

public class ParserValidator {

	@Autowired
	private OfferDAO jdbcOfferDAO;

	public boolean validateOfferTyping(ExcelParserContext context, Cell cell) {
		return true;
	}

	public boolean validateOfferTechnology(ExcelParserContext context, Cell cell) {
		return true;
	}

	// public int validateProductCode(ExcelParserContext context, Cell cell) {
	// if (!validateValue(cell)) {
	// return 0;
	// }
	//
	// return jdbcOfferDAO.getProductId(context.getIdOffer(), cell.getStringCellValue());
	// }
	//
	// public int validateProductCode(ExcelParserContext context, String productCode) {
	// if (productCode == null) {
	// return 0;
	// }
	//
	// return jdbcOfferDAO.getProductId(context.getIdOffer(), productCode);
	// }

	public boolean validateProductDescription(ExcelParserContext context, Cell cell, int productId) {
		if (!validateValue(cell) || productId == 0) {
			return false;
		}

		String productDescription = jdbcOfferDAO.getProductDescription(productId);
		return cell.getStringCellValue().equals(productDescription);
	}

	public boolean validateOptionDescription(ExcelParserContext context, Cell cell, Cell productCode) {
		return true;
	}

	public String validateAttributeCode(ExcelParserContext context, Cell cell, long productId) {
		if (!validateValue(cell) || productId == 0) {
			return null;
		}

		return jdbcOfferDAO.getAttributeCode(productId, cell.getStringCellValue());
	}

	public boolean validateAttributeDescription(ExcelParserContext context, Cell cell, String attributeCode) {
		if (!validateValue(cell)) {
			return false;
		}

		String attibutDescription = jdbcOfferDAO.getAttributeDescription(attributeCode);
		return cell.getStringCellValue().equals(attibutDescription);
	}

	public boolean validateDefaultValue(ExcelParserContext context, Sheet sheet, Cell cell, int firstRow, int lastRow, int col) {
		try {
			if (validateValue(cell)) {
				for (int i = firstRow; i <= lastRow; i++) {
					if (validateValue(sheet.getRow(i).getCell(col)) && sheet.getRow(i).getCell(col).getStringCellValue().equals(cell.getStringCellValue())) {
						return true;
					}
				}

				return false;
			} else {
				return true;
			}
		} catch (IllegalStateException e) {
			System.out.println("Error row : " + firstRow + ", col : " + col);
			throw e;
		}
	}

	public String validateValueDescription(ExcelParserContext context, Cell cell, String attributeCode) {
		if (!validateValue(cell)) {
			return null;
		}

		return jdbcOfferDAO.getAttributeValueCode(attributeCode, getValue(cell));
	}

	public boolean validateEndDate(Cell cell) {
		return cell != null ? (new Date()).before(DateUtil.getJavaDate(cell.getNumericCellValue())) : false;
	}

	// TODO need refactoring
	public boolean validateValue(Cell cell) {

		return cell != null //
				&& cell.getCellType() != Cell.CELL_TYPE_BLANK//
				&& !(cell.getCellType() == Cell.CELL_TYPE_STRING //
				&& "".equals(cell.getStringCellValue().trim()));

	}

	public boolean validateNumeric(Cell cell) {
		if (cell != null) {
			return cell.getCellType() == Cell.CELL_TYPE_NUMERIC;
		} else {
			return false;
		}

	}

	public boolean validateDate(Cell cell) {
		if (cell != null) {
			return cell.getCellType() == Cell.CELL_TYPE_NUMERIC && cell.getNumericCellValue() > 0;
		} else {
			return false;
		}

	}

	public String getValue(Cell cell) {
		if (cell == null) {
			return null;
		}

		if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
			return cell.getStringCellValue().trim();
		}

		if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			return "" + (long) cell.getNumericCellValue();
		}

		return null;
	}

	public Date getDate(Cell cell) {
		if (validateDate(cell)) {
			return DateUtil.getJavaDate(cell.getNumericCellValue());
		} else {
			return null;
		}

	}

	public boolean validateBoolean(Cell cell) {
		if (cell != null) {
			String value = cell.getStringCellValue();
			return value.equalsIgnoreCase("Y") || value.equalsIgnoreCase("O") || value.equalsIgnoreCase("N");
		} else {
			return false;
		}

	}

	public boolean getBoolean(Cell cell) {
		return cell.getStringCellValue().equalsIgnoreCase("Y") || cell.getStringCellValue().equalsIgnoreCase("O");
	}

	public void setError(Sheet sheet, int row, int col) {
		Cell cell = sheet.getRow(row).getCell(col);

		if (cell == null) {
			cell = sheet.getRow(row).createCell(col);
		}

		CellStyle style = sheet.getWorkbook().createCellStyle();
		style.cloneStyleFrom(cell.getCellStyle());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setFillForegroundColor(IndexedColors.ORANGE.getIndex());

		Font font = sheet.getWorkbook().createFont();
		font.setColor(IndexedColors.YELLOW.getIndex());
		style.setFont(font);

		cell.setCellStyle(style);
	}

	public void setModified(Sheet sheet, int row, int col) {
		Cell cell = sheet.getRow(row).getCell(col);

		if (cell == null) {
			cell = sheet.getRow(row).createCell(col);
		}

		CellStyle style = sheet.getWorkbook().createCellStyle();
		style.cloneStyleFrom(cell.getCellStyle());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());

		cell.setCellStyle(style);
	}
}
