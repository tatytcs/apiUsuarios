package br.com.cotiinformatica.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

//anotação do Lombok que gera automaticamente os métodos getters e setters
@Data
public class AutenticarUsuarioRequestDto {
	
	//anotações do Bean Validation
	@Email(message = "Por favor, informe um email válido.")
	@NotEmpty(message = "Por favor, informe o email de acesso.")
	private String email;
	
	//anotações do Bean Validation
	@Size(min = 8, message = "Por favor, informe uma senha com pelo menos 8 caracteres.")
	@NotEmpty(message = "Por favor, informe a senha de acesso.")
	private String senha;
	
	

}
