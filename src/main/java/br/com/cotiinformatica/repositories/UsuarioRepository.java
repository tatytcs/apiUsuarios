package br.com.cotiinformatica.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cotiinformatica.entities.Usuario;

//interface para representar o repositório de dados da entidade Usuario
//Nome da entidade e tipo de chave primária
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
	
	// Usuario é o nome da classe
	@Query("""
			SELECT u
			FROM Usuario u
			WHERE u.email = :param1
			""")
	
	
	// método para consultar um usuário pelo email
	public Usuario findByEmail(@Param("param1") String email);
	
	@Query("""
            SELECT u
            FROM Usuario u
            WHERE u.email = :param1 AND u.senha = :param2
            """)

	// método para consultar um usuário pelo email e senha
	public Usuario findByEmailAndSenha(@Param("param1") String email, @Param("param2")String senha);
	
}




