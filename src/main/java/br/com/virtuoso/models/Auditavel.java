package br.com.virtuoso.models;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import br.com.virtuoso.models.seguranca.SegUsuario;

@MappedSuperclass
public class Auditavel extends Persistivel {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_ultimo_usuario")
	private SegUsuario ultimoUsuario;

	public SegUsuario getUltimoUsuario() {
		return ultimoUsuario;
	}

	public void setUltimoUsuario(SegUsuario ultimoUsuario) {
		this.ultimoUsuario = ultimoUsuario;
	}

}