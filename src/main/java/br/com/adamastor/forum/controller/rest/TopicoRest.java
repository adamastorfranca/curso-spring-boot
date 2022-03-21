package br.com.adamastor.forum.controller.rest;


import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
	public ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
		TopicoDTO dto = topicoService.cadastrar(form);
		
		URI uri = uriBuilder.path("rest/topicos/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto); 
	}
	
	@PutMapping(value = "/atualizar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TopicoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm form) {
		TopicoDTO dto = topicoService.atualizar(id, form);
		
		return ResponseEntity.ok(dto); 
	}
	
	@DeleteMapping(value = "/deletar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deletar(@PathVariable Long id, UriComponentsBuilder uriBuilder) {
		topicoService.deletar(id);
		return ResponseEntity.ok().build(); 	
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<TopicoDTO>> buscarTodosTopicos(){
		List<TopicoDTO> dto = topicoService.buscarTodos();
		
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
	public @ResponseBody ResponseEntity<List<TopicoDTO>> buscarTopicoPorCurso(@PathVariable String nomeCurso){
		List<TopicoDTO> dto = topicoService.buscarPorNomeCurso(nomeCurso);
		
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
