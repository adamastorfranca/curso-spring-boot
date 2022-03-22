package br.com.adamastor.forum.controller.rest;


import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.adamastor.forum.model.dto.DetalhesTopicoDTO;
import br.com.adamastor.forum.model.dto.TopicoDTO;
import br.com.adamastor.forum.model.form.AtualizacaoTopicoForm;
import br.com.adamastor.forum.model.form.TopicoForm;
import br.com.adamastor.forum.model.service.TopicoService;

@RestController
@RequestMapping("rest/topicos")
public class TopicoRest {
	
	@Autowired
	private TopicoService topicoService;
	
	@PostMapping(value = "/cadastrar", produces = MediaType.APPLICATION_JSON_VALUE)
	@CacheEvict(value = "listaDeTopicos", allEntries = true)
	public @ResponseBody ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
		TopicoDTO dto = topicoService.cadastrar(form);
		
		URI uri = uriBuilder.path("rest/topicos/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto); 
	}
	
	@PutMapping(value = "/atualizar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@CacheEvict(value = "listaDeTopicos", allEntries = true)
	public @ResponseBody ResponseEntity<TopicoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm form) {
		TopicoDTO dto = topicoService.atualizar(id, form);

		if (dto == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/deletar/{id}")
	@CacheEvict(value = "listaDeTopicos", allEntries = true)
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		boolean resultado = topicoService.deletar(id);
		
		if (resultado == false) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.OK);	
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@Cacheable(value = "listaDeTopicos")
	public @ResponseBody ResponseEntity<Page<TopicoDTO>> buscarTodosTopicos(@PageableDefault(sort = "titulo", direction = Direction.ASC) Pageable paginacao){
		Page<TopicoDTO> dto = topicoService.buscarTodos(paginacao);
		
		if (dto == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@GetMapping(value = "/buscarPorTitulo/{titulo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<TopicoDTO>> buscarTopicoPorNome(@PathVariable String titulo){
		List<TopicoDTO> dto = topicoService.buscarTopicosPorTitulo(titulo);
		
		if (dto == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@GetMapping(value = "/buscarPorCurso/{nomeCurso}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Page<TopicoDTO>> buscarTopicoPorCurso(@PathVariable String nomeCurso, Pageable paginacao){
		Page<TopicoDTO> dto = topicoService.buscarPorNomeCurso(nomeCurso, paginacao);
		
		if (dto == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@GetMapping(value = "/buscarPorId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<DetalhesTopicoDTO> buscarTopicoPorId(@PathVariable Long id){
		DetalhesTopicoDTO dto = topicoService.buscarPorId(id);
		
		if (dto == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@GetMapping(value = "/buscarPorAutor/{nomeAutor}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<TopicoDTO>> buscarTopicoPorNomeAutor(@PathVariable String nomeAutor){
		List<TopicoDTO> dto = topicoService.buscarPorAutor(nomeAutor);
		
		if (dto == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

}
