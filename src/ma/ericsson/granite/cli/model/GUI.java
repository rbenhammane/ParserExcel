package ma.ericsson.granite.cli.model;

import java.util.ArrayList;
import java.util.List;

import ma.ericsson.utils.Utils;

import org.apache.commons.lang3.StringUtils;

public class GUI {

	private String name;
	private List<GUIAttribute> attributes = new ArrayList<>();
	private List<GUIOperation> operations = new ArrayList<GUIOperation>();

	public String getGraniteViewName() {
		return "V_" + Utils.clean(name, "_").toUpperCase();
		// Matcher matcher = Pattern.compile(" +(.)").matcher(name);
		//
		// while (matcher.find()) {
		// name = name.replaceFirst(" +(.)", matcher.group(1).toUpperCase());
		// }
		// String newName = name;
		// newName = "V_" + name.replaceAll("é", "e").replaceAll("è", "e").replaceAll("'", "").replaceAll("\\.", "_").replaceAll(" ", "");
		// return newName;
	}

	public String getJpaModelName() {
		return name;
	}

	public String getServiceName() {
		return name + "FormService";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<GUIAttribute> getAttributes() {
		// if (attributes.isEmpty()) {
		// GUIAttribute guiAttribute = new GUIAttribute();
		// guiAttribute.setName("rownum");
		// guiAttribute.setDataType("long");
		// attributes.add(guiAttribute);
		// }
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
		// return name.replace(" ", "");
		return StringUtils.capitalize(Utils.clean(name,"")) + "Form";
	}

	public String getFormManagerClass() {
		return name.replace(" ", "");
	}

	public String getFormModelClass() {
		return StringUtils.capitalize(Utils.clean(name,""));
	}

	public String getGridName() {
		// return name.replace(" ", "");
		return StringUtils.capitalize(Utils.clean(name,""));
	}

	public String getJSPName() {
		return (Utils.clean(name,"_") + ".jsp").toLowerCase();
	}
}
