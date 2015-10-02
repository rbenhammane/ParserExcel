package ma.ericsson.granite.cli.service.sql.impl;

import static ma.ericsson.granite.cli.util.ParserConstants.SQL_BLANK_LINE;

import java.util.ArrayList;
import java.util.List;

import ma.ericsson.granite.cli.model.GUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public abstract class BaseSqlBuilder {

	@Autowired
	private ApplicationContext applicationContext;

	protected List<String> output;

	public List<String> build(GUI gui) {
		
		output = new ArrayList<String>();

		setQueries(gui);

		output.add(SQL_BLANK_LINE);
		output.add(SQL_BLANK_LINE);
		output.add(SQL_BLANK_LINE);

		return output;
	}

	public abstract void setQueries(GUI gui);

}
