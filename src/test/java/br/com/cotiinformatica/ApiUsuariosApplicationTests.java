package br.com.cotiinformatica;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import br.com.cotiinformatica.dtos.AutenticarUsuarioRequestDto;
import br.com.cotiinformatica.dtos.CriarUsuarioRequestDto;

@SpringBootTest
//Configura o ambiente de teste para simular requisições HTTP
@AutoConfigureMockMvc
//Ordena a execução dos testes
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApiUsuariosApplicationTests {
	
	@Autowired	MockMvc mockMvc; //simula requisições HTTP
	@Autowired ObjectMapper objectMapper;//converte objetos para JSON e vice-versa
	
	
	private static String email;
	private static String senha;
	
	@Test
	@Order(1)
	void criarUsuarioComSucesso() throws Exception {
		
		var faker = new Faker();
		
		
		var request = new CriarUsuarioRequestDto();
		request.setNome(faker.name().fullName());//nome válido
		request.setEmail(faker.internet().emailAddress());//email válido
		request.setSenha("@Admin123");//senha fixa
		
		//executa requisição HTTP e verifica a resposta
		mockMvc.perform(
				post("/api/usuario/criar") //URL da requisição HTTP
				.contentType("application/json") //tipo de conteúdo da requisição 
				.content(objectMapper.writeValueAsString(request))) //corpo da requisição (JSON) 
				.andExpect(status().isOk());//resposta HTTP
		
		 email = request.getEmail(); //armazenar o email do usuário criado
		 senha = request.getSenha(); //armazenar a senha do usuário criado
		
	}

	@Test
	@Order(2)
	void autenticarUsuarioComSucesso() throws Exception {
		
		var request = new AutenticarUsuarioRequestDto();
		request.setEmail(email); //preencher com o email do usuário criado
		request.setSenha(senha);//preencher com a senha do usuário criado
		
		mockMvc.perform(post("/api/usuario/autenticar") // URL da requisição HTTP
				.contentType("application/json") // tipo de conteúdo da requisição
				.content(objectMapper.writeValueAsString(request))) // corpo da requisição (JSON)
				.andExpect(status().isOk());// resposta HTTP
	}
	
	@Test
	@Order(3)
	void criarUsuarioJaExistente() throws Exception {
		
		
		var request = new CriarUsuarioRequestDto();
		request.setNome("Usuario Existente");//nome válido
		request.setEmail(email);//email já cadastrado anteriormente no teste 1
		request.setSenha("@Admin123");//senha fixa
		
		var result = mockMvc.perform(post("/api/usuario/criar") // URL da requisição HTTP
				.contentType("application/json") // tipo de conteúdo da requisição
				.content(objectMapper.writeValueAsString(request))) // corpo da requisição (JSON)
				.andExpect(status().isBadRequest())// resposta HTTP
				.andReturn();
				
		//getContentAsString() -> obter o conteúdo da resposta HTTP (JSON)		
		var content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		assertTrue(content.contains("O email informado já está cadastrado, tente outro."));
		
	}
	
	@Test
	@Order(4)
	void acessoNegadoParaUsuarioInvalido() throws Exception {
		
		
		var request = new AutenticarUsuarioRequestDto();
		request.setEmail(email);//email já cadastrado anteriormente no teste 1
		request.setSenha("@Teste2025");//senha fixa
		
		
		
		//executa requisição HTTP e verifica a resposta
		var result = mockMvc.perform(
				post("/api/usuario/autenticar") //URL da requisição HTTP
				.contentType("application/json") //tipo de conteúdo da requisição 
				.content(objectMapper.writeValueAsString(request))) //corpo da requisição (JSON) 
				.andExpect(status().isBadRequest())
				.andReturn();//resposta HTTP
		
		var content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		assertTrue(content.contains("Acesso negado. Usuário não encontrado."));
		
	}
}
