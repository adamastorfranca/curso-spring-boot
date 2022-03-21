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

import br.com.adamastor.forum.model.dto.UsuarioDTO;
import br.com.adamastor.forum.model.service.UsuarioService;

@RestController
@RequestMapping("rest/usuarios")
public class UsuarioRest {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<UsuarioDTO>> buscarTodosUsuarios(){
		List<UsuarioDTO> dto = usuarioService.buscarTodos();
		
		if (dto == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@GetMapping(value = "/buscarPorId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<UsuarioDTO> buscarTopicoPorId(@PathVariable Long id){
		UsuarioDTO dto = usuarioService.buscarPorId(id);
		
		if (dto == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@GetMapping(value = "/buscarPorEmail/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<UsuarioDTO>> buscarUsuarioPorEmail(@PathVariable String email){
		List<UsuarioDTO> dto = usuarioService.buscarPorEmail(email);
		
		if (dto == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@GetMapping(value = "/buscarPorNome/{nome}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<UsuarioDTO>> buscarUsuarioPorNome(@PathVariable String nome){
		List<UsuarioDTO> dto = usuarioService.buscarPorNome(nome);
		
		if (dto == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	
	
}
