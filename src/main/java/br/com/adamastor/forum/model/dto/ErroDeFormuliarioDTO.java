package br.com.adamastor.forum.model.dto;

public class ErroDeFormuliarioDTO {
	
	private String campo;
	private String erro;
	
	public ErroDeFormuliarioDTO(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}

}
