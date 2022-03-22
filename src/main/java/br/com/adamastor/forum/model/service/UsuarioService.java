package br.com.adamastor.forum.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.adamastor.forum.model.dto.UsuarioDTO;
import br.com.adamastor.forum.model.entity.Usuario;
import br.com.adamastor.forum.model.form.UsuarioForm;
import br.com.adamastor.forum.model.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Transactional
	public UsuarioDTO cadastrar(UsuarioForm form) {
		Usuario usuario = form.criarUsuario();
		usuarioRepository.save(usuario);
		return new UsuarioDTO(usuario);
	}
	
	@Transactional
	public boolean deletar(Long id) {
		Optional<Usuario> resultado = usuarioRepository.findById(id);
		if(resultado.isPresent()) {
			Usuario usuario = resultado.get();
			usuarioRepository.delete(usuario);
		}
		return resultado.isPresent();
	}
	
	@Transactional
	public UsuarioDTO atualizar(Long id, UsuarioForm form) {
		Optional<Usuario> resultado = usuarioRepository.findById(id);
		if(resultado.isPresent()) {
			Usuario usuario = resultado.get();
			usuario.setNome(form.getNome());
			usuario.setEmail(form.getEmail());
			usuario.setSenha(form.getSenha());
			usuarioRepository.save(usuario);
			return new UsuarioDTO(usuario);
		}
		return null;
	}
	
	public Page<UsuarioDTO> buscarTodos(Pageable paginacao){
		Page<Usuario> usuarios = usuarioRepository.findAll(paginacao);
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
	
		public Page<UsuarioDTO> buscarPorNome(String nome, Pageable paginacao) {
		Page<Usuario> usuarios = usuarioRepository.findByNomeContains(nome, paginacao);
		return UsuarioDTO.converter(usuarios);
	}
		
	public List<UsuarioDTO> buscarPorEmail(String email) {
		List<Usuario> usuarios = usuarioRepository.findByEmailContains(email);
		return UsuarioDTO.converter(usuarios);
	}
	
}
