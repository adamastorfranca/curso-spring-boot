package br.com.adamastor.forum.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.adamastor.forum.model.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
	
	List<Usuario> findByNomeContains(String nome);
	List<Usuario> findByEmailContains(String email);

}
