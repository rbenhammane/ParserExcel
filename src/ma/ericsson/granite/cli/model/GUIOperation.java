package ma.ericsson.granite.cli.model;

import ma.ericsson.utils.Utils;

public class GUIOperation {

	private String name;
	private String comment;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getSicName() {
		return Utils.clean(name, "");
	}

	public String getOperationName() {
		return Utils.clean(name.toLowerCase(), "");
	}

}
