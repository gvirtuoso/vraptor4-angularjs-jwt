package br.com.virtuoso.daos.cadastro;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.hibernate.Session;

import br.com.virtuoso.daos.AbstractDao;
import br.com.virtuoso.models.cadastro.CadUnidadeMedida;

@RequestScoped
public class CadUnidadeMedidaDao extends AbstractDao<CadUnidadeMedida> {

	/**
	 * @deprecated Construtor utilizado apenas pelo CDI
	 */
	public CadUnidadeMedidaDao() {
		this(null);
	}

	@Inject
	public CadUnidadeMedidaDao(Session session) {
		this.session = session;
	}

}