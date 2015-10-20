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
		Y_POSITIONS.put("1", 10);
		Y_POSITIONS.put("2", 40);
		Y_POSITIONS.put("3", 70);
		Y_POSITIONS.put("4", 100);
		Y_POSITIONS.put("5", 130);
		Y_POSITIONS.put("6", 160);
		Y_POSITIONS.put("7", 190);
		Y_POSITIONS.put("8", 220);
		Y_POSITIONS.put("9", 250);
		Y_POSITIONS.put("10", 280);
	}

	@Override
	public void parseSheet(Sheet sheet) throws ExcelSheetException {

		output = new Form();
		output.setName(sheet.getSheetName());
		
		String value, readType, sheetType = "";
		int index;

		for (int i = topLeftCellRow; i <= sheet.getLastRowNum(); i++) {

			// read row
			if (sheet.getRow(i) != null) {

				readType = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol));

				if (readType == null || readType.trim().equals("")) {

					continue;

				} else if (SHEET_TYPES.contains(readType)) {

					sheetType = readType;

				} else {

					switch (sheetType) {

						case "FORM":
							index = 0;

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + index))) == null) {
								validator.setError(sheet, i, topLeftCellCol + index++);
								error = true;
							} else {
								output.setName(value);
								index++;
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + index))) == null) {
								validator.setError(sheet, i, topLeftCellCol + index++);
								error = true;
							} else {
								output.setDescription(value);
								index++;
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + index))) == null) {
								validator.setError(sheet, i, topLeftCellCol + index++);
								error = true;
							} else {
								output.setManager(value);
								index++;
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + index))) == null) {
								validator.setError(sheet, i, topLeftCellCol + index++);
								error = true;
							} else {
								output.setModel(value);
								index++;
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + index))) == null) {
								validator.setError(sheet, i, topLeftCellCol + index++);
								error = true;
							} else {
								output.setPk(value);
								index++;
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + index))) == null) {
								validator.setError(sheet, i, topLeftCellCol + index++);
								error = true;
							} else {
								output.setSic(value);
								index++;
							}

							break;

						case "COMBO":
							index = 0;

							Combo combo = new Combo();

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + index))) == null) {
								validator.setError(sheet, i, topLeftCellCol + index++);
								error = true;
							} else {
								combo.setName(value);
								index++;
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + index))) == null) {
								validator.setError(sheet, i, topLeftCellCol + index++);
								error = true;
							} else {
								combo.setKey(value);
								index++;
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + index))) == null) {
								validator.setError(sheet, i, topLeftCellCol + index++);
								error = true;
							} else {
								combo.setValue(value);
								index++;
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + index))) == null) {
								validator.setError(sheet, i, topLeftCellCol + index++);
								error = true;
							} else {
								combo.setQuery(value);
								index++;
							}

							output.getComboList().add(combo);

							break;

						case "FIELD":
							index = 0;

							Field field = new Field();

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + index))) == null) {
								validator.setError(sheet, i, topLeftCellCol + index++);
								error = true;
							} else {
								field.setName(value);
								index++;
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + index))) == null) {
								validator.setError(sheet, i, topLeftCellCol + index++);
								error = true;
							} else {
								field.setDescription(value);
								index++;
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + index))) == null) {
								validator.setError(sheet, i, topLeftCellCol + index++);
								error = true;
							} else {
								field.setGroupKey(value);
								index++;
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + index))) == null) {
								validator.setError(sheet, i, topLeftCellCol + index++);
								error = true;
							} else {
								field.setAttributeKey(value);
								index++;
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + index))) == null) {
								validator.setError(sheet, i, topLeftCellCol + index++);
								error = true;
							} else {
								field.setAttributeValue(value);
								index++;
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + index))) == null) {
								validator.setError(sheet, i, topLeftCellCol + index++);
								error = true;
							} else {
								field.setSic(value);
								index++;
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + index))) == null) {
								validator.setError(sheet, i, topLeftCellCol + index++);
								error = true;
							} else {
								field.setMandatory(value.equals("YES"));
								index++;
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + index))) == null) {
								validator.setError(sheet, i, topLeftCellCol + index++);
								error = true;
							} else {
								field.setType(JAVA_TYPES.get(value));
								index++;
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + index))) == null) {
								validator.setError(sheet, i, topLeftCellCol + index++);
								error = true;
							} else {
								field.setFormType(FORM_TYPES.get(value));
								index++;
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + index))) == null) {
								validator.setError(sheet, i, topLeftCellCol + index++);
								error = true;
							} else {
								field.setPk(value.equals("YES"));
								index++;
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + index))) == null) {
								validator.setError(sheet, i, topLeftCellCol + index++);
								error = true;
							} else {
								field.setVisible(value.equals("YES"));
								index++;
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + index))) == null) {
								validator.setError(sheet, i, topLeftCellCol + index++);
								error = true;
							} else {
								field.setReadOnly(value.equals("YES"));
								index++;
							}

							if (field.isVisible()) {

								if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + index))) == null) {
									validator.setError(sheet, i, topLeftCellCol + index++);
									error = true;
								} else {
									field.setxPosition(X_POSITIONS.get(value));
									index++;
								}

								if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + index))) == null) {
									validator.setError(sheet, i, topLeftCellCol + index++);
									error = true;
								} else {
									field.setyPosition(Y_POSITIONS.get(value));
									index++;
								}

								if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + index++))) != null && !value.equals("")) {
									field.setxPosition(Integer.parseInt(value));
								}

								if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + index++))) != null && !value.equals("")) {
									field.setyPosition(Integer.parseInt(value));
								}

							}

							output.getFieldList().add(field);

							break;

						case "BUTTON":
							index = 0;

							Button button = new Button();

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + index))) == null) {
								validator.setError(sheet, i, topLeftCellCol + index++);
								error = true;
							} else {
								button.setName(value);
								index++;
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + index))) == null) {
								validator.setError(sheet, i, topLeftCellCol + index++);
								error = true;
							} else {
								button.setDescription(value);
								index++;
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + index))) == null) {
								validator.setError(sheet, i, topLeftCellCol + index++);
								error = true;
							} else {
								button.setWidth(Integer.parseInt(value));
								index++;
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + index))) == null) {
								validator.setError(sheet, i, topLeftCellCol + index++);
								error = true;
							} else {
								button.setHeight(Integer.parseInt(value));
								index++;
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + index))) == null) {
								validator.setError(sheet, i, topLeftCellCol + index++);
								error = true;
							} else {
								button.setSic(value);
								index++;
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + index++))) != null) {
								button.setOnChange(value);
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + index))) == null) {
								validator.setError(sheet, i, topLeftCellCol + index++);
								error = true;
							} else {
								button.setxPosition(Integer.parseInt(value));
								index++;
							}

							if ((value = validator.getValue(sheet.getRow(i).getCell(topLeftCellCol + index))) == null) {
								validator.setError(sheet, i, topLeftCellCol + index++);
								error = true;
							} else {
								button.setyPosition(Integer.parseInt(value));
								index++;
							}

							output.getButtonList().add(button);

							break;
					}
				}
			}
		}
	}

}
