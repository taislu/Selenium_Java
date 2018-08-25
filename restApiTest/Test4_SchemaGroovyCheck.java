package restApiTest;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.util.List;

public class Test4_SchemaGroovyCheck {
	
	//@Test
	public void testContentType() {
		
		given().
			get("http://services.groupkt.com/country/get/iso3code/USA").
		then().
			statusCode(200).
			contentType(ContentType.JSON);
			//ContentType HTML, XML
	}
	
	//@Test
	public void testSchema() {
		
		given().
			get("http://services.groupkt.com/country/get/iso3code/USA").
		then().
			assertThat().body( JsonSchemaValidator.matchesJsonSchemaInClasspath("?.json") );
	}
	
	//@Test
	public void testPresenceOfElements() {
		
		given().
			get("http://services.groupkt.com/country/search?text=lands").
		then().
			body("RestResponse.result.name", hasItems("Cayman Islands", "Cook Islands")).log().all();
	}
	
	//@Test
	public void testLengthOfResponse() {
		
		given().
			get("http://services.groupkt.com/country/search?text=islands").
		then().
			body("RestResponse.result.alpha3_code*.length().sum()", greaterThan(10));
	}
	
	//@Test
	public void testGetResponseAsList() {
		
		Response response = get("http://services.groupkt.com/country/search?text=lands");
		
		List<String> l = response.jsonPath().getList("RestResponse.result.name");
		
		System.out.println("List size : "+ l.size());
		
		for(String country : l) {
			System.out.println("country : " + country);
			if(country.equals("Solomon Islands")) {
				break;
			}
		}
		
	}
	
	@Test
	public void testConditionsOnList() {
		
		Response response = get("http://services.groupkt.com/country/search?text=lands");
		List<String> list = 
				response.jsonPath().getList("RestResponse.result.findAll { it.name.length() > 20 }.name");
		System.out.println(list);
	}
	
	

}
