package ma.ericsson.service.gen;

import java.io.IOException;

import ma.ericsson.utils.Utils;

public class GraniteUserGenerator {

	public static void main(String[] args) throws IOException {
		String queryTemplate = Utils.getFileContent("./sql/drop_create_user.txt");
		
		String users = Utils.getFileContent("./sql/list_granite_user.txt");
		for(String user : users.split("\n")){
			System.out.println(queryTemplate.replace("the_user", user));
		}
//		System.out.println(users);
	}

}
