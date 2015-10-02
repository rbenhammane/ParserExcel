package ma.ericsson.granite.cli.service.sql.impl;

import static ma.ericsson.granite.cli.util.ParserConstants.*;
import ma.ericsson.granite.cli.model.GUI;

public class SqlFormBuilder extends BaseSqlBuilder {

	@Override
	public void setQueries(GUI gui) {

		output.add("-- FORM");
		output.add(SQL_BLANK_LINE);
		output.add(SQL_BLANK_LINE);

		output.add("INSERT INTO \"EAT\".\"M_FORM\" (FORM_NAME, FORM_DESCRIPTION, MANAGER_CLASS, ENTITY_MANAGER, CLASS_NAME, PK_NAME, DOMAIN, FORM_LAYOUT) VALUES ('"
				+ gui.getFormName()
				+ "', '"
				+ gui.getName()
				+ "', 'srms.acquisition.forms.inwi."
				+ gui.getFormManagerClass()
				+ "FormService', '"
				+ ENTITY_MANAGER
				+ "', 'model."
				+ gui.getFormModelClass()
				+ "', 'rownum', '" + DOMAIN + "', '2');");

		output.add(SQL_BLANK_LINE);
	}
}
