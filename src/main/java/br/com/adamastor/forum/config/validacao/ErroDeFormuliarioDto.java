package br.com.adamastor.forum.config.validacao;

public class ErroDeFormuliarioDto {
	
	private String campo;
	private String erro;
	
	public ErroDeFormuliarioDto(String campo, String erro) {
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
