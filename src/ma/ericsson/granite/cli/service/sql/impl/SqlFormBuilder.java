package ma.ericsson.granite.cli.service.sql.impl;

import static ma.ericsson.granite.cli.util.ParserConstants.*;

import java.util.HashMap;
import java.util.Map;

import ma.ericsson.granite.cli.model.GUI;
import ma.ericsson.granite.cli.util.ParserUtils;

public class SqlFormBuilder extends BaseSqlBuilder {

	@Override
	public void setQueries(GUI gui) {

		Map<String, String> sics = new HashMap<String, String>();
		output.add("-- FORM");
		output.add(SQL_BLANK_LINE);
		output.add(SQL_BLANK_LINE);

		output.add("INSERT INTO \"EAT\".\"M_FORM\" (FORM_NAME, FORM_DESCRIPTION, MANAGER_CLASS, ENTITY_MANAGER, CLASS_NAME, PK_NAME, DOMAIN, FORM_LAYOUT, COLUMNS) VALUES ('"
				+ gui.getFormName()
				+ "', '"
				+ gui.getName()
				+ "', 'srms.acquisition.forms.inwi."
				+ gui.getFormManagerClass()
				+ "FormService', '"
				+ ENTITY_MANAGER
				+ "', 'srms.acquisition.forms.inwi."
				+ gui.getFormModelClass()
				+ "', 'ROW_NUM', '" + DOMAIN + "', '1', '3');");

		/** Delete script */
//		outputDelete.add("DELETE FROM \"EAT\".\"M_FORM\" WHERE FORM_NAME = '"+gui.getFormName()+"';");
//		outputDelete.add(SQL_BLANK_LINE);
		
		sics.put(FORM_SIC_PREFIX + gui.getFormName(), gui.getName().replaceAll("'", "''"));
		output.add(SQL_BLANK_LINE);
		
		output.add("-- FORM SICS");
		output.add(SQL_BLANK_LINE);
		output.add(SQL_BLANK_LINE);
		
		outputDelete.add(SQL_BLANK_LINE);
		outputDelete.add("-- DELETE FORM SICS");
		outputDelete.add(SQL_BLANK_LINE);
		for (String sic : sics.keySet()) {
			output.add(ParserUtils.generateSicQuery(sic, sics.get(sic)));
			
			outputDelete.add("DELETE FROM \"EAT\".\"I_DICTIONARY\" WHERE SIC = '"+sic+"' and DOMAIN = '"+DOMAIN+"';");
			outputDelete.add(SQL_BLANK_LINE);
		}

	}
}
