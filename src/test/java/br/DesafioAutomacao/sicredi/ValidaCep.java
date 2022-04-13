package br.DesafioAutomacao.sicredi;

import static io.restassured.RestAssured.get;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;


public class ValidaCep {
  
	@Test
	public void consultaCEPValido() {

		get("https://viacep.com.br/ws/91060900/json/")
		.then().statusCode(200)
		.assertThat().body("cep", equalTo("91060-900"))
		.assertThat().body("logradouro", 	equalTo("Avenida Assis Brasil 3940"))
		.assertThat().body("complemento",	equalTo(""))
		.assertThat().body("bairro", equalTo("São Sebastião"))
		.assertThat().body("localidade", equalTo("Porto Alegre"))
		.assertThat().body("uf", equalTo("RS"))
		.assertThat().body("ibge", equalTo("4314902"));
	}
	
	@Test
	public void consultaCEPFormatoInvalido() {

		get("https://viacep.com.br/ws/1234/json/")
		.then().statusCode(400)
		.assertThat().body("html.body.h1",equalTo("Erro 400"));
	}
	
	@Test
	public void consultaCEPInexistente() {
		get("https://viacep.com.br/ws/99999999/json/")
		.then().statusCode(200)
		.assertThat().body("erro", equalTo(true));
	}
	
	
}
