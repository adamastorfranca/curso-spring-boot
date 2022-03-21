package br.com.adamastor.forum.model.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.adamastor.forum.model.entity.StatusTopico;
import br.com.adamastor.forum.model.entity.Topico;

public class DetalhesTopicoDTO {
	
	private Long id;
	private String titulo;
	private String nomeAutor;
	private String mensagem;
	private LocalDateTime dataCriacao;
	private String nomeCurso;
	private StatusTopico status;
	private List<RespostaDTO> respostas;
	
	public DetalhesTopicoDTO() {
	}

	public DetalhesTopicoDTO(Topico topico) {
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.dataCriacao = topico.getDataCriacao();
		this.status = topico.getStatus();
		this.nomeAutor = topico.getAutor().getNome();
		this.nomeCurso = topico.getCurso().getNome();
		this.respostas = new ArrayList<>();
		this.respostas.addAll(RespostaDTO.converter(topico.getRespostas()));
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public StatusTopico getStatus() {
		return status;
	}

	public String getNomeAutor() {
		return nomeAutor;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public List<RespostaDTO> getRespostas() {
		return respostas;
	}

}
