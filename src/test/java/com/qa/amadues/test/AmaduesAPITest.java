package com.qa.amadues.test;




import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AmaduesAPITest extends BaseTest {
	//invocationCount is used run on multiple times//5th 49.04
	@Test//(invocationCount = 2)
	public void getFlightDetailsTest() {
		
		Map<String, String> queryParams = Map.of("origin", "PAR", "maxPrice", "200");
		Response response = restclient.get(BASE_URL_AMADEUS, "/v1/shopping/flight-destinations", queryParams, null, AuthType.OAUTH2, ContentType.JSON);
		Assert.assertEquals(response.statusCode(), 200);
	}
	

}
