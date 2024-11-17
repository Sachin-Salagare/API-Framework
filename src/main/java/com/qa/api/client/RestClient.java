package com.qa.api.client;

import static io.restassured.RestAssured.expect;
//used to equalTo method 
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.util.Base64;
import java.util.Map;

import com.qa.api.constants.AuthType;
import com.qa.api.exceptions.FrameWorkException;
import com.qa.api.manager.ConfigManager;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
// we are creating wrapper class hear like post put
public class RestClient {

	private ResponseSpecification responseSpec200= expect().statusCode(200);
	private ResponseSpecification responseSpec200or404= expect().statusCode(anyOf(equalTo(200),equalTo(404)));
	private ResponseSpecification responseSpec200or201= expect().statusCode(anyOf(equalTo(200),equalTo(201)));
	private ResponseSpecification responseSpec201= expect().statusCode(201);
	private ResponseSpecification responseSpec204= expect().statusCode(204);
	private ResponseSpecification responseSpec400= expect().statusCode(400);
	private ResponseSpecification responseSpec401= expect().statusCode(401);
	private ResponseSpecification responseSpec404= expect().statusCode(404);
	private ResponseSpecification responseSpec422= expect().statusCode(422);
	private ResponseSpecification responseSpec500= expect().statusCode(500);

   //private String baseUrl=ConfigManager.get("baseUrl");



	private RequestSpecification setupRequest(String baseUrl,AuthType authtype, ContentType contentType) {

		RequestSpecification request=RestAssured.given().log().all()
		                             .baseUri(baseUrl)
		                                 .contentType(contentType)
		                                    .accept(contentType);

		switch(authtype) {
		case BEARER_TOKEN:
			request.header("Authorization", "Bearer " + ConfigManager.get("bearerToken"));
			break;
		case CONTACTS_BEARER_TOKEN:
			request.header("Authorization", "Bearer " + ConfigManager.get("contacts_bearer_Token"));
			break;			
		case OAUTH2:
			request.header("Authorization", "Bearer " + generatOAuth2Token());
			break;
		case BASIC_AUTH:
			request.header("Authorization", "Basic " +genarateBasicAuthToken());
			break;
		case API_KEy:
			request.header("x-api-key",ConfigManager.get("apiKey"));
			break;
		case No_AUTH:
			System.out.println("No Auth required....");
			break;
			default:
				System.out.println("This auth is not supported.. please pass right AuthType..."); 
				throw new FrameWorkException("NO AUTH SUPPORTED....");

		}
		return request;
	}
	
	/**
	 * This method is used to generate the Base64 encode String 
	 * @return
	 */
	
	public String genarateBasicAuthToken() {
		String credentials=ConfigManager.get("besicUsername")+":"+ConfigManager.get("besicPassword");//admin:admin
		return Base64.getEncoder().encodeToString(credentials.getBytes());
	}
	
	
	
	
	
	//generating OAuth2 token
	private String generatOAuth2Token() {
		return RestAssured.given()
				.formParam("client_id", ConfigManager.get("clientId"))
				.formParam("client_secret", ConfigManager.get("clientSecret"))
				.formParam("grant_type", ConfigManager.get("grantType"))
				.post(ConfigManager.get("tokenUrl"))
				.then()
				.extract()
				.path("access_token");
	}


	//*********************************************CURD Methods**************************************************\\
	/**
	 * This method is used to call	GET APIs
	 * @param endPoint
	 * @param queryParms
	 * @param pathParam
	 * @param authType
	 * @param contentType
	 * @return  it will return get api response
	 */
	public Response get(String baseUrl,String endPoint, Map<String,String> queryParams, Map<String,String> pathParam, AuthType authType, ContentType contentType) {

	RequestSpecification request=setupRequest(baseUrl,authType, contentType);
	    applyParams(request,queryParams,pathParam);

		Response response=request.get(endPoint).then().spec(responseSpec200or404).extract().response();
		response.prettyPrint();
		return response;
	}

