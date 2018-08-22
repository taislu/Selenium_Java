package restApiTest;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
//import static org.hamcrest.Matchers.equalTo;
//import static org.hamcrest.Matchers.hasItems;
//import static org.hamcrest.Matchers.hasXPath;
import static org.hamcrest.Matchers.*;

public class Test1_RestApiBasicForXml {
	
	// test xml response for single body content
	
	//@Test
	public void testSingleContent() {
		
		given().
			get("http://www.thomas-bayer.com/sqlrest/CUSTOMER/10/").
		then().
			body("CUSTOMER.ID", equalTo("10")).
			log().all();
	}
	
	//@Test
	public void testMultipleContent() {
		
		given().
			get("http://www.thomas-bayer.com/sqlrest/CUSTOMER/10/").
		then().
			body("CUSTOMER.ID", equalTo("10")).
			body("CUSTOMER.FIRSTNAME", equalTo("Sue")).
			body("CUSTOMER.LASTNAME", equalTo("Fuller")).
			body("CUSTOMER.STREET", equalTo("135 Upland Pl.")).
			body("CUSTOMER.CITY", equalTo("Dallas"));
	}
	
	//@Test
	public void testWithXpath() {
		
		given().
			get("http://www.thomas-bayer.com/sqlrest/CUSTOMER/10/").
		then().
			body(hasXPath("/CUSTOMER/FIRSTNAME"), containsString("Sue"));
	}
	
	@Test
	public void testWithXpathType() {
		
		given().
			get("http://www.thomas-bayer.com/sqlrest/CUSTOMER/10/").
		then().
			body(hasXPath("/INVOICEList/INVOICE[text()='40']")).log().all();
	}

}
