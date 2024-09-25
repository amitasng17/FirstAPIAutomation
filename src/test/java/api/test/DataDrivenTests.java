package api.test;
import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utlities.ExcelDataProvider;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;

public class DataDrivenTests {
	

@Test(priority=1,dataProvider="Data",dataProviderClass=ExcelDataProvider.class)
public void testPostUser(String id,String userName,String fName,String lName,String eMail, String pwd,String phone) {
	User payload=new User();
	payload.setId(Integer.parseInt(id));
	payload.setUsername(userName);
	payload.setFirstName(fName);
	payload.setLastName(lName);
	payload.setEmail(eMail);
	payload.setPassword(pwd);
	payload.setPhone(phone);
	
	Response res=UserEndpoints.CreateUser(payload);
	Assert.assertEquals(res.getStatusCode(),200);
}

@Test(priority=2,dataProvider="Usernames",dataProviderClass=ExcelDataProvider.class)
public void testGetUser(String userName) {
	
	Response res=UserEndpoints.ReadUser(userName);
	Assert.assertEquals(res.getStatusCode(),200);
}

@Test(priority=3,dataProvider="Usernames",dataProviderClass=ExcelDataProvider.class)
public void testDeleteUser(String userName) {
	
	Response res=UserEndpoints.DeleteUser(userName);
	Assert.assertEquals(res.getStatusCode(),200);
}
}
