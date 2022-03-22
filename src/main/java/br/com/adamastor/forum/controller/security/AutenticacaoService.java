package br.com.adamastor.forum.controller.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.adamastor.forum.model.entity.Usuario;
import br.com.adamastor.forum.model.repository.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> resultado = usuarioRepository.findByEmail(username);
		if (resultado.isPresent()) {
			return resultado.get();
		}
		
		throw new UsernameNotFoundException("Dados inválidados");
	}
	
	

}
