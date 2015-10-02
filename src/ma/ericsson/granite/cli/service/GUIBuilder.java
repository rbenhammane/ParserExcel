package ma.ericsson.granite.cli.service;

public interface GUIBuilder<I, O> {

	O build(I data);
}
