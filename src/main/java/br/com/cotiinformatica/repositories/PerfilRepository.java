package br.com.cotiinformatica.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cotiinformatica.entities.Perfil;

//interface para representar o repositório de dados da entidade Perfil
//Nome da entidade e tipo de chave primária
@Repository
public interface PerfilRepository extends JpaRepository<Perfil, UUID> {
	
	//método para consultar um perfil pelo nome
	@Query("""
            SELECT p
            FROM Perfil p
            WHERE p.nome = :param1
            """)
	
	//@param1 -> nome do parâmetro que será recebido pelo método
	public Perfil findByNome(@Param("param1") String nome);
		
}
