package ma.ericsson.granite.cli.service.sql.impl;

import ma.ericsson.granite.cli.model.Field;
import ma.ericsson.granite.cli.model.Form;
import ma.ericsson.granite.cli.model.GUI;
import ma.ericsson.granite.cli.model.GUIAttribute;
import ma.ericsson.granite.cli.util.ParserUtils;

import java.util.HashMap;
import java.util.Map;

import static ma.ericsson.granite.cli.util.ParserConstants.*;

public class SqlSingleFormFieldBuilder extends BaseSqlFormBuilder {

    private static final int FIELD_WIDTH = 140;

    @Override
    public void setQueries(Form form) {

        output.add("-- FORM FIELDS SICS");
        output.add(SQL_BLANK_LINE);
        output.add(SQL_BLANK_LINE);

        int index = 1;

        for (Field field : form.getFieldList()) {

            if (field.isVisible()) {
                output.add("INSERT INTO \"EAT\".\"M_FORM_FIELD\"\n" +
                        "(FORM_NAME, FIELD_NAME, FIELD_DESCRIPTION, FIELD_CAPTION, FIELD_SIC, MANDATORY, FIELD_TYPE, FORM_FIELD_TYPE, PRESENTATION_INDEX, VISIBLE, POS_X, POS_Y, DOMAIN)\n" +
                        "VALUES\n" +
                        "('" + form.getName() + "', '" + field.getName() + "Label', '" + field.getDescription().replace("'", "\\\\''") + "', '" + field.getName() + "', '"
                        + (field.getSic().trim().startsWith("field") ? field.getSic() : "field." + form.getSic() + "." + field.getSic()) + "', '" + (field.isMandatory() ? "Y" : "N") + "', 0, 99, "
                        + index + ", 'Y', " + field.getxPosition() + ", " + (field.getyPosition() - 10) + ", 'INWI');");
                output.add(SQL_BLANK_LINE);
            }

            output.add("INSERT INTO \"EAT\".\"M_FORM_FIELD\"\n" +
                    "(FORM_NAME, FIELD_NAME, FIELD_DESCRIPTION, FIELD_CAPTION, FIELD_SIC, MANDATORY, FIELD_TYPE, FORM_FIELD_TYPE, MIN_LENGTH, MAX_LENGTH, PK, PRESENTATION_INDEX, VISIBLE, READ_ONLY, UPDATE_PRIVILEGE, VIEW_PRIVILEGE, DOMAIN, POS_X, POS_Y, WIDTH, HEIGHT)\n" +
                    "VALUES\n" +
                    "('" + form.getName() + "', '" + field.getName() + "', '" + field.getDescription().replace("'", "\\\\''") + "', '" + field.getName() + "', '"
                    + (field.getSic().trim().startsWith("field") ? field.getSic() : "field." + form.getSic() + "." + field.getSic()) + "', '" + (field.isMandatory() ? "Y" : "N") + "', "
                    + field.getType() + ", " + field.getFormType() + ", 0, 0, '" + (field.isPk() ? "Y" : "N") + "', " + (50 + index) + ", '" + (field.isVisible() ? "Y" : "H") + "', '"
                    + (field.isReadOnly() ? "Y" : "N") + "', '*', '*', 'INWI', " + (field.getxPosition() + FIELD_WIDTH) + ", " + field.getyPosition() + ", "
                    + (field.getWidth() != null ? field.getWidth() : FIELD_WIDTH) + ", " + (field.getHeight() != null ? field.getHeight() : "20") + ");\n");
            output.add(SQL_BLANK_LINE);
            index++;
        }

        output.add(SQL_BLANK_LINE);
        output.add("-- FORM FIELDS SICS");
        output.add(SQL_BLANK_LINE);
        output.add(SQL_BLANK_LINE);

        for (Field field : form.getFieldList()) {

            if (!field.getSic().trim().equals("field.empty")) {
                output.add("INSERT INTO \"EAT\".\"I_DICTIONARY\" (ID, SIC, LANG_ID, MESSAGE, DOMAIN) VALUES (I_DICTIONARY_ID.NEXTVAL, '"
                        + (field.getSic().trim().startsWith("field") ? field.getSic() : "field." + form.getSic() + "." + field.getSic()) + "', 'FR', '"
                        + field.getDescription().replace("'", "\\\\''") + "', 'INWI');");
                output.add(SQL_BLANK_LINE);
            }
        }
    }
}
