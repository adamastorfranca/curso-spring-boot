package br.com.adamastor.forum.model.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.adamastor.forum.model.entity.Resposta;

public class RespostaDTO {
	
	private Long id;
	private String mensagem;
	private LocalDateTime dataCriacao;
	private String nomeAutor;
	
	public RespostaDTO() {
	}

	public RespostaDTO(Resposta resposta) {
		this.id = resposta.getId();
		this.mensagem = resposta.getMensagem();
		this.dataCriacao = resposta.getDataCriacao();
		this.nomeAutor = resposta.getAutor().getNome();
	}

	public Long getId() {
		return id;
	}

	public String getMensagem() {
		return mensagem;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public String getNomeAutor() {
		return nomeAutor;
	}
	
	public static List<RespostaDTO> converter(List<Resposta> respostas){
		return respostas.stream().map(RespostaDTO::new).collect(Collectors.toList());
	}
	

}
