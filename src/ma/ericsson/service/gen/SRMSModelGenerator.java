package ma.ericsson.service.gen;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ma.ericsson.granite.cli.model.GUI;
import ma.ericsson.granite.cli.model.GUIAttribute;
import ma.ericsson.utils.Utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.AnnotationSource;
import org.jboss.forge.roaster.model.source.FieldSource;
import org.jboss.forge.roaster.model.source.JavaClassSource;

public class SRMSModelGenerator {

	protected static final Log log = LogFactory.getLog(SRMSModelGenerator.class);

	private final static String PKG_NAME = "srms.acquisition.forms.inwi";

	private final static String SOURCE_PATH = "../SRMS/src-gen-model/srms/acquisition/forms/inwi/";

	public static void createClassModel(GUI gui) {

		String classeName = gui.getFormModelClass();
		Map<String, String> mapNameType;

		final JavaClassSource javaClass = Roaster.create(JavaClassSource.class);
		// classeName = StringUtils.capitalize(classeName.toLowerCase());
		javaClass.setPackage(PKG_NAME).setName(classeName);
		javaClass.addImport(java.util.Date.class);

		/*************/
		/** LONG ID **/
		/*************/
		String id = "ROW_NUM";
		javaClass.addProperty(Long.class, id);
		FieldSource fieldId = javaClass.getField(id);
		fieldId.addAnnotation(Id.class);
		AnnotationSource annotationId = fieldId.addAnnotation(Column.class);
		annotationId.setLiteralValue("name", "\"" + id.toUpperCase() + "\"");

		AnnotationSource annotationSource = fieldId.addAnnotation(GeneratedValue.class);// .addAnnotationValue("strategy", GenerationType.AUTO);
		javaClass.addImport(GenerationType.class);
		annotationSource.setLiteralValue("strategy", "GenerationType.AUTO");
		/*************/
		/*************/

		List<GUIAttribute> attributes = gui.getAttributes();
		for (GUIAttribute attr : attributes) {
			// for (String attrName : mapNameType.keySet()) {
			String attrName = attr.getAttributeName();
			String attrType = attr.getDataType().toLowerCase();

			// TODO in GUI.java side
			attrType = StringUtils.capitalize(attrType);
			attrName = StringUtils.uncapitalize(attrName);

			if (attrType.equals("Picklist")) {
				attrType = "String";
			}

			// String attributName = Utils.normalize(attrName);
			javaClass.addProperty(attrType, attrName);
			FieldSource field = javaClass.getField(attrName);

			if (attrType.equals("Date")) {
				AnnotationSource annotation = field.addAnnotation(Temporal.class);
				// annotation.setEnumValue(TemporalType.TIMESTAMP);
				annotation.setEnumValue(TemporalType.DATE);
			}
			AnnotationSource annotation = field.addAnnotation(Column.class);
			annotation.setLiteralValue("name", "\"" + attr.getColumnName().toUpperCase() + "\"");

		}

		/********************/
		/** @Table @Entity **/
		/********************/
		javaClass.addAnnotation(Entity.class);
		// javaClass.addImport(GenericJsonSerializer.class);

		// AnnotationSource jsonSerialize = javaClass.addAnnotation(JsonSerialize.class);
		// jsonSerialize.setLiteralValue("using", "GenericJsonSerializer.class");

		AnnotationSource annotationTable = javaClass.addAnnotation(Table.class);
		annotationTable.setStringValue("name", gui.getGraniteViewName());

		// AnnotationSource annotationNamedQuery = javaClass.addAnnotation(NamedQuery.class);
		// annotationNamedQuery.setStringValue("name", "TODO");
		// annotationNamedQuery.setStringValue("query", "TODO");

		/********************/
		/** PRINT CODE SRC **/
		/********************/
		// System.out.println(javaClass);
		Utils.setFileContent(new File(SOURCE_PATH + "/" + classeName + ".java"), javaClass.toString());
		System.out.println(classeName + ".java generated !");

	}

	public static void main(String[] args) {
		// Legal doc upload => DA/SRMSData.Acquisition Phone
	}

}
