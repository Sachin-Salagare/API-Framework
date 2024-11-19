package com.qa.api.base;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.qa.api.client.RestClient;
import com.qa.api.mocking.WireMockSetup;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

public class BaseTest   {

	
	protected final static String BASE_URL_REQ_RES="https://reqres.in";
	protected final static String BASE_URL_PRODUCT="https://fakestoreapi.com";
	protected final static String BASE_URL_CONTACTS="https://thinking-tester-contact-list.herokuapp.com";
	protected final static String BASE_URL_GOREST="https://gorest.co.in";
	protected final static String BASE_URL_CIRCUIT="https://ergast.com";
	protected final static String BASE_URL_BASIC_AUTH = "https://the-internet.herokuapp.com";
	protected final static String BASE_URL_AMADEUS = "https://test.api.amadeus.com";
	protected final static String BASE_URL_LOCALHOST_PORT = "http://localhost:8089";
	
	
	protected RestClient restclient;
	
	@BeforeSuite
	public void setUpReport() {
		RestAssured.filters(new AllureRestAssured());
	}
	
	@BeforeTest
	public void setUp() {
		restclient= new RestClient();
		WireMockSetup.createWireMockServer();

	}
	
	
	
	@AfterTest
	public void stopMockServer() {
		WireMockSetup.stopWireMock();
	}
}

