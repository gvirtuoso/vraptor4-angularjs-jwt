package br.com.virtuoso.wrappers.seguranca;

import br.com.virtuoso.models.seguranca.SegUsuario;

public class SegUsuarioLoginWrapper {

	private String token;
	private SegUsuario usuario;

	public SegUsuarioLoginWrapper(String token, SegUsuario segUsuario) {
		this.token = token;
		this.usuario = segUsuario;
	}

	public String getToken() {
		return token;
	}

	public SegUsuario getUsuario() {
		return usuario;
	}

}