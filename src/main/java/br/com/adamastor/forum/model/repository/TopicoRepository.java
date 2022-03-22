package br.com.adamastor.forum.model.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import br.com.adamastor.forum.model.entity.Topico;

public interface TopicoRepository extends CrudRepository<Topico, Long>{

	List<Topico> findByTituloContains(String titulo);
	Page<Topico> findByCursoNomeContains(String nomeCurso, Pageable paginacao);
	List<Topico> findByAutorNomeContains(String nomeAutor);
	Page<Topico> findAll(Pageable paginacao);
	
}
