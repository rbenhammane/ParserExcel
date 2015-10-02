package ma.ericsson.service.gen;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ma.ericsson.utils.Utils;

import org.apache.commons.lang3.StringUtils;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.AnnotationSource;
import org.jboss.forge.roaster.model.source.FieldSource;
import org.jboss.forge.roaster.model.source.JavaClassSource;


public class SRMSModelGenerator {


	private final static String SOURCE_PATH = "D:\\workspace\\dev\\java\\workspaces\\eclipse_workspace_esioox\\EAT-Generator\\src-gen";

	public static void createClassModel(String pkgName, String classeName, Map<String, String> mapNameType) {

		final JavaClassSource javaClass = Roaster.create(JavaClassSource.class);
		classeName = StringUtils.capitalize(classeName.toLowerCase());
		javaClass.setPackage(pkgName).setName(classeName);
		// javaClass.addImport(Boolean.class);

		/*************/
		/** LONG ID **/
		/*************/
		String id = "rownum";
		javaClass.addProperty(Long.class, id);
		FieldSource fieldId = javaClass.getField(id);
		fieldId.addAnnotation(Id.class);
		AnnotationSource annotationId = fieldId.addAnnotation(Column.class);
		annotationId.setLiteralValue("name", id.toUpperCase());

		AnnotationSource annotationSource = fieldId.addAnnotation(GeneratedValue.class);// .addAnnotationValue("strategy", GenerationType.AUTO);
		javaClass.addImport(GenerationType.class);
		annotationSource.setLiteralValue("strategy", "GenerationType.AUTO");
		/*************/
		/*************/

		/** Other fields */
		for (String attrName : mapNameType.keySet()) {
			String attrType = mapNameType.get(attrName).toLowerCase();

			String attributName = Utils.normalize(attrName);
			javaClass.addProperty(attrType, attributName);
			FieldSource field = javaClass.getField(attributName);

			if (attrType.equals("Date")) {
				AnnotationSource annotation = field.addAnnotation(Temporal.class);
				// annotation.setEnumValue(TemporalType.TIMESTAMP);
				annotation.setEnumValue(TemporalType.DATE);
			}
			AnnotationSource annotation = field.addAnnotation(Column.class);
			annotation.setLiteralValue("name", attrName.toUpperCase());

		}

		/********************/
		/** @Table @Entity **/
		/********************/
		javaClass.addAnnotation(Entity.class);
		// javaClass.addImport(GenericJsonSerializer.class);

//		AnnotationSource jsonSerialize = javaClass.addAnnotation(JsonSerialize.class);
//		jsonSerialize.setLiteralValue("using", "GenericJsonSerializer.class");

		AnnotationSource annotationTable = javaClass.addAnnotation(Table.class);
		annotationTable.setStringValue("name", classeName.toUpperCase() + " TODO");

		AnnotationSource annotationNamedQuery = javaClass.addAnnotation(NamedQuery.class);
		annotationNamedQuery.setStringValue("name", "TODO");
		annotationNamedQuery.setStringValue("query", "TODO");

		/********************/
		/** PRINT CODE SRC **/
		/********************/
		System.out.println(javaClass);
		Utils.setFileContent(new File(SOURCE_PATH + "/" + classeName + ".java"), javaClass.toString());

	}


	public static void main(String[] args) {
		
		System.out.println(new File(".").getAbsolutePath());
		
//		Map<String, String> mapNameType = new HashMap<String, String>();
//		mapNameType.put("att1", "String");
//		mapNameType.put("att2", "Date");
//		createClassModel("model", "ClasseName", mapNameType);
	}

}
