package ma.ericsson.granite.cli.service.sql.impl;

import static ma.ericsson.granite.cli.util.ParserConstants.*;
import ma.ericsson.granite.cli.model.GUI;
import ma.ericsson.granite.cli.model.GUIOperation;
import ma.ericsson.granite.cli.util.ParserUtils;

public class SqlFormButtonBuilder extends BaseSqlBuilder {

	@Override
	public void setQueries(GUI gui) {

		output.add("-- FORM BUTTONS");
		output.add(SQL_BLANK_LINE);
		output.add(SQL_BLANK_LINE);

		for (GUIOperation operation : gui.getOperations()) {
			output.add(ParserUtils.generateSicQuery(BUTTON_FORM_SIC_PREFIX + gui.getFormName() + "." + operation.getSicName(), operation.getName()));
			output.add(SQL_BLANK_LINE);
		}

		output.add(SQL_BLANK_LINE);
	}
}
