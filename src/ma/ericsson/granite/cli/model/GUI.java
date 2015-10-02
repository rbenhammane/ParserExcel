package ma.ericsson.granite.cli.model;

import java.util.ArrayList;
import java.util.List;

public class GUI {

	private String name;
	private List<GUIAttribute> attributes = new ArrayList<>();
	private List<GUIOperation> operations = new ArrayList<GUIOperation>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<GUIAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<GUIAttribute> attributes) {
		this.attributes = attributes;
	}

	public List<GUIOperation> getOperations() {
		return operations;
	}

	public void setOperations(List<GUIOperation> operations) {
		this.operations = operations;
	}

	public String getFormName() {
		return name.replace(" ", "");
	}

	public String getFormManagerClass() {
		return name.replace(" ", "");
	}

	public String getFormModelClass() {
		return name.replace(" ", "");
	}

	public String getGridName() {
		return name;
	}

	public String getJSPName() {
		return name.replaceAll(" ", "_").toLowerCase();
	}
}
