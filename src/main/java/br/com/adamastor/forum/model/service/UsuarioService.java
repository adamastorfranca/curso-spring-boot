package br.com.adamastor.forum.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.adamastor.forum.model.dto.UsuarioDTO;
import br.com.adamastor.forum.model.entity.Usuario;
import br.com.adamastor.forum.model.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<UsuarioDTO> buscarTodos(){
		List<Usuario> usuarios = (List<Usuario>) usuarioRepository.findAll();
		return UsuarioDTO.converter(usuarios);
	}
	
	public UsuarioDTO buscarPorId(Long id) {
		Optional<Usuario> usuarios = usuarioRepository.findById(id);
		
		if(usuarios.isPresent()) {
			Usuario usuario = usuarios.get();
			return new UsuarioDTO(usuario);
		}
		return null;
	}
	
	public List<UsuarioDTO> buscarPorEmail(String email) {
		List<Usuario> usuarios = usuarioRepository.findByEmailContains(email);
		return UsuarioDTO.converter(usuarios);
	}
	
	public List<UsuarioDTO> buscarPorNome(String nome) {
		List<Usuario> usuarios = usuarioRepository.findByNomeContains(nome);
		return UsuarioDTO.converter(usuarios);
	}

	
}
