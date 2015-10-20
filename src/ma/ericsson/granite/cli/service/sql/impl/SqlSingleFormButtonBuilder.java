package ma.ericsson.granite.cli.service.sql.impl;

import ma.ericsson.granite.cli.model.Button;
import ma.ericsson.granite.cli.model.Form;
import ma.ericsson.granite.cli.model.GUI;
import ma.ericsson.granite.cli.model.GUIOperation;
import ma.ericsson.granite.cli.util.ParserUtils;

import static ma.ericsson.granite.cli.util.ParserConstants.DOMAIN;
import static ma.ericsson.granite.cli.util.ParserConstants.SQL_BLANK_LINE;

public class SqlSingleFormButtonBuilder extends BaseSqlFormBuilder {

	@Override
	public void setQueries(Form form) {

		output.add("-- FORM BUTTONS");
		output.add(SQL_BLANK_LINE);
		output.add(SQL_BLANK_LINE);

		for (Button button : form.getButtonList()) {

			output.add("INSERT INTO M_FORM_FIELD "
					+ "(FORM_NAME, FIELD_NAME, FIELD_DESCRIPTION, FIELD_CAPTION, FIELD_SIC, FIELD_TYPE, FORM_FIELD_TYPE, PRESENTATION_INDEX, VISIBLE, DOMAIN, ON_CHANGE, WIDTH, HEIGHT, POS_X, POS_Y)"
					+ "VALUES ('" + form.getName() + "', '" + button.getName() + "', '" + button.getName() + "', '" + button.getName() + "', '"
					+ (button.getSic().trim().startsWith("btn.") ? button.getSic().trim() : "btn." + form.getSic() + "." + button.getSic()) + "', 0, 999, 100, 'Y', 'INWI', '"
					+ (button.getOnChange() != null && !button.getOnChange().equals("") ? button.getOnChange() + "();" : "") + "', " + button.getWidth() + ", "
					+ button.getHeight() + ", " + button.getxPosition() + ", " + button.getyPosition() + ");");
			output.add(SQL_BLANK_LINE);

		}
	}
}
