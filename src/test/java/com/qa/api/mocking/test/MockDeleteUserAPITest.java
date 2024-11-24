package com.qa.api.mocking.test;

import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.mocking.APIMocks;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class MockDeleteUserAPITest  extends BaseTest{
	
  @Test
  public void deletDummyAPITest() {
	 APIMocks.deleteDummyUser();
	 Response response=restclient.delete(BASE_URL_LOCALHOST_PORT, "/user/users/1", null, null, AuthType.No_AUTH, ContentType.ANY);
	  response.then()
	  .assertThat()
	  .statusCode(204)
	
	  ;
  }
}
