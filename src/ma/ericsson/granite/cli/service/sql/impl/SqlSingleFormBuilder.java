package ma.ericsson.granite.cli.service.sql.impl;

import ma.ericsson.granite.cli.model.Form;
import ma.ericsson.granite.cli.model.GUI;
import ma.ericsson.granite.cli.util.ParserConstants;
import ma.ericsson.granite.cli.util.ParserUtils;

import static ma.ericsson.granite.cli.util.ParserConstants.*;

public class SqlSingleFormBuilder extends BaseSqlFormBuilder {

	@Override
	public void setQueries(Form form) {

		// Map<String, String> sics = new HashMap<String, String>();
		output.add("-- FORM");
		output.add(SQL_BLANK_LINE);
		output.add(SQL_BLANK_LINE);

		output.add("INSERT INTO \"EAT\".\"M_FORM\" (FORM_NAME, FORM_DESCRIPTION, MANAGER_CLASS, ENTITY_MANAGER, CLASS_NAME, PK_NAME, DOMAIN, FORM_LAYOUT, COLUMNS) VALUES ('"
				+ form.getName() + "', '"
				+ form.getName() + "', '" + ParserConstants.SERVICE_PACKAGE+"."
				+ form.getManager() + "FormService', '" + ENTITY_MANAGER + "', '"+ParserConstants.MODEL_PACKAGE+"."
				+ form.getModel() + "', 'ROW_NUM', '" + DOMAIN + "', '1', '3');");

		/** Delete script */
		outputDelete.add("DELETE FROM \"EAT\".\"M_FORM\" WHERE FORM_NAME = '" + form.getName() + "';");
		outputDelete.add(SQL_BLANK_LINE);

		output.add(SQL_BLANK_LINE);
		String sic = FORM_SIC_PREFIX + form.getName().toLowerCase();
		String sicVal = form.getName().replaceAll("'", " ");
		output.add(ParserUtils.generateSicQuery(sic, sicVal));
		output.add(SQL_BLANK_LINE);

		output.add("-- FORM SICS");
		output.add(SQL_BLANK_LINE);
		output.add(SQL_BLANK_LINE);

		outputDelete.add(SQL_BLANK_LINE);
		outputDelete.add("-- DELETE FORM SICS");
		outputDelete.add(SQL_BLANK_LINE);
//		for (String sic : sics.keySet()) {
//			output.add(ParserUtils.generateSicQuery(sic, sics.get(sic)));
//		}
		outputDelete.add("DELETE FROM \"EAT\".\"I_DICTIONARY\" WHERE SIC = '" + sic + "' and DOMAIN = '" + DOMAIN + "';");
		outputDelete.add(SQL_BLANK_LINE);

	}
}
