package br.com.adamastor.forum.model.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.adamastor.forum.model.entity.Curso;

public interface CursoRepository extends CrudRepository<Curso, Long>{

	Curso findByNome(String nomeCurso);
	
}
