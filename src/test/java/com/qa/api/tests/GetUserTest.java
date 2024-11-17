package com.qa.api.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetUserTest extends BaseTest{


	@Test
	public void getUserTest() {
		Map<String,String> queryParms =new HashMap<>();
		queryParms.put("name", "naveen");
		queryParms.put("status", "active");
		Response response=restclient.get(BASE_URL_GOREST,"/public/v2/users", queryParms, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.getStatusCode(), 200);

	}
}
