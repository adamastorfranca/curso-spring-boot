package br.com.adamastor.forum.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import br.com.adamastor.forum.model.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
	
	Page<Usuario> findAll(Pageable paginacao);
	Page<Usuario> findByNomeContains(String nome, Pageable paginacao);
	List<Usuario> findByEmailContains(String email);
	Optional<Usuario> findByEmail(String email);

}
