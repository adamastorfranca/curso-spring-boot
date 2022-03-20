package br.com.adamastor.forum.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.adamastor.forum.model.entity.Topico;

public interface TopicoRepository extends CrudRepository<Topico, Long>{

	List<Topico> findByTituloContainsIgnoreCase(String titulo);
	List<Topico> findByCursoNomeContainsIgnoreCase(String nomeCurso);
	List<Topico> findByUsuarioNomeContainsIgnoreCase(String nomeAutor);
	
}
