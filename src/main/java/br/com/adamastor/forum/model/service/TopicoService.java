package br.com.adamastor.forum.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	public Page<TopicoDTO> buscarTodos(int pagina, int qnt, String ordenacao) {
		Pageable paginacao = PageRequest.of(pagina, qnt, Direction.ASC, ordenacao);
		
		Page<Topico> topicos = topicoRepository.findAll(paginacao);
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

	public Page<TopicoDTO> buscarPorNomeCurso(String nomeCurso, int pagina, int qnt, String ordenacao) {
		Pageable paginacao = PageRequest.of(pagina, qnt, Direction.ASC, ordenacao);
		
		Page<Topico> topicos = topicoRepository.findByCursoNomeContains(nomeCurso, paginacao);
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

	@Transactional
	public TopicoDTO cadastrar(TopicoForm form) {
		Topico topico = form.buscarCursoCriarTopico(cursoRepository);
		topicoRepository.save(topico);
		return new TopicoDTO(topico);
	}

	@Transactional
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

	@Transactional
	public boolean deletar(Long id) {
		Optional<Topico> resultado = topicoRepository.findById(id);

		if (resultado.isPresent()) {
			Topico topico = resultado.get();
			topicoRepository.delete(topico);
		}
		return resultado.isPresent();
	}

}
