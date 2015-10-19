package ma.ericsson.granite.cli.model;

import ma.ericsson.utils.Utils;

public class GUIAttribute {

	private String name;
	private String comment;
	private String mapping;
	private String dataType;
	private String access;
	private String otherInfo;

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

	public String getMapping() {
		return mapping == null ? "" : mapping;
	}

	public void setMapping(String mapping) {
		this.mapping = mapping;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getAccess() {
		return access == null ? "" : access;
	}

	public void setAccess(String access) {
		this.access = access;
	}

	public String getGroupName() {
		return getMapping().substring(getMapping().indexOf('/') + 1, mapping.indexOf('.'));
	}

	public String getAttributeName() {
		return Utils.clean(getName().toLowerCase(), "");
		
		// return Utils.clean(getMapping().toLowerCase(), "");
		
		
		// String str = mapping.substring(mapping.indexOf('.') + 1);
		//
		// Matcher matcher = Pattern.compile(" +(.)").matcher(str);
		//
		// while (matcher.find()) {
		// str = str.replaceFirst(" +(.)", matcher.group(1).toUpperCase());
		// }
		//
		// str = str.replaceAll("é", "e")//
		// .replaceAll("è", "e")//
		// .replaceAll("\\(", "")//
		// .replaceAll("\\)", "")//
		// .replaceAll("'", "")//
		// .replaceAll("\\.", "_")//
		// .replaceAll("/", "_");
		// return str;
	}

	public String getFormFieldName() {
		return mapping.substring(name.indexOf('.') + 1);
	}

	public String getColumnName() {
		return Utils.clean(mapping, "_");
	}

	public String getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}

	public String getComboName() {
		if (getDataType() == null || !getDataType().toLowerCase().equals("picklist")) {
			return "null";
		}

		if (otherInfo.contains("COMBO_NAME")) {
			return "'" + otherInfo.split("COMBO_NAME=")[1].split(";")[0] + "'";
		}
		return "'XLS DEF NOT FOUND'";
	}

	public boolean getIsHidden() {
		return otherInfo.toLowerCase().contains("hidden");
	}

	public String getFormFieldType() {
		if (getDataType() == null) {
			return "0";
		}
		if (getDataType().toLowerCase().equals("date")) {
			return "4";
		} else if (getDataType().toLowerCase().equals("textarea")) {
			return "10";
		} else if (getDataType().toLowerCase().equals("picklist")) {
			return "13";
		} else {
			return "0";
		}

	}

	public String getFieldType() {
		// return getDataType().toLowerCase().equals("picklist") ? "13" : "1";
		return "0";
	}
}
