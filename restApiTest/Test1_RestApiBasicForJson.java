package restApiTest;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class Test1_RestApiBasicForJson {
	
	// simply checking the status code 
	
	//@Test
	public void testStatusCode() {
		
		given().
			get("http://jsonplaceholder.typicode.com/posts/3").
		then().
		statusCode(200);
		
	}
	
	// verify status code and log response
	
	//@Test
	public void testLogging() {
		
		given().
			get("http://services.groupkt.com/state/get/USA/all").
		then().
			statusCode(200).
			log().all();
	}
	
	// verify content using org.hamcrest.Matchers equalTo method
	
	//@Test
	public void testEqualToMethod() {
		
		given().
			get("http://services.groupkt.com/state/get/USA/CA").
		then().
			body("RestResponse.result.name", equalTo("California"));
		
	}
	
	// verify multiple content using org.hamcrest.Matchers hasItems method
	
	//@Test
	public void testHasItemsMethod() {
		
		given().
			get("http://services.groupkt.com/state/get/USA/all").
		then().
			body("RestResponse.result.name", hasItems("California", "Massachusetts"));
	}
	
	// verify parameter can be set
	
	//@Test
	public void testParameters() {
		
		given().
			param("text", "California").
		when().
			get("http://services.groupkt.com/state/search/USA").
		then().
			statusCode(200).
			log().all();
	}

}
