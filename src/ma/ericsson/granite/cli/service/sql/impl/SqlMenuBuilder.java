package ma.ericsson.granite.cli.service.sql.impl;

import static ma.ericsson.granite.cli.util.ParserConstants.*;

import java.util.HashMap;
import java.util.Map;

import ma.ericsson.granite.cli.model.GUI;
import ma.ericsson.granite.cli.util.ParserConstants;
import ma.ericsson.granite.cli.util.ParserUtils;

public class SqlMenuBuilder extends BaseSqlBuilder {

	@Override
	public void setQueries(GUI gui) {

		Map<String, String> sics = new HashMap<String, String>();
		output.add("-- MENU");
		output.add(SQL_BLANK_LINE);
		output.add(SQL_BLANK_LINE);

		output.add("INSERT INTO \"EAT\".\"M_MENU_ITEM\" (MENU_ITEM_ID, MENU_ID, ITEM_NAME, ITEM_SIC, URL, PRIVILEGE, PRESENTATION_INDEX) VALUES ("
				+ "(select max(MENU_ITEM_ID) from \"EAT\".\"M_MENU_ITEM\")+1"
				+ ", '"
				+ MENU_ID
				+ "', '"
				+ gui.getName()
				+ "', '"+MENU_SIC_PREFIX+gui.getGridName()
				+ "', '"+JSP_FOLDER+gui.getJSPName()
				+ "','" + PRIVILLEGE_ALL + "', '1');");

		/** Delete script */
		outputDelete.add("-- Delete MENU");
		outputDelete.add(SQL_BLANK_LINE);
		outputDelete.add("DELETE FROM \"EAT\".\"M_MENU_ITEM\" WHERE ITEM_NAME = '"+gui.getName()+"';");
		outputDelete.add(SQL_BLANK_LINE);
		
		
		sics.put(MENU_SIC_PREFIX + gui.getGridName(), gui.getName().replaceAll("'", "''"));
		output.add(SQL_BLANK_LINE);
		output.add("-- FORM FIELDS SICS");
		output.add(SQL_BLANK_LINE);
		output.add(SQL_BLANK_LINE);
		
		outputDelete.add(SQL_BLANK_LINE);
		outputDelete.add("-- DELETE MENU SICS");
		outputDelete.add(SQL_BLANK_LINE);
		for (String sic : sics.keySet()) {
			output.add(ParserUtils.generateSicQuery(sic, sics.get(sic)));
			
			outputDelete.add("DELETE FROM \"EAT\".\"I_DICTIONARY\" WHERE SIC = '"+sic+"' and DOMAIN = '"+DOMAIN+"';");
			outputDelete.add(SQL_BLANK_LINE);
		}
	}
}
