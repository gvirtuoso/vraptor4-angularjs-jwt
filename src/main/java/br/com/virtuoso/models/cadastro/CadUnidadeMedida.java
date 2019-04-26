package br.com.virtuoso.models.cadastro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.virtuoso.models.Persistivel;

@Entity
@Table(name = "cad_unidade_medida")
public class CadUnidadeMedida extends Persistivel {

	@Column(name = "tx_descricao", length = 100)
	private String descricao;

	@Column(name = "tx_simbolo", length = 5)
	private String simbolo;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

}