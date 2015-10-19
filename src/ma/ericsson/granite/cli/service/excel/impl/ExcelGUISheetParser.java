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
		
		output.setName(sheet.getSheetName().replace("gen-", ""));

		String value;

		for (int i = topLeftCellRow; i <= sheet.getLastRowNum(); i++) {

			// read attribute
			if (sheet.getRow(i) != null) {
				if (validator.validateValue(sheet.getRow(i).getCell(topLeftCellCol))) {

					GUIAttribute attribute = new GUIAttribute();

					/*************************************************/
					/*********** OtherInfo **************************/
					/*************************************************/
					value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + 0));
					if (value == null) {
						validator.setError(sheet, i, topLeftCellCol + 5);
						// error = true;
					} else {
						attribute.setOtherInfo(value);
					}

					
					/*************************************************/
					/************ DATA *******************************/
					/*************************************************/
					attribute.setName(validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + 1)));

					/*************************************************/
					/************ Comments ***************************/
					/*************************************************/
					attribute.setComment(validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + 2)));

					/*************************************************/
					/************ CDRM Mapping ***********************/
					/*************************************************/
					value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + 3));
					if (value == null) {
						validator.setError(sheet, i, topLeftCellCol + 3);
						// error = true;
					} else {
						attribute.setMapping(value);
					}

					/*************************************************/
					/************ GUI Data Type **********************/
					/*************************************************/
					value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + 4));
					if ( value == null) {
						validator.setError(sheet, i, topLeftCellCol + 4);
						// error = true;
					} else {
						attribute.setDataType(value.toLowerCase());
					}
					/*************************************************/
					/************ READ/WRITE **********************/
					/*************************************************/
					value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + 5));
					if ( value == null) {
						validator.setError(sheet, i, topLeftCellCol + 5);
						// error = true;
					} else {
						attribute.setAccess(value.toLowerCase());
					}

					output.getAttributes().add(attribute);

				}

				// read operation
				if (validator.validateValue(sheet.getRow(i).getCell(topLeftCellCol + 7))) {

					GUIOperation operation = new GUIOperation(output);

					/*************************************************/
					/*************************************************/
					/*************************************************/
					operation.setName(validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + 7)));

					/*************************************************/
					/*************************************************/
					/*************************************************/
					operation.setComment(validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + 8)));

					output.getOperations().add(operation);

				}
			}
		}
	}

}
