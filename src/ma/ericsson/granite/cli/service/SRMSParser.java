package ma.ericsson.granite.cli.service;

import ma.ericsson.granite.cli.exception.GUIParserException;

public interface SRMSParser<I, O> {

	O parseGUIs(I input) throws GUIParserException;
}
