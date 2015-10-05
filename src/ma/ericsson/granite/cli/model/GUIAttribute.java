package ma.ericsson.granite.cli.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GUIAttribute {

	private String name;
	private String comment;
	private String mapping;
	private String dataType;
	private String access;

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
		return mapping;
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
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}

	public String getGroupName() {
		return mapping.substring(mapping.indexOf('/') + 1, mapping.indexOf('.'));
	}

	public String getAttributeName() {

		String str = mapping.substring(mapping.indexOf('.') + 1);

		Matcher matcher = Pattern.compile(" +(.)").matcher(str);

		while (matcher.find()) {
			str = str.replaceFirst(" +(.)", matcher.group(1).toUpperCase());
		}

		str = str.replaceAll("é", "e")//
				.replaceAll("è", "e")//
				.replaceAll("\\(", "")//
				.replaceAll("\\)", "")//
				.replaceAll("'", "")//
				.replaceAll("\\.", "_")//
				.replaceAll("/", "_");
		return str;
	}

	public String getFormFieldName() {
		return mapping.substring(name.indexOf('.') + 1);
	}
}
