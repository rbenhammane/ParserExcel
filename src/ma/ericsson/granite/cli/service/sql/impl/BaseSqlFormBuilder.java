package ma.ericsson.granite.cli.service.sql.impl;

import ma.ericsson.granite.cli.model.Form;
import ma.ericsson.granite.cli.model.GUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static ma.ericsson.granite.cli.util.ParserConstants.SQL_BLANK_LINE;

public abstract class BaseSqlFormBuilder {

	@Autowired
	private ApplicationContext applicationContext;

	protected List<String> output;

	
	public List<String> build(Form form) {
		output = new ArrayList<String>();
		
		setQueries(form);

		output.add(SQL_BLANK_LINE);
		output.add(SQL_BLANK_LINE);
		
		return output;
	}

	public abstract void setQueries(Form form);

}
