package com.qa.api.mocking;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

public class WireMockSetup {

	private static WireMockServer server;
	
	public static void createWireMockServer() {
		//https://localhost:8089/api/users
		server=new WireMockServer(8089);
		WireMock.configureFor("localhost", 8089);
		server.start();
	}
	
	public static void stopWireMock() {
		server.stop();
	}
}
