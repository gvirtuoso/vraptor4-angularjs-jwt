package br.com.virtuoso.daos.compra;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.virtuoso.daos.AbstractDao;
import br.com.virtuoso.exceptions.DataAccessObjectException;
import br.com.virtuoso.models.compra.ComListaItem;

@RequestScoped
public class ComListaItemDao extends AbstractDao<ComListaItem> {

	/**
	 * @deprecated Construtor utilizado apenas pelo CDI
	 */
	public ComListaItemDao() {
		this(null);
	}

	@Inject
	public ComListaItemDao(Session session) {
		this.session = session;
	}

	public List<ComListaItem> listarTodos(ComListaItem comListaItem) throws DataAccessObjectException {

		StringBuilder hql = new StringBuilder("from ComListaItem c where 1 = 1 ");
		hql.append("and c.comLista.id = :id ");

		Query query = session.createQuery(hql.toString());
		query.setParameter("id", comListaItem.getComLista().getId());

		return (List<ComListaItem>) query.list();

	}

	@Transactional
	public void excluirItensPorLista(Long idComLista) throws DataAccessObjectException {

		StringBuilder hql = new StringBuilder("delete from ComListaItem c where 1 = 1 ");
		hql.append("and c.comLista.id = :id ");

		Query query = session.createQuery(hql.toString());
		query.setParameter("id", idComLista);

		query.executeUpdate();

		this.session.flush();

	}

}