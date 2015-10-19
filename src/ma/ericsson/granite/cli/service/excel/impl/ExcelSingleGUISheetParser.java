package ma.ericsson.granite.cli.service.excel.impl;

import ma.ericsson.granite.cli.exception.ExcelSheetException;
import ma.ericsson.granite.cli.model.*;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelSingleGUISheetParser extends BaseExcelFormSheetParser {

	private static final List<String> SHEET_TYPES = Arrays.asList("FORM", "COMBO", "FIELD", "BUTTON");

	private static final Map<String, Integer> JAVA_TYPES = new HashMap<>();
	static {
		JAVA_TYPES.put("LONG", 10);
		JAVA_TYPES.put("BIGDECIMAL", 12);
		JAVA_TYPES.put("STRING", 0);
		JAVA_TYPES.put("INTEGER", 1);
		JAVA_TYPES.put("DATE", 4);
		JAVA_TYPES.put("DATETIME", 5);
		JAVA_TYPES.put("BIGINTEGER", 11);
		JAVA_TYPES.put("BOOLEAN", 15);
		JAVA_TYPES.put("OBJECT", 99);
	}

	private static final Map<String, Integer> FORM_TYPES = new HashMap<>();
	static {
		FORM_TYPES.put("Radio Button", 15);
		FORM_TYPES.put("XType", 1000);
		FORM_TYPES.put("Text Field", 0);
		FORM_TYPES.put("Int", 1);
		FORM_TYPES.put("Real", 2);
		FORM_TYPES.put("Date", 4);
		FORM_TYPES.put("Time", 5);
		FORM_TYPES.put("Date Time", 6);
		FORM_TYPES.put("Text Area", 10);
		FORM_TYPES.put("Check Box", 12);
		FORM_TYPES.put("Combo Box", 13);
		FORM_TYPES.put("Multi Select", 14);
		FORM_TYPES.put("Label", 99);
		FORM_TYPES.put("Button", 999);
	}

	private static final Map<String, Integer> X_POSITIONS = new HashMap<>();
	static {
		X_POSITIONS.put("1", 10);
		X_POSITIONS.put("2", 310);
		X_POSITIONS.put("3", 610);
	}

	private static final Map<String, Integer> Y_POSITIONS = new HashMap<>();
	static {
		Y_POSITIONS.put("1", 50);
		Y_POSITIONS.put("2", 100);
		Y_POSITIONS.put("3", 150);
		Y_POSITIONS.put("4", 200);
		Y_POSITIONS.put("5", 250);
		Y_POSITIONS.put("6", 300);
		Y_POSITIONS.put("7", 350);
		Y_POSITIONS.put("8", 400);
		Y_POSITIONS.put("9", 450);
		Y_POSITIONS.put("10", 500);
	}

	@Override
	public void parseSheet(Sheet sheet) throws ExcelSheetException {

		output = new Form();
		output.setName(sheet.getSheetName());
		
		String value, readType, sheetType = null;

		for (int i = topLeftCellRow; i <= sheet.getLastRowNum(); i++) {

			// read row
			if (sheet.getRow(i) != null) {

				readType = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol));

				if (SHEET_TYPES.contains(readType)) {

					sheetType = readType;

				} else {

					switch (sheetType) {

						case "FORM":

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol))) == null) {
								validator.setError(sheet, i, topLeftCellCol);
								error = true;
							} else {
								output.setName(value);
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + 1))) == null) {
								validator.setError(sheet, i, topLeftCellCol + 1);
								error = true;
							} else {
								output.setDescription(value);
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + 2))) == null) {
								validator.setError(sheet, i, topLeftCellCol + 2);
								error = true;
							} else {
								output.setManager(value);
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + 3))) == null) {
								validator.setError(sheet, i, topLeftCellCol + 3);
								error = true;
							} else {
								output.setModel(value);
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + 4))) == null) {
								validator.setError(sheet, i, topLeftCellCol + 4);
								error = true;
							} else {
								output.setPk(value);
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + 5))) == null) {
								validator.setError(sheet, i, topLeftCellCol + 5);
								error = true;
							} else {
								output.setSic(value);
							}

							break;

						case "COMBO":

							Combo combo = new Combo();

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol))) == null) {
								validator.setError(sheet, i, topLeftCellCol);
								error = true;
							} else {
								combo.setName(value);
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + 1))) == null) {
								validator.setError(sheet, i, topLeftCellCol + 1);
								error = true;
							} else {
								combo.setKey(value);
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + 2))) == null) {
								validator.setError(sheet, i, topLeftCellCol + 2);
								error = true;
							} else {
								combo.setValue(value);
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + 3))) == null) {
								validator.setError(sheet, i, topLeftCellCol + 3);
								error = true;
							} else {
								combo.setQuery(value);
							}

							output.getComboList().add(combo);

							break;

						case "FIELD":

							Field field = new Field();

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol))) == null) {
								validator.setError(sheet, i, topLeftCellCol);
								error = true;
							} else {
								field.setName(value);
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + 1))) == null) {
								validator.setError(sheet, i, topLeftCellCol + 1);
								error = true;
							} else {
								field.setDescription(value);
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + 2))) == null) {
								validator.setError(sheet, i, topLeftCellCol + 2);
								error = true;
							} else {
								field.setGroupKey(value);
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + 3))) == null) {
								validator.setError(sheet, i, topLeftCellCol + 3);
								error = true;
							} else {
								field.setAttributeKey(value);
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + 4))) == null) {
								validator.setError(sheet, i, topLeftCellCol + 4);
								error = true;
							} else {
								field.setAttributeValue(value);
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + 5))) == null) {
								validator.setError(sheet, i, topLeftCellCol + 5);
								error = true;
							} else {
								field.setSic(value);
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + 6))) == null) {
								validator.setError(sheet, i, topLeftCellCol + 6);
								error = true;
							} else {
								field.setMandatory(value.equals("YES"));
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + 7))) == null) {
								validator.setError(sheet, i, topLeftCellCol + 7);
								error = true;
							} else {
								field.setFile(value.equals("YES"));
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + 8))) == null) {
								validator.setError(sheet, i, topLeftCellCol + 8);
								error = true;
							} else {
								field.setType(JAVA_TYPES.get(value));
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + 9))) == null) {
								validator.setError(sheet, i, topLeftCellCol + 9);
								error = true;
							} else {
								field.setFormType(FORM_TYPES.get(value));
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + 10))) == null) {
								validator.setError(sheet, i, topLeftCellCol + 10);
								error = true;
							} else {
								field.setPk(value.equals("YES"));
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + 11))) == null) {
								validator.setError(sheet, i, topLeftCellCol + 11);
								error = true;
							} else {
								field.setVisible(value.equals("YES"));
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + 12))) == null) {
								validator.setError(sheet, i, topLeftCellCol + 12);
								error = true;
							} else {
								field.setReadOnly(value.equals("YES"));
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + 13))) == null) {
								validator.setError(sheet, i, topLeftCellCol + 13);
								error = true;
							} else {
								field.setxPosition(X_POSITIONS.get(value));
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + 14))) == null) {
								validator.setError(sheet, i, topLeftCellCol + 14);
								error = true;
							} else {
								field.setyPosition(Y_POSITIONS.get(value));
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + 15))) != null) {
								field.setxPosition(Integer.parseInt(value));
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + 16))) != null) {
								field.setyPosition(Integer.parseInt(value));
							}

							output.getFieldList().add(field);

							break;

						case "BUTTON":

							Button button = new Button();

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol))) == null) {
								validator.setError(sheet, i, topLeftCellCol);
								error = true;
							} else {
								button.setName(value);
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + 1))) == null) {
								validator.setError(sheet, i, topLeftCellCol + 1);
								error = true;
							} else {
								button.setDescription(value);
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + 2))) == null) {
								validator.setError(sheet, i, topLeftCellCol + 2);
								error = true;
							} else {
								button.setWidth(Integer.parseInt(value));
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + 3))) == null) {
								validator.setError(sheet, i, topLeftCellCol + 3);
								error = true;
							} else {
								button.setHeight(Integer.parseInt(value));
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + 4))) == null) {
								validator.setError(sheet, i, topLeftCellCol + 4);
								error = true;
							} else {
								button.setxPosition(Integer.parseInt(value));
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + 5))) == null) {
								validator.setError(sheet, i, topLeftCellCol + 5);
								error = true;
							} else {
								button.setyPosition(Integer.parseInt(value));
							}

							output.getButtonList().add(button);

							break;
					}
				}
			}
		}
	}

}