  //body using as generics concept in java as T	
	// body could be any type so i used T it can be any type ()
	//some api post will have quaryparam and path param
	//<> Diamond bracket
	
	/**
	 * This method is used to call POST APIs
	 * @param <T>
	 * @param endpoint
	 * @param body
	 * @param queryParams
	 * @param pathParam
	 * @param authType
	 * @param contentType
	 * @return  it will return post api response
	 */
  public <T>Response post(String baseUrl,String endpoint, T body, 
		  Map<String,String> queryParams,Map<String,String> pathParam,AuthType authType, ContentType contentType) {
	  
	  RequestSpecification request=setupRequest(baseUrl,authType, contentType);
	  applyParams(request,queryParams,pathParam);
		Response response=request.body(body).post(endpoint).then().spec(responseSpec200or201).extract().response();
		response.prettyPrint();
		return response;		
  }	

  //over loading post method because of file 
  /**
   * This method is used to call POST APIs with json body
   * @param endpoint
   * @param file
   * @param queryParams
   * @param pathParam
   * @param authType
   * @param contentType
   * @return it will return post api response
   */
  public Response post(String baseUrl,String endpoint, File file, 
		  Map<String,String> queryParams,Map<String,String> pathParam,AuthType authType, ContentType contentType) {
	  
	  RequestSpecification request=setupRequest(baseUrl,authType, contentType);
	  applyParams(request,queryParams,pathParam);
		Response response=request.body(file).post(endpoint).then().spec(responseSpec201).extract().response();
		response.prettyPrint();
		return response;		
  }  
  
  /**
   * This method is used to call put APIs
   * @param endpoint
   * @param body
   * @param queryParams
   * @param pathParam
   * @param authType
   * @param contentType
   * @return it will return put api response
   */
  public <T>Response put(String baseUrl,String endpoint, T body, 
		  Map<String,String> queryParams,Map<String,String> pathParam,AuthType authType, ContentType contentType) {
	  
	  RequestSpecification request=setupRequest(baseUrl,authType, contentType);
	  applyParams(request,queryParams,pathParam);
		Response response=request.body(body).put(endpoint).then().spec(responseSpec200).extract().response();
		response.prettyPrint();
		return response;		
  }	 
  /**
   * This method is used to call put APIs
   * @param endpoint
   * @param body
   * @param queryParams
   * @param pathParam
   * @param authType
   * @param contentType
   * @return  it will return put patch response
   */
  public <T>Response patch(String baseUrl,String endpoint, T body, 
		  Map<String,String> queryParams,Map<String,String> pathParam,AuthType authType, ContentType contentType) {
	  
	  RequestSpecification request=setupRequest(baseUrl,authType, contentType);
	  applyParams(request,queryParams,pathParam);
		Response response=request.body(body).patch(endpoint).then().spec(responseSpec200).extract().response();
		response.prettyPrint();
		return response;		
  }	   
  
  /**
   * This method is used to call delete APIs
   * @param endpoint
   * @param body
   * @param queryParams
   * @param pathParam
   * @param authType
   * @param contentType
   * @return it will return delete patch response
   */
  public Response delete(String baseUrl,String endpoint, 
		  Map<String,String> queryParams,Map<String,String> pathParam,AuthType authType, ContentType contentType) {
	  
	  RequestSpecification request=setupRequest(baseUrl,authType, contentType);
	  applyParams(request,queryParams,pathParam);
		Response response=request.delete(endpoint).then().spec(responseSpec204).extract().response();
		response.prettyPrint();
		return response;		
  }	 
  //Applying  path and quary params
  private void applyParams(RequestSpecification request,Map<String,String> queryParams,Map<String,String> pathParam) {
		if(queryParams !=null) {
			request.queryParams(queryParams);
		}
		if (pathParam !=null) {
			request.queryParams(pathParam);
		}
  }

}
