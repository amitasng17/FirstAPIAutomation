package api.endpoints;
import org.testng.annotations.Test;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import io.restassured.response.Response;

//we will implement methods which will perform CRUD(Create, Read, Update,Delete)operations
public class UserEndpointsUsingPropFile {
	
	//method created for getting urls from config.properties file
	static ResourceBundle getURL() {

		ResourceBundle propfile = ResourceBundle.getBundle("config");// Load properties file
		return propfile;
	}
	
public static Response CreateUser(User payload) {
	String post_url=getURL().getString("post_url");
	
	Response res=given()
	   .contentType(ContentType.JSON)
	   .accept(ContentType.JSON)
	   .body(payload)
	
	.when()
	   .post(post_url);
	
	return res;
}

public static Response ReadUser(String username) {
	String get_url=getURL().getString("get_url");
	Response res=given()
	   .pathParam("username",username)
	.when()
	   .get(get_url);
	return res;
}

public static Response UpdateUser(String username, User payload) {
	String update_url=getURL().getString("update_url");
	Response res=given()
			   .contentType(ContentType.JSON)
			   .accept(ContentType.JSON)
			   .pathParam("username", username)
			   .body(payload)
			.when()
			   .put(update_url);
			return res;
}

public static Response DeleteUser(String username) {
	String delete_url=getURL().getString("delete_url");
	Response res= given()
			   .pathParam("username", username)
			.when()
			   .delete(delete_url);
	        return res;
}

}
