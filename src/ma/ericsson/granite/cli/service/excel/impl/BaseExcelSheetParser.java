package ma.ericsson.granite.cli.service.excel.impl;

import ma.ericsson.granite.cli.exception.ExcelSheetException;
import ma.ericsson.granite.cli.model.GUI;
import ma.ericsson.granite.cli.service.excel.ExcelParserContext;
import ma.ericsson.granite.cli.service.excel.ExcelSheetParser;
import ma.ericsson.granite.cli.util.ParserValidator;

import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public abstract class BaseExcelSheetParser implements ExcelSheetParser<Sheet, GUI> {

	@Autowired
	private ApplicationContext applicationContext;

	protected boolean error = false;
	protected ParserValidator validator;
	protected GUI output;
	protected ExcelParserContext context;
	protected int topLeftCellRow;
	protected int topLeftCellCol;

	public GUI parse(Sheet sheet) throws ExcelSheetException {
		parseSheet(sheet);
		return error ? null : output;
	}

	public abstract void parseSheet(Sheet sheet) throws ExcelSheetException;

	public void setValidator(ParserValidator validator) {
		this.validator = validator;
	}

	public void setOutput(GUI output) {
		this.output = output;
	}

	public void setContext(ExcelParserContext context) {
		this.context = context;
	}

	public void setRow(int topLeftCellRow) {
		this.topLeftCellRow = topLeftCellRow;
	}

	public void setCol(int topLeftCellCol) {
		this.topLeftCellCol = topLeftCellCol;
	}

	@SuppressWarnings("unchecked")
	protected <T> T getList() {
		return (T) applicationContext.getBean("list");
	}

	@SuppressWarnings("unchecked")
	protected <T> T getSet() {
		return (T) applicationContext.getBean("set");
	}

	@SuppressWarnings("unchecked")
	protected <T> T getMap() {
		return (T) applicationContext.getBean("map");
	}
}
