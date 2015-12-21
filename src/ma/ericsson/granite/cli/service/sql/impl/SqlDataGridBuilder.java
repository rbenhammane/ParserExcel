package ma.ericsson.granite.cli.service.sql.impl;

import static ma.ericsson.granite.cli.util.ParserConstants.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import ma.ericsson.granite.cli.model.GUI;
import ma.ericsson.granite.cli.model.GUIAttribute;
import ma.ericsson.granite.cli.util.ParserConstants;
import ma.ericsson.granite.cli.util.ParserUtils;
import ma.ericsson.service.gen.SRMSJspGenerator;
import ma.ericsson.service.gen.SRMSServiceGenerator;

public class SqlDataGridBuilder extends BaseSqlBuilder {

	@Override
	public void setQueries(GUI gui) {

		Map<String, String> sics = new HashMap<String, String>();
		output.add("-- DATAGRID");
		output.add(SQL_BLANK_LINE);
		output.add(SQL_BLANK_LINE);
		output.add("INSERT INTO EAT.M_DATAGRID (DATAGRID_NAME, DESCRIPTION, QUERY, DATASOURCE, DOMAIN, ACTION_EDIT, ACTION_NEW, ACTION_VIEW) VALUES ('"
				+ gui.getGridName()
				+ "', '"
				+ gui.getName()
				+ "', '"
				+ "SELECT * FROM \"INSTALL\"."+gui.getGraniteViewName()
				+ "', '"
				+ ParserConstants.DATASOURCE_GRANITEDS
				+ "', '"
				+ ParserConstants.DOMAIN
				+ "', '1', '1', '1'); ");
		output.add(SQL_BLANK_LINE);
		
		/** Delete script */
//		outputDelete.add("DELETE FROM \"EAT\".\"M_DATAGRID\" WHERE DATAGRID_NAME = '"+gui.getGridName()+"';");
//		outputDelete.add(SQL_BLANK_LINE);
		
		sics.put(DATAGRID_SIC_PREFIX + gui.getGridName(), gui.getName().replaceAll("'", "''"));
		output.add("-- DATAGRID SICS");
		output.add(SQL_BLANK_LINE);
		output.add(SQL_BLANK_LINE);
		
//		outputDelete.add(SQL_BLANK_LINE);
//		outputDelete.add("-- DELETE DATAGRID SICS");
//		outputDelete.add(SQL_BLANK_LINE);
		for (String sic : sics.keySet()) {
			output.add(ParserUtils.generateSicQuery(sic, sics.get(sic)));
			
//			outputDelete.add("DELETE FROM \"EAT\".\"I_DICTIONARY\" WHERE SIC = '"+sic+"' and DOMAIN = '"+DOMAIN+"';");
//			outputDelete.add(SQL_BLANK_LINE);
		}

		output.add(SQL_BLANK_LINE);
		
		//Jsp Creation
//		try {
//			SRMSJspGenerator.createJSP(gui);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
	}
}
