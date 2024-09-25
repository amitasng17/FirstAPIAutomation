package api.test;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.endpoints.UserEndpointsUsingPropFile;
import api.payload.User;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
//Similarly create more classes for Store and Pet modules

public class UserTestUsingPropFile {
	Faker faker;
	User userpayload;
	
	
@BeforeClass
public void Datasetup() {
	//generating data using POJO
	faker = new Faker();
	userpayload = new User();
	userpayload.setEmail(faker.internet().emailAddress());
	userpayload.setId(faker.idNumber().hashCode());
	userpayload.setFirstName(faker.name().firstName());
	userpayload.setLastName(faker.name().lastName());
	userpayload.setPassword(faker.internet().password(5, 10));
	userpayload.setPhone(faker.phoneNumber().cellPhone());
	userpayload.setUsername(faker.name().username());
}

@Test(priority=1)
public void testpostuser() {
	
	Response res=UserEndpointsUsingPropFile.CreateUser(userpayload);
	res.then().log().all();
	Assert.assertEquals(res.getStatusCode(),200);
}

@Test(priority=2)
public void testgetuserbyname() {
	
	Response res=UserEndpointsUsingPropFile.ReadUser(this.userpayload.getUsername());
	res.then().log().all();
	System.out.println(res.asPrettyString());
	Assert.assertEquals(res.getStatusCode(),200);
}

@Test(priority=3)
public void testupdateuserbyname() {
	this.userpayload.setFirstName("Amita");
	Response res=UserEndpointsUsingPropFile.UpdateUser(this.userpayload.getUsername(), userpayload);
	res.then().log().body();
	System.out.println(res.asPrettyString());
	Assert.assertEquals(res.getStatusCode(),200);
	
	//Checking if the data is updated
	Response resafterupdate=UserEndpointsUsingPropFile.ReadUser(this.userpayload.getUsername());
	Assert.assertEquals(resafterupdate.getStatusCode(),200);
}

@Test(priority=4)
public void testdeleteuserbyname() {
	
	Response res=UserEndpointsUsingPropFile.DeleteUser(this.userpayload.getUsername());
	res.then().log().all();
	Assert.assertEquals(res.getStatusCode(),200);
}

}
