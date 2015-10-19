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
	protected List<String> outputDelete;
	protected List<String> outputViewCreation;

	
	public List<List<String>> build(GUI gui) {
		List<List<String>> result = new ArrayList<List<String>>();
		output = new ArrayList<String>();
		outputDelete = new ArrayList<String>();
		outputViewCreation = new ArrayList<String>();
		
		setQueries(gui);

		output.add(SQL_BLANK_LINE);
		output.add(SQL_BLANK_LINE);
		output.add(SQL_BLANK_LINE);

		result.add(output);
		result.add(outputDelete);
		result.add(outputViewCreation);
		
		return result;
	}

	public abstract void setQueries(GUI gui);

}
