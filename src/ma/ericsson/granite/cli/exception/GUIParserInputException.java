package ma.ericsson.granite.cli.exception;

import java.io.OutputStream;

@SuppressWarnings(value = "serial")
public class GUIParserInputException extends GUIParserException {

	private OutputStream out;

	public GUIParserInputException(OutputStream out) {
		super("Error validating GUIs");
		this.out = out;
	}

	public OutputStream getOutStream() {
		return out;
	}
}
