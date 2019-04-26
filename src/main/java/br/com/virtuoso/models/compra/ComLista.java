package br.com.virtuoso.models.compra;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.virtuoso.models.Auditavel;
import br.com.virtuoso.models.seguranca.SegUsuario;

@Entity
@Table(name = "com_lista")
public class ComLista extends Auditavel {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_proprietario")
	private SegUsuario segUsuarioProprietario;

	@Column(name = "tx_descricao", length = 100)
	private String descricao;

	public SegUsuario getSegUsuarioProprietario() {
		return segUsuarioProprietario;
	}

	public void setSegUsuarioProprietario(SegUsuario segUsuarioProprietario) {
		this.segUsuarioProprietario = segUsuarioProprietario;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}