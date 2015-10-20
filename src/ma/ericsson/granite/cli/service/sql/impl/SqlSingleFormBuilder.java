package ma.ericsson.granite.cli.service.sql.impl;

import ma.ericsson.granite.cli.model.Form;
import ma.ericsson.granite.cli.model.GUI;
import ma.ericsson.granite.cli.util.ParserConstants;
import ma.ericsson.granite.cli.util.ParserUtils;

import static ma.ericsson.granite.cli.util.ParserConstants.*;

public class SqlSingleFormBuilder extends BaseSqlFormBuilder {

    @Override
    public void setQueries(Form form) {

        output.add("-- FORM");
        output.add(SQL_BLANK_LINE);
        output.add(SQL_BLANK_LINE);

        output.add("INSERT INTO \"EAT\".\"M_FORM\" (FORM_NAME, FORM_DESCRIPTION, MANAGER_CLASS, ENTITY_MANAGER, CLASS_NAME, PK_NAME, DOMAIN, FORM_LAYOUT) \n" +
                "VALUES ('" + form.getName() + "', '" + form.getDescription() + "', '" + form.getManager() + "', 'GRANITE', '" + form.getModel() + "', '" + form.getPk() + "', 'INWI', '2');");

        output.add(SQL_BLANK_LINE);
    }
}
