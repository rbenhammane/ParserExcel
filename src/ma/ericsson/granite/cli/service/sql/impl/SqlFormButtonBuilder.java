package ma.ericsson.granite.cli.service.sql.impl;

import static ma.ericsson.granite.cli.util.ParserConstants.*;
import ma.ericsson.granite.cli.model.GUI;
import ma.ericsson.granite.cli.model.GUIAttribute;
import ma.ericsson.granite.cli.model.GUIOperation;

public class SqlFormButtonBuilder extends BaseSqlBuilder {

	@Override
	public void setQueries(GUI gui) {

		output.add("-- FORM BUTTONS");
		output.add(SQL_BLANK_LINE);
		output.add(SQL_BLANK_LINE);

		for (GUIOperation operation : gui.getOperations()) {
			output.add("INSERT INTO \"EAT\".\"M_FORM_BUTTON\" (BUTTON_NAME, FORM_NAME, BUTTON_LABEL, ACTION, FORM_BIND, PRESENTATION_INDEX, BUTTON_SIC) VALUES ('"
					+ operation.getName()
					+ "', 'APDValidationRadio', 'Dowload APD', 'javascript:DowloadAPD()', 'N', '1', 'btn.inwi.sic.DowloadAPD');");
			output.add(SQL_BLANK_LINE);
		}

		output.add(SQL_BLANK_LINE);
	}
}
