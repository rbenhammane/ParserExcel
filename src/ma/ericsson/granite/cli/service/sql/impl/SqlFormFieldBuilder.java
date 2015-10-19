package ma.ericsson.granite.cli.service.sql.impl;

import static ma.ericsson.granite.cli.util.ParserConstants.DOMAIN;
import static ma.ericsson.granite.cli.util.ParserConstants.FIELD_FORM_SIC_PREFIX;
import static ma.ericsson.granite.cli.util.ParserConstants.SQL_BLANK_LINE;

import java.util.HashMap;
import java.util.Map;

import ma.ericsson.granite.cli.model.GUI;
import ma.ericsson.granite.cli.model.GUIAttribute;
import ma.ericsson.granite.cli.util.ParserUtils;

public class SqlFormFieldBuilder extends BaseSqlBuilder {

	@Override
	public void setQueries(GUI gui) {
		Map<String, String> sics = new HashMap<String, String>();
		output.add("-- FORM FIELDS");
		output.add(SQL_BLANK_LINE);
		output.add(SQL_BLANK_LINE);

		outputDelete.add("-- Delete Form FIELD");
		outputDelete.add(SQL_BLANK_LINE);

		output.add("INSERT INTO \"EAT\".\"M_FORM_FIELD\" (FORM_NAME, FIELD_NAME, FIELD_SIC, PK, MANDATORY, FIELD_TYPE, FORM_FIELD_TYPE, MIN_LENGTH, MAX_LENGTH, VISIBLE) VALUES ('" + gui.getFormName() + "', 'ROW_NUM', 'field.empty', 'Y', 'N', '10', '0', '0', '0', 'N');");
		output.add(SQL_BLANK_LINE);
		outputDelete.add("DELETE FROM \"EAT\".\"M_FORM_FIELD\" WHERE FIELD_NAME = 'ROW_NUM' AND FORM_NAME = '" + gui.getFormName() + "';");
		outputDelete.add(SQL_BLANK_LINE);

		/** Delete script */
		outputDelete.add("DELETE FROM \"EAT\".\"M_FORM_BUTTON\" WHERE FORM_NAME = '" + gui.getFormName() + "';");
		outputDelete.add(SQL_BLANK_LINE);

		int i = 0;
		for (GUIAttribute attr : gui.getAttributes()) {
			String caption = attr.getName().replaceAll("'", " ");
			// boolean visible = !attribute.getName().contains("NOT TO BE DISPLAYED IN GUI");
			boolean hidden = attr.getIsHidden();

			String type = attr.getDataType();
			// cmbYesNo
			String combo = attr.getComboName();
			String fieldType = attr.getFieldType(); // type.toLowerCase().equals("picklist") ? "13" : "1";
			String formFieldType = attr.getFormFieldType(); // type.toLowerCase().equals("date") ? "4" : "0";
			String mandatory = attr.getAccess().contains("write") ? "Y" : "N";
			output.add("INSERT INTO \"EAT\".\"M_FORM_FIELD\" (FORM_NAME, FIELD_NAME, FIELD_DESCRIPTION, FIELD_CAPTION, FIELD_SIC, MANDATORY, FIELD_TYPE, FORM_FIELD_TYPE, MIN_LENGTH, MAX_LENGTH, VISIBLE, COMBO_NAME,PRESENTATION_INDEX) " + //
					"VALUES ('" + gui.getFormName() + "', '" + attr.getAttributeName() + "', '" + caption + "', '" + caption + "', '" + FIELD_FORM_SIC_PREFIX + gui.getFormName().toLowerCase() + "." + attr.getAttributeName() + "', '" + mandatory + "', '" + fieldType + "', '" + formFieldType + "', '0', '0', '" + (hidden ? "N" : "Y") + "'," + combo + "," + i++ + ");");
			output.add(SQL_BLANK_LINE);
			sics.put(FIELD_FORM_SIC_PREFIX + gui.getFormName().toLowerCase() + "." + attr.getAttributeName(), caption);

			/** Delete script */
			outputDelete.add("DELETE FROM \"EAT\".\"M_FORM_FIELD\" WHERE FIELD_NAME = '" + attr.getAttributeName() + "' AND FORM_NAME = '" + gui.getFormName() + "';");
			outputDelete.add(SQL_BLANK_LINE);

		}
		// Form Delete
		outputDelete.add("-- Delete FORM");
		outputDelete.add(SQL_BLANK_LINE);
		outputDelete.add("DELETE FROM \"EAT\".\"M_FORM_FIELD\" WHERE FORM_NAME = '" + gui.getFormName() + "';");
		outputDelete.add(SQL_BLANK_LINE);

		output.add("-- FORM FIELDS SICS");
		output.add(SQL_BLANK_LINE);
		output.add(SQL_BLANK_LINE);

		outputDelete.add(SQL_BLANK_LINE);
		outputDelete.add("-- DELETE FORM FIELD SICS");
		outputDelete.add(SQL_BLANK_LINE);
		for (String sic : sics.keySet()) {
			output.add(ParserUtils.generateSicQuery(sic, sics.get(sic)));
			output.add(SQL_BLANK_LINE);

			outputDelete.add("DELETE FROM \"EAT\".\"I_DICTIONARY\" WHERE SIC = '" + sic + "' and DOMAIN = '" + DOMAIN + "';");
			outputDelete.add(SQL_BLANK_LINE);
		}

		output.add(SQL_BLANK_LINE);
	}
}
