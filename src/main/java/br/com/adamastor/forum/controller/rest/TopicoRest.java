package br.com.adamastor.forum.controller.rest;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.adamastor.forum.model.dto.TopicoDTO;
import br.com.adamastor.forum.model.service.TopicoService;

@RestController
@RequestMapping("rest/topicos")
public class TopicoRest {
	
	@Autowired
	private TopicoService topicoService;

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
		List<TopicoDTO> dto = topicoService.buscarPorTitulo(titulo);
		
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
	public @ResponseBody ResponseEntity<TopicoDTO> buscarTopicoPorId(@PathVariable Long id){
		TopicoDTO dto = topicoService.buscarPorId(id);
		
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
//	@PostMapping
//	public ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
//		Topico topico = form.converter(cursoRepository);	
//		topicoRepository.save(topico);
//		
//		URI uri = uriBuilder.path("topicos/{id}").buildAndExpand(topico.getId()).toUri();
//		return ResponseEntity.created(uri).body(new TopicoDTO(topico)); 
//	}
}
