package com.qa.contacts.api.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.gorestpojo.ContactsCredentials;
import com.qa.api.manager.ConfigManager;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ContactAPITest extends BaseTest {

	String tokenId;
	@BeforeMethod
	public void generateToken(){
		
		 ContactsCredentials cred=ContactsCredentials.builder()
		                     .email("sachinss123@gmail.com")
		                     .password("Sachin@123")
		                     .build();
		                      
		Response response=restclient.post(BASE_URL_CONTACTS,"/users/login", cred, null, null, AuthType.No_AUTH, ContentType.JSON);
		tokenId=response.jsonPath().getString("token");
		System.out.println("My token id====>"+ tokenId);
		ConfigManager.set("contacts_bearer_Token", tokenId);
	}
	
	@Test
	public void getContactsTest() {
		Response response=restclient.get(BASE_URL_CONTACTS,"/contacts", null, null, AuthType.CONTACTS_BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
}
