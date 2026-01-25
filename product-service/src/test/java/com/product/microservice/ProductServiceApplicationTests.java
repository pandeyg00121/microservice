package com.product.microservice;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.mongodb.MongoDBContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

	@ServiceConnection
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setup(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static{
		mongoDBContainer.start();
	}

	@Test
	void shouldCreateProduct() {
//		String requestBody = """
//				{	"name":"iphone",
//					"description":"iphone is smart device ",
//					"price":1000
//				}
//				""";
//
//		RestAssured.given()
//				.contentType("application/json")
//				.body(productRequest)
//				.when()
//				.post("/api/product")
//				.then()
//				.log().all()
//				.statusCode(201)
//				.body("id", Matchers.notNullValue())
//				.body("name", Matchers.equalTo(productRequest.name()))
//				.body("description", Matchers.equalTo(productRequest.description()))
//				.body("price", Matchers.is(productRequest.price().intValueExact()));
	}

}
