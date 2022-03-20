package br.com.adamastor.forum.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.adamastor.forum.model.dto.TopicoDTO;
import br.com.adamastor.forum.model.entity.Topico;
import br.com.adamastor.forum.model.repository.TopicoRepository;

@Service
public class TopicoService  {
	
	@Autowired
	private TopicoRepository topicoRepository;	
//	@Autowired
//	private CursoRepository cursoRepository;
	
	public List<TopicoDTO> buscarTodos(){
		List<Topico> topicos = (List<Topico>) topicoRepository.findAll();
		return TopicoDTO.converter(topicos);
	}
	
	public TopicoDTO buscarPorId(Long id){
		Optional<Topico> topicos = topicoRepository.findById(id);
				
		if(topicos.isPresent()) {
			Topico topico = topicos.get();
			return new TopicoDTO(topico);
		}
		
		return null;
	}
	
	public List<TopicoDTO> buscarPorNomeCurso(String nomeCurso){
		List<Topico> topicos = topicoRepository.findByCursoNomeContainsIgnoreCase(nomeCurso);
		return TopicoDTO.converter(topicos);
	}

	public List<TopicoDTO> buscarPorTitulo(String titulo) {
		List<Topico> topicos = topicoRepository.findByTituloContainsIgnoreCase(titulo);
		return TopicoDTO.converter(topicos);
	}
	
	public List<TopicoDTO> buscarPorAutor(String nomeAutor) {
		List<Topico> topicos = topicoRepository.findByUsuarioNomeContainsIgnoreCase(nomeAutor);
		return TopicoDTO.converter(topicos);
	}

}
