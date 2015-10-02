package ma.ericsson.granite.cli.service.excel.impl;

import ma.ericsson.granite.cli.exception.ExcelSheetException;
import ma.ericsson.granite.cli.model.GUI;
import ma.ericsson.granite.cli.model.GUIAttribute;
import ma.ericsson.granite.cli.model.GUIOperation;

import org.apache.poi.ss.usermodel.Sheet;

public class ExcelGUISheetParser extends BaseExcelSheetParser {

	@Override
	public void parseSheet(Sheet sheet) throws ExcelSheetException {

		output = new GUI();
		output.setName(sheet.getSheetName());
		
		String value;

		for (int i = topLeftCellRow; i <= sheet.getLastRowNum(); i++) {

			// read attribute
			if (sheet.getRow(i) != null) {
				if (validator.validateValue(sheet.getRow(i).getCell(topLeftCellCol))) {
				
					GUIAttribute attribute = new GUIAttribute();
	
					attribute.setName(validator.getValue(sheet.getRow(i).getCell(topLeftCellCol)));
					
					attribute.setComment(validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + 1)));
	
					if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + 2))) == null) {
						validator.setError(sheet, i, topLeftCellCol + 2);
						error = true;
					} else {
						attribute.setMapping(value);
					}
	
					if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + 3))) == null) {
						validator.setError(sheet, i, topLeftCellCol + 3);
						error = true;
					} else {
						attribute.setDataType(value.toLowerCase());
					}
	
					if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + 4))) == null) {
						validator.setError(sheet, i, topLeftCellCol + 4);
						error = true;
					} else {
						attribute.setAccess(value.toLowerCase());
					}
					
					output.getAttributes().add(attribute);
					
				}
				

				// read operation
				if (validator.validateValue(sheet.getRow(i).getCell(topLeftCellCol + 6))) {
					
					GUIOperation operation = new GUIOperation();
					
					operation.setName(validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + 6)));
					
					operation.setComment(validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + 7)));
					
				}
			}
		}
	}

}
