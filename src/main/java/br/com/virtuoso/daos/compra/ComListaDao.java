package br.com.virtuoso.daos.compra;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.virtuoso.daos.AbstractDao;
import br.com.virtuoso.exceptions.DataAccessObjectException;
import br.com.virtuoso.models.compra.ComLista;

@RequestScoped
public class ComListaDao extends AbstractDao<ComLista> {

	/**
	 * @deprecated Construtor utilizado apenas pelo CDI
	 */
	public ComListaDao() {
		this(null);
	}

	@Inject
	public ComListaDao(Session session) {
		this.session = session;
	}

	public List<ComLista> listarTodos(ComLista lista) throws DataAccessObjectException {

		StringBuilder hql = new StringBuilder("from ComLista l where 1 = 1 ");
		hql.append("and l.segUsuarioProprietario.id = :id ");

		Query query = session.createQuery(hql.toString());
		query.setParameter("id", lista.getSegUsuarioProprietario().getId());

		return (List<ComLista>) query.list();

	}

}