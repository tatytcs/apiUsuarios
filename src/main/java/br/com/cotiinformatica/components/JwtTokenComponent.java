package br.com.cotiinformatica.components;

import java.util.Date;

import org.springframework.stereotype.Component;

import br.com.cotiinformatica.entities.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenComponent {

	public String getToken(Usuario usuario) {

		// chave secreta para assinatura do token
		var secretKey = "83c33a5f-b544-4dab-bdb2-98887805cf45";

		// gerando o token

		return Jwts.builder().setSubject(usuario.getEmail()) // email do usuário
				.claim("perfil", usuario.getPerfil().getNome()) // perfil do usuário
				.setIssuedAt(new Date()) // data de geração do token
				.setExpiration(new Date(System.currentTimeMillis() + 1800000)) // data de expiração do token (30min)
				.signWith(SignatureAlgorithm.HS256, secretKey) // chave para assinatura do token
				.compact();
	}
}