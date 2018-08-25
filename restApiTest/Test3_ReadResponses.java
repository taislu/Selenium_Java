package restApiTest;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.io.InputStream;

public class Test3_ReadResponses {
	
	//@Test
	public void testGetResponseAsString() {
		
		String respAsString = get("http://services.groupkt.com/country/search?text=lands").asString();
		System.out.println("respAsString : " + respAsString);
	}
	
	//@Test
	public void testGetResponseAsInputString() {
		
		InputStream stream = get("http://services.groupkt.com/country/search?text=lands").asInputStream();
		System.out.println("Stream Length : " + stream.toString().length());
	}
	
	//@Test
	public void testGetResponseAsByteArray() {
		
		byte[] byteArray = get("http://services.groupkt.com/country/search?text=lands").asByteArray();
		System.out.println("byteArray.length : " + byteArray.length);
	}
	
	// extract details using path
	
	//@Test
	public void testExtractDetailsUsingPath() {
		
		String href = when().
						get("http://jsonplaceholder.typicode.com/photos/1").
					  then().
					    contentType(ContentType.JSON).
					    body("albumId", equalTo(1)).
					  extract().
					    path("url");
		System.out.println("href : " + href);
		
		when().get(href).then().statusCode(200);
	}
	
	//@Test
	public void testExtractPathInOneLine() {
		
		//type 1
		String href1= get("http://jsonplaceholder.typicode.com/photos/1").path("url");
		System.out.println("href1 : " + href1);
		when().get(href1).then().statusCode(200);
		
		//type 2
		String href2= get("http://jsonplaceholder.typicode.com/photos/1").andReturn().jsonPath().getString("url");
		System.out.println("href2 : " + href2);
		when().get(href1).then().statusCode(200);
		
	}
	
	@Test
	public void testExtractDetailsUsingResponse() {
		
		Response response = when().
								get("http://jsonplaceholder.typicode.com/photos/1").
							then().
							extract().
								response();
		
		System.out.println("Content Type : " + response.contentType());
		System.out.println("Href : " + response.path("url"));
		System.out.println("Status Code : " + response.statusCode());
	}

}
