package ma.ericsson.granite.cli.service;

public class ParserContext<O> {

	private O data;

	public O getData() {
		return data;
	}

	public void setData(O data) {
		this.data = data;
	}
}