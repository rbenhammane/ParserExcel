package ma.ericsson.granite.cli.service.sql.impl;

import static ma.ericsson.granite.cli.util.ParserConstants.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ma.ericsson.granite.cli.model.GUI;
import ma.ericsson.granite.cli.model.GUIAttribute;
import ma.ericsson.granite.cli.util.ParserConstants;
import ma.ericsson.granite.cli.util.ParserUtils;
import ma.ericsson.service.gen.SRMSModelGenerator;

public class SqlDataGridFieldBuilder extends BaseSqlBuilder {

	@Override
	public void setQueries(GUI gui) {

		Map<String, String> sics = new HashMap<String, String>();
		Map<String, String> jpaAttr = new HashMap<String, String>();
		List<String> attrs = new ArrayList<String>();
		output.add("-- DATAGRID FIELDS");
		output.add(SQL_BLANK_LINE);
		output.add(SQL_BLANK_LINE);
		
		outputDelete.add("-- Delete DATAGRID Field");
		outputDelete.add(SQL_BLANK_LINE);
		
		for (GUIAttribute attribute : gui.getAttributes()) {
			output.add("INSERT INTO \"EAT\".\"M_DATAGRID_FIELD\" (DATAGRID_NAME, FIELD_NAME, FIELD_TYPE, FIELD_CAPTION, FIELD_SIC, PRIVILEGE, VISIBLE, PRESENTATION_INDEX, PK, DEFAULT_WIDTH, DOMAIN, SORTABLE, FILTERABLE) VALUES ('"
					+ gui.getGridName()
					+ "', '"
					+ attribute.getAttributeName()
					+ "', '"
					+ "0', '"
					+ attribute.getName().replaceAll("'", "''").replaceAll(" ", "_")
					+ "', 'field.inwi." + attribute.getAttributeName()
					+ "', '*', 'Y', '1', 'N', '0','"
					+ ParserConstants.DOMAIN
					+ "','Y', 'Y' );");
			output.add(SQL_BLANK_LINE);
			
			sics.put(FIELD_DATAGRID_SIC_PREFIX + attribute.getAttributeName(), attribute.getName().replaceAll("'", "''"));
			/** Delete script */
			outputDelete.add("DELETE FROM \"EAT\".\"M_DATAGRID_FIELD\" WHERE FIELD_NAME = '"+attribute.getAttributeName()+"';");
			outputDelete.add(SQL_BLANK_LINE);
			attrs.add(attribute.getAttributeName());
			
			jpaAttr.put(attribute.getAttributeName(), "String");
		}
		// delete Datagrid
		outputDelete.add("-- Delete DATAGRID");
		outputDelete.add(SQL_BLANK_LINE);
		outputDelete.add("DELETE FROM \"EAT\".\"M_DATAGRID\" WHERE DATAGRID_NAME = '"+gui.getGridName()+"';");
		outputDelete.add(SQL_BLANK_LINE);
		
		output.add("-- DATAGRID FIELDS SICS");
		output.add(SQL_BLANK_LINE);
		output.add(SQL_BLANK_LINE);
		
		outputDelete.add(SQL_BLANK_LINE);
		outputDelete.add("-- DELETE DATAGRID FIELD SICS");
		outputDelete.add(SQL_BLANK_LINE);
		for (String sic : sics.keySet()) {
			output.add(ParserUtils.generateSicQuery(sic, sics.get(sic)));
			output.add(SQL_BLANK_LINE);
			
			outputDelete.add("DELETE FROM \"EAT\".\"I_DICTIONARY\" WHERE SIC = '"+sic+"' and DOMAIN = '"+DOMAIN+"';");
			outputDelete.add(SQL_BLANK_LINE);
		}
		output.add(SQL_BLANK_LINE);
		
		// view creation
		outputViewCReation = ParserUtils.createGraniteView(attrs, gui);

		//JPA Model Generation
		SRMSModelGenerator.createClassModel(JPA_PACKAGE, gui.getJpaModelName(), jpaAttr);
	}
}
