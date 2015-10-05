package ma.ericsson.granite.cli.service.sql.impl;

import static ma.ericsson.granite.cli.util.ParserConstants.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

		for (GUIAttribute attribute : gui.getAttributes()) {
			output.add("INSERT INTO \"EAT\".\"M_FORM_FIELD\" (FORM_NAME, FIELD_NAME, FIELD_DESCRIPTION, FIELD_CAPTION, FIELD_SIC, MANDATORY, FIELD_TYPE, FORM_FIELD_TYPE, MIN_LENGTH, MAX_LENGTH, VISIBLE) VALUES ('"
					+ gui.getFormName()
					+ "', '"
					+ attribute.getAttributeName()
					+ "', '"
					+ attribute.getName().replaceAll("'", "''")
					+ "', '"
					+ attribute.getName().replaceAll("'", "''")
					+ "', '"+FIELD_FORM_SIC_PREFIX
					+ attribute.getAttributeName()
					+ "', 'N', '1', '0', '0', '0', 'Y');");
			output.add(SQL_BLANK_LINE);
			sics.put(FIELD_FORM_SIC_PREFIX + attribute.getAttributeName(), attribute.getName().replaceAll("'", "''"));
			
			/** Delete script */
			outputDelete.add("DELETE FROM \"EAT\".\"M_FORM_FIELD\" WHERE FIELD_NAME = '"+attribute.getAttributeName()+"';");
			outputDelete.add(SQL_BLANK_LINE);
		}
		//Form Delete
		outputDelete.add("-- Delete FORM");
		outputDelete.add(SQL_BLANK_LINE);
		outputDelete.add("DELETE FROM \"EAT\".\"M_FORM\" WHERE FORM_NAME = '"+gui.getFormName()+"';");
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
			
			outputDelete.add("DELETE FROM \"EAT\".\"I_DICTIONARY\" WHERE SIC = '"+sic+"' and DOMAIN = '"+DOMAIN+"';");
			outputDelete.add(SQL_BLANK_LINE);
		}

		output.add(SQL_BLANK_LINE);
	}
}
