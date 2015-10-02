package ma.ericsson.granite.cli.service.excel;

import ma.ericsson.granite.cli.exception.ExcelSheetException;

public interface ExcelSheetParser<I, O> {

	O parse(I input) throws ExcelSheetException;

}