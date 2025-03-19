package br.com.cotiinformatica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.dtos.AutenticarUsuarioRequestDto;
import br.com.cotiinformatica.dtos.AutenticarUsuarioResponseDto;
import br.com.cotiinformatica.dtos.CriarUsuarioRequestDto;
import br.com.cotiinformatica.dtos.CriarUsuarioResponseDto;
import br.com.cotiinformatica.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

	// Injeção de dependência
	@Autowired
	UsuarioService usuarioService;

	@Operation(summary = "Serviço da API para criar um novo usuário.")
	@PostMapping("criar")
	public ResponseEntity<CriarUsuarioResponseDto> criar(@RequestBody @Valid CriarUsuarioRequestDto request) {
		var response = usuarioService.criarUsuario(request);
		return ResponseEntity.ok(response);
	}
	
	/*	 
	  	ResponseEntity -> representa uma resposta HTTP
		AutenticarUsuarioRequestDTO -> representa os dados de entrada do serviço
		@RequestBody -> indica que os dados estão no corpo da requisição
		@Valid -> indica que a validação do objeto deve ser feita
		AutenticarUsuarioResponseDTO -> representa os dados de saída do serviço
	 */
	
	@Operation(summary = "Serviço da API para autenticar um usuário.") 
	@PostMapping("autenticar")
	public ResponseEntity<AutenticarUsuarioResponseDto> autenticar(@RequestBody @Valid AutenticarUsuarioRequestDto request) {
		
		//invocando o serviço para autenticar
		var response = usuarioService.autenticarUsuario(request);
		//retornando a resposta
        return ResponseEntity.ok(response);
	}
}