package com.qa.api.schemavalidation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.utils.SchemaValidator;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductApiSchemaTest extends BaseTest {

	@Test
	public void productApiTest() {
//		RestAssured.given()
//		           .baseUri("https://fakestoreapi.com")
//		           .when()
//		           .get("/products")
//		           .then()
//		           .assertThat()
//		           .statusCode(200)
//		           .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/Product-schema.json"));//6th 35.00
		
	Response response= restclient.get(BASE_URL_PRODUCT,"/products" , null, null, AuthType.No_AUTH, ContentType.JSON);
	Assert.assertEquals( SchemaValidator.validateSchema(response, "schema/Product-schema.json"),true);
	}
	

}
