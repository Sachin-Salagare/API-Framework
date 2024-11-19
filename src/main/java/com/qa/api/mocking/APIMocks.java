package com.qa.api.mocking;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;//static import wire mock
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;


public class APIMocks {
	//create stub/mock for user :
	public static void getDummyUser() {
		
		stubFor(get(urlEqualTo("/api/users"))
				.willReturn(aResponse()
					.withStatus(200)	
					.withHeader("Contentnt-Type", "application/json")
					.withBodyFile("gorestUser.json")
					)
		    		 );
	}
	
	public static void getDummyUserWithQueryParam() {
		
		stubFor(get(urlPathEqualTo("/api/users"))
				.withQueryParam("name", equalTo("Sachin123"))
				.willReturn(aResponse()
					.withStatus(200)	
					.withHeader("Contentnt-Type", "application/json")
					.withBodyFile("gorestUser.json"))
		    		 );
	}	
	
	public static void getDummyProductWithJsonFile() {
		
		stubFor(get(urlEqualTo("/api/products"))
				.willReturn(aResponse()
					.withStatus(200)	
					.withHeader("Contentnt-Type", "application/json")
					.withBodyFile("product.json"))
		    		 );
	}	
	
//***********************************************create stub/mock for post call******************************************************
//1.00.00 7th
	
	public static void createDummyUser() {
		stubFor(post(urlEqualTo("/api/users"))
				.withHeader("Content-Type", equalTo("application/json"))
				.willReturn(aResponse()
						.withStatus(201)
						.withHeader("Content-Type", "application/json")
						.withStatusMessage("user is created")
						.withBody("{\"id\":\"1\",\"name\":\"jhon\"}")
						));
	}
	
	
	public static void createDummyUserWithJsonFile() {
		stubFor(post(urlEqualTo("/api/users"))
				.withHeader("Content-Type", equalTo("application/json"))
				.willReturn(aResponse()
						.withStatus(201)
						.withHeader("Content-Type", "application/json")
						.withStatusMessage("user is created")
						.withBodyFile("gorestUser.json")
						));
	}	
	
	
	//****************************** create stub/mock for DELETE calls**************************************************


     public static void deleteDummyUser() {
    	 
    	 stubFor(delete(urlEqualTo("/user/users/1"))
    			 .willReturn(aResponse()
    					 .withStatus(204)
    					 .withStatusMessage("USER DELETED")
    					 ));
    	 
     }





}

