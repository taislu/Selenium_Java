package restApiTest;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

public class Test2_SettingRootPath {
	
	// basic way to test all parameters/nodes
	
	//@Test
	public void testWithoutRoot() {
		
		given().
			get("http://services.groupkt.com/country/get/iso3code/USA").
		then().
			body("RestResponse.result.name", is("United States of America")).
			body("RestResponse.result.alpha2_code", is("US")).
			body("RestResponse.result.alpha3_code", is("USA"));
	}

	// use root feature to test all parameters/nodes
	
	//@Test
	public void testWithRoot() {
		
		given().
			get("http://services.groupkt.com/country/get/iso3code/USA").
		then().
			root("RestResponse.result").
			body("name", is("United States of America")).
			body("alpha2_code", is("US")).
			body("alpha3_code", is("USA"));
	}
	
	// detach root path
	
	//@Test
	public void testDetachRoot() {
			
		given().
			get("http://services.groupkt.com/country/get/iso3code/USA").
		then().
			root("RestResponse.result").
			body("name", is("United States of America")).
			body("alpha2_code", is("US")).
			detachRoot("result").
			body("result.alpha3_code", is("USA"));
	}
	
}
