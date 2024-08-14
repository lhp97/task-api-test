package br.ce.wceaquino.tests.apitest;

import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class APITest {
	
	@Before
	public void setup() {
		RestAssured.baseURI = "http://localhost:8001/tasks-backend";
	}
	
	@Test
	public void deveRetornarListaDeTarefas() {
		
		RestAssured
			.given()
			.when()
				.get("/todo")
			.then()
				.statusCode(200);
	}
	
	@Test
	public void deveGravarTarefaComSucessoQuandoTarefaForValida() {
		
		RestAssured
			.given()
				.body("{ \"task\": \"Teste via API\", \"dueDate\": \"2024-08-13\" }")
				.contentType(ContentType.JSON)
			.when()
				.post("/todo")
			.then()
				.log().all()
				.statusCode(201);
		
	}
	
	@Test
	public void naoDeveGravarTarefaInValida() {
		
		RestAssured
			.given()
				.body("{ \"task\": \"Teste via API\", \"dueDate\": \"2020-12-30\" }")
				.contentType(ContentType.JSON)
			.when()
				.post("/todo")
			.then()
				.log().all()
				.statusCode(400);
		
	}

}
