package br.com.cotiinformatica.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.cotiinformatica.entities.Usuario;

//interface para representar o repositório de dados da entidade Usuario
//Nome da entidade e tipo de chave primária
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

	
}
