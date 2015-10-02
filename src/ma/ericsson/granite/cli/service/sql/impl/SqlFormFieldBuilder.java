package ma.ericsson.granite.cli.service.sql.impl;

import static ma.ericsson.granite.cli.util.ParserConstants.*;
import ma.ericsson.granite.cli.model.GUI;
import ma.ericsson.granite.cli.model.GUIAttribute;

public class SqlFormFieldBuilder extends BaseSqlBuilder {

	@Override
	public void setQueries(GUI gui) {

		output.add("-- FORM FIELDS");
		output.add(SQL_BLANK_LINE);
		output.add(SQL_BLANK_LINE);

		for (GUIAttribute attribute : gui.getAttributes()) {
			output.add("INSERT INTO \"EAT\".\"M_FORM_FIELD\" (FORM_NAME, FIELD_NAME, FIELD_DESCRIPTION, FIELD_CAPTION, FIELD_SIC, MANDATORY, FIELD_TYPE, FORM_FIELD_TYPE, MIN_LENGTH, MAX_LENGTH, VISIBLE) VALUES ('"
					+ gui.getFormName()
					+ "', '"
					+ attribute.getAttributeName()
					+ "', '"
					+ attribute.getName().replaceAll("'", "''")
					+ "', '"
					+ attribute.getName().replaceAll("'", "''")
					+ "', 'field.inwi."
					+ attribute.getAttributeName()
					+ "', 'N', '1', '0', '0', '0', 'Y');");
			output.add(SQL_BLANK_LINE);
		}

		output.add(SQL_BLANK_LINE);
	}
}
