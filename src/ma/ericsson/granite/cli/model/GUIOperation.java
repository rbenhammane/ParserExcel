package ma.ericsson.granite.cli.model;

import ma.ericsson.granite.cli.util.ParserConstants;
import ma.ericsson.utils.Utils;

public class GUIOperation {

	private String name;
	private String comment;
	private GUI gui;

	public GUIOperation(GUI gui) {
		this.gui = gui;
	}

	public String getName() {
		return name.replace("'", " ");
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
		return ParserConstants.BUTTON_FORM_SIC_PREFIX + gui.getFormName() + "." + getOperationName();
		// return Utils.clean(name, "");
	}

	public String getOperationName() {
		return Utils.clean(name.toLowerCase(), "");
	}

}
