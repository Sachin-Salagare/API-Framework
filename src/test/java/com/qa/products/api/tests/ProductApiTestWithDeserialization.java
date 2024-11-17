package com.qa.products.api.tests;



import java.util.Arrays;

import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.gorestpojo.Product;
import com.qa.api.utils.JsonUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductApiTestWithDeserialization  extends BaseTest{
	
	
	@Test
	public void getAllProduct() {
	Response response=restclient.get(BASE_URL_PRODUCT,"/products", null, null, AuthType.No_AUTH, ContentType.JSON);
	 //product [] response coming from array
	Product[] product=JsonUtils.deserialize(response, Product[].class);
	System.out.println(Arrays.toString(product));
	
	for (Product p : product) {
		System.out.println("Id-->" +p.getId());
		System.out.println("title-->" +p.getTitle());
		System.out.println("rating-->" +p.getRating().getRate());
		System.out.println("count-->" +p.getRating().getCount());

		System.out.println("-----------------------------");

	}
	
	}

}
