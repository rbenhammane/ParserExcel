package ma.ericsson.granite.cli.service.sql.impl;

import ma.ericsson.granite.cli.model.Button;
import ma.ericsson.granite.cli.model.Combo;
import ma.ericsson.granite.cli.model.Form;
import ma.ericsson.granite.cli.util.ParserConstants;
import ma.ericsson.granite.cli.util.ParserUtils;

import static ma.ericsson.granite.cli.util.ParserConstants.*;

public class SqlSingleFormDeleteBuilder extends BaseSqlFormBuilder {

	@Override
	public void setQueries(Form form) {

		output.add("set escape '\\';");
		output.add(SQL_BLANK_LINE);
		output.add(SQL_BLANK_LINE);

		output.add("-- CLEANUP");
		output.add(SQL_BLANK_LINE);
		output.add(SQL_BLANK_LINE);

		output.add("DELETE FROM \"EAT\".\"M_FORM_FIELD\" WHERE FORM_NAME = '" + form.getName() + "';");
		output.add(SQL_BLANK_LINE);

		output.add("DELETE FROM \"EAT\".\"M_FORM_BUTTON\" WHERE FORM_NAME = '" + form.getName() + "';");
		output.add(SQL_BLANK_LINE);

		for (Combo combo : form.getComboList()) {
			output.add("DELETE FROM \"EAT\".\"M_COMBO\" WHERE COMBO_NAME = '" + combo.getName() + "';");
			output.add(SQL_BLANK_LINE);
		}

		output.add("DELETE FROM \"EAT\".\"M_FORM\" WHERE FORM_NAME = '" + form.getName() + "';");
		output.add(SQL_BLANK_LINE);

		output.add("DELETE FROM \"EAT\".\"I_DICTIONARY\" WHERE SIC LIKE 'field." + form.getSic() + ".%';");
		output.add(SQL_BLANK_LINE);

		output.add("DELETE FROM \"EAT\".\"I_DICTIONARY\" WHERE SIC LIKE 'btn." + form.getSic() + ".%';");
		output.add(SQL_BLANK_LINE);
	}
}
