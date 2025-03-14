package br.com.cotiinformatica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@Operation(summary = "Serviço da API para autenticar um usuário.") 
	@PostMapping("autenticar") public void autenticar() {
		// TODO } }
	}
	}