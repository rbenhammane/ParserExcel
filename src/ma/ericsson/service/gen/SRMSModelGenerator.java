package ma.ericsson.service.gen;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.*;

import ma.ericsson.granite.cli.model.Field;
import ma.ericsson.granite.cli.model.Form;
import ma.ericsson.granite.cli.model.GUI;
import ma.ericsson.granite.cli.model.GUIAttribute;
import ma.ericsson.granite.cli.util.ParserConstants;
import ma.ericsson.utils.Utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.AnnotationSource;
import org.jboss.forge.roaster.model.source.FieldSource;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.PropertySource;

public class SRMSModelGenerator {

	protected static final Log log = LogFactory.getLog(SRMSModelGenerator.class);

	private static final Map<String, String> collumns = new HashMap<>();
	static {
		collumns.put("nomSiteInstId", "NOM_SITE_INST_ID");
		collumns.put("nomSiteHumId", "NOM_SITE_HUM_ID");
		collumns.put("siteInstId", "SITE_INST_ID");
		collumns.put("ngoSrmsInstId", "NGO_SRMS_INST_ID");
	}

	// private final static String PKG_NAME = "srms.acquisition.forms.inwi";

	public static void createClassModel(Form form) {

		String classeName = form.getModel();

		final JavaClassSource javaClass = Roaster.create(JavaClassSource.class);
		javaClass.setPackage(ParserConstants.MODEL_PACKAGE).setName(classeName.substring(classeName.lastIndexOf(".") + 1));
		javaClass.addImport(java.util.Date.class);

		/*************/
		/*************/

		List<Field> fields = form.getFieldList();
		for (Field field : fields) {
			if (!field.getGroup().equals("ATTACHEMENT")) {
				String attrName = field.getName();
				String attrType = "String";
				addProperty(javaClass, attrName, attrType, field.isPk(), field.isReadOnly());
			}
		}

		/********************/
		/** @Table @Entity **/
		/********************/
		javaClass.addAnnotation(Entity.class);
		javaClass.addInterface(Serializable.class);

		AnnotationSource annotationTable = javaClass.addAnnotation(Table.class);
		annotationTable.setStringValue("name", "V_SRMS_ACQUISITION_SELECTED_INWI");

		/********************/
		/** PRINT CODE SRC **/
		/********************/
		// System.out.println(javaClass);
		Utils.setFileContent(new File(ParserConstants.SOURCE_PATH_MODEL + "/" + classeName + ".java"), javaClass.toString());
		System.out.println(classeName + ".java generated !");

	}

	private static void addProperty(JavaClassSource javaClass, String field, String type, boolean isPk, boolean isReadOnly) {

		String column = collumns.containsKey(field) ? collumns.get(field) : "TODO";

		PropertySource propertySource = javaClass.addProperty(type, field);

		FieldSource fieldSource = propertySource.getField();

		if (collumns.containsKey(field)) {
			if (isPk) {
				fieldSource.addAnnotation(Id.class);
			}
			fieldSource.addAnnotation(Column.class).setLiteralValue("name", "\"" + collumns.get(field) + "\"");
		} else if (isReadOnly) {
			fieldSource.addAnnotation(Column.class).setLiteralValue("name", "\"" + column + "\"");
		} else {
			fieldSource.addAnnotation(Transient.class);
		}
	}

	public static void main(String[] args) {
		// Legal doc upload => DA/SRMSData.Acquisition Phone
	}

}
