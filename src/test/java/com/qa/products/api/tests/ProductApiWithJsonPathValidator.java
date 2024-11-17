package com.qa.products.api.tests;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.utils.JsonPathValidator;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductApiWithJsonPathValidator extends BaseTest {

	 @Test
	 public void getProductTest() {
		Response response= restclient.get(BASE_URL_PRODUCT,"/products", null, null, AuthType.No_AUTH, ContentType.JSON);
		Assert.assertEquals(response.statusCode(), 200);
		
		List<Number> prices=JsonPathValidator.readList(response, "$[?(@.price > 50)].price");//1.21.00 4th
		System.out.println(prices);
		
		List<Number> id=JsonPathValidator.readList(response, "$[?(@.price > 50)].id");
		System.out.println(id);
		
		List<Double> rates=JsonPathValidator.readList(response, "$[?(@.price > 50)].rating.rate");
		System.out.println(rates);
		
		List<Number> count=JsonPathValidator.readList(response, "$[?(@.price > 50)].rating.count");
		System.out.println(count);
		
		
		//getMap
		List<Map<String,Object>> jewelaryList=JsonPathValidator.readListOfMaps(response, "$[?(@.category == 'jewelery')].['title','price']'");
		
		System.out.println(jewelaryList.size());
		
		for (Map<String, Object> product : jewelaryList) {//4th
			String titel=(String) product.get("title");
			Number price=(Number) product.get("price");
			
			System.out.println("title==>"+titel);
			System.out.println("price==>"+price);
			System.out.println("--------------------------------------");
		}
		
		
		//get min price
		Double minprice=JsonPathValidator.read(response, "min($[*].price)");
		System.out.println("minprice===>"+minprice);
		
	 }
}
