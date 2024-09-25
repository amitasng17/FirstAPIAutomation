package api.endpoints;
import org.testng.annotations.Test;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;

//we will implement methods which will perform CRUD(Create, Read, Update,Delete)operations
public class UserEndpoints {
	
	
public static Response CreateUser(User payload) {
	
	Response res=given()
	   .contentType(ContentType.JSON)
	   .accept(ContentType.JSON)
	   .body(payload)
	
	.when()
	   .post(Routes.post_url);
	
	return res;
}

public static Response ReadUser(String username) {
	
	Response res=given()
	   .pathParam("username",username)
	.when()
	   .get(Routes.get_url);
	return res;
}

public static Response UpdateUser(String username, User payload) {
	
	Response res=given()
			   .contentType(ContentType.JSON)
			   .accept(ContentType.JSON)
			   .pathParam("username", username)
			   .body(payload)
			.when()
			   .put(Routes.update_url);
			return res;
}

public static Response DeleteUser(String username) {
	
	Response res= given()
			   .pathParam("username", username)
			.when()
			   .delete(Routes.delete_url);
	        return res;
}

}
