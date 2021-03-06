package ma.ericsson.granite.cli.service.sql.impl;

import static ma.ericsson.granite.cli.util.ParserConstants.SQL_BLANK_LINE;
import ma.ericsson.granite.cli.model.Button;
import ma.ericsson.granite.cli.model.Form;

public class SqlSingleFormButtonBuilder extends BaseSqlFormBuilder {

	@Override
	public void setQueries(Form form) {

		output.add("-- FORM BUTTONS");
		output.add(SQL_BLANK_LINE);
		output.add(SQL_BLANK_LINE);

		int index = 1;
		for (Button button : form.getButtonList()) {

			if (button.getOperation() == null || button.getOperation().equals("")) {
				output.add("INSERT INTO M_FORM_FIELD " + "(FORM_NAME, FIELD_NAME, FIELD_DESCRIPTION, FIELD_CAPTION, FIELD_SIC, FIELD_TYPE, FORM_FIELD_TYPE, PRESENTATION_INDEX, VISIBLE, DOMAIN, ON_CHANGE, WIDTH, HEIGHT, POS_X, POS_Y)" + "VALUES ('" + form.getName() + "', '" + button.getName() + "', '" + button.getName() + "', '" + button.getName() + "', '" + (button.getSic().trim().startsWith("btn.") ? button.getSic().trim() : "btn." + form.getSic() + "." + button.getSic()) + "', 0, 999, 100, 'Y', 'INWI', '" + (button.getOnChange() != null && !button.getOnChange().equals("") ? button.getOnChange() + "();" : "") + "', " + button.getWidth() + ", " + button.getHeight() + ", " + button.getxPosition() + ", " + button.getyPosition() + ");");
			} else {
				output.add("INSERT INTO \"EAT\".\"M_FORM_BUTTON\"(BUTTON_NAME, FORM_NAME, BUTTON_LABEL, ACTION, FORM_BIND, PRESENTATION_INDEX, BUTTON_SIC) \n" + "VALUES ('" + button.getName() + "','" + form.getName() + "','" + button.getDescription() + "','javascript:" + button.getOperation() + "()', 'N', " + index++ + ", '" + (button.getSic().trim().startsWith("btn.") ? button.getSic().trim() : "btn." + form.getSic() + "." + button.getSic()) + "');");

				output.add(SQL_BLANK_LINE);

				output.add("INSERT INTO \"EAT\".\"I_DICTIONARY\" (ID, SIC, LANG_ID, MESSAGE, DOMAIN) VALUES (I_DICTIONARY_ID.NEXTVAL, '" + button.getSic() + "', 'FR', '" + button.getDescription() + "', 'INWI');");

				output.add(SQL_BLANK_LINE);
			}
			output.add(SQL_BLANK_LINE);

		}
	}
}
