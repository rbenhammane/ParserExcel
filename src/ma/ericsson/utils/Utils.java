package ma.ericsson.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

	public static String normalize(String name) {
		return Normalizer.normalize(name.toLowerCase(), Form.NFD) //
				.replaceAll("\\p{InCombiningDiacriticalMarks}+", "")//
				.replaceAll("[^\\p{Alnum}]+", "-");//
	}

	public static void setFileContent(File file, String content) {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
			out.println(content);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getFileContent(String path) throws IOException {
		String content = "";
		File file = new File(path);

		FileInputStream fin = new FileInputStream(file);
		BufferedReader myInput = new BufferedReader(new InputStreamReader(fin, "UTF-8"));
		String tmp = null;
		while ((tmp = myInput.readLine()) != null) {
			content += tmp + "\n";
		}
		fin.close();
		myInput.close();
		return content;
	}

	public static String upperCaseFirst(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	public static String clean(String inStr, String sep) {
		String outStr = inStr.substring(inStr.indexOf('.') + 1);

		Matcher matcher = Pattern.compile(" +(.)").matcher(outStr);

		while (matcher.find()) {
			outStr = outStr.replaceFirst(" +(.)", sep + matcher.group(1).toUpperCase());
		}

		outStr = cleanSpecialChar(outStr);
		return outStr;
	}

	public static String cleanSpecialChar(String outStr) {
		return outStr.trim()//
				.replaceAll("é", "e")//
				.replaceAll("è", "e")//
				.replaceAll("\\(", "")//
				.replaceAll("\\)", "")//
				.replaceAll(" ", "_")//
				.replaceAll("\\.", "_")//
				.replaceAll("-", "_")//
				.replaceAll("'", "")//
				.replaceAll("\\.", "_")//
				.replaceAll("/", "_")//
				.replaceAll("__", "_");
	}

}
