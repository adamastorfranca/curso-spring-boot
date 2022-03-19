package br.com.adamastor.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.adamastor.forum.modelo.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long>{

	Curso findByNome(String nome);
	
}
