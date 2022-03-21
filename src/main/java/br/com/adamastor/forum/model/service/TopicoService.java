package br.com.adamastor.forum.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.adamastor.forum.model.dto.DetalhesTopicoDTO;
import br.com.adamastor.forum.model.dto.TopicoDTO;
import br.com.adamastor.forum.model.entity.Topico;
import br.com.adamastor.forum.model.form.AtualizacaoTopicoForm;
import br.com.adamastor.forum.model.form.TopicoForm;
import br.com.adamastor.forum.model.repository.CursoRepository;
import br.com.adamastor.forum.model.repository.TopicoRepository;

@Service
public class TopicoService {

	@Autowired
	private TopicoRepository topicoRepository;
	@Autowired
	private CursoRepository cursoRepository;

	public List<TopicoDTO> buscarTodos() {
		List<Topico> topicos = (List<Topico>) topicoRepository.findAll();
		return TopicoDTO.converter(topicos);
	}

	public DetalhesTopicoDTO buscarPorId(Long id) {
		Optional<Topico> resultado = topicoRepository.findById(id);

		if (resultado.isPresent()) {
			Topico topico = resultado.get();
			return new DetalhesTopicoDTO(topico);
		}
		return null;
	}

	public List<TopicoDTO> buscarPorNomeCurso(String nomeCurso) {
		List<Topico> topicos = topicoRepository.findByCursoNomeContains(nomeCurso);
		return TopicoDTO.converter(topicos);
	}

	public List<TopicoDTO> buscarTopicosPorTitulo(String titulo) {
		List<Topico> topicos = topicoRepository.findByTituloContains(titulo);
		return TopicoDTO.converter(topicos);
	}

	public List<TopicoDTO> buscarPorAutor(String nomeAutor) {
		List<Topico> topicos = topicoRepository.findByAutorNomeContains(nomeAutor);
		return TopicoDTO.converter(topicos);
	}

	public TopicoDTO cadastrar(TopicoForm form) {
		Topico topico = form.converter(cursoRepository);
		topicoRepository.save(topico);
		return new TopicoDTO(topico);
	}

	public TopicoDTO atualizar(Long id, AtualizacaoTopicoForm form) {
		Optional<Topico> resultado = topicoRepository.findById(id);

		if (resultado.isPresent()) {
			Topico topico = resultado.get();
			topico.setTitulo(form.getTitulo());
			topico.setMensagem(form.getMensagem());
			topicoRepository.save(topico);
			return new TopicoDTO(topico);
		}
		return null;
	}

	public void deletar(Long id) {
		Optional<Topico> resultado = topicoRepository.findById(id);

		if (resultado.isPresent()) {
			Topico topico = resultado.get();
			topicoRepository.delete(topico);
		}
	}

}
