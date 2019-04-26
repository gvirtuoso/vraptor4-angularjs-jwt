package br.com.virtuoso.daos;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.virtuoso.exceptions.DataAccessObjectException;
import br.com.virtuoso.interfaces.IDataAccessObject;
import br.com.virtuoso.models.Persistivel;

@SuppressWarnings({ "unchecked", "rawtypes" })
public abstract class AbstractDao<T extends Persistivel> implements IDataAccessObject<T> {

	protected Session session;

	@Transactional
	@Override
	public void salvar(T objeto) throws DataAccessObjectException {
		this.session.save(objeto);
	}

	@Transactional
	@Override
	public void alterar(T objeto) throws DataAccessObjectException {
		this.session.update(objeto);
	}

	@Transactional
	@Override
	public void excluir(T objeto) throws DataAccessObjectException {
		this.session.delete(objeto);
	}

	@Override
	public T listar(T objeto) throws DataAccessObjectException {

		StringBuilder hql = new StringBuilder("from " + getTypeClass().getName() + " e where 1 = 1 ");
		hql.append("and e.id = :id ");

		Query query = session.createQuery(hql.toString());
		query.setParameter("id", objeto.getId());

		return (T) query.uniqueResult();

	}

	@Override
	public List<T> listarTodos() throws DataAccessObjectException {
		return session.createQuery("from " + getTypeClass().getName()).list();
	}

	@Override
	public List<T> listarTodosOrdenando(String columnName) throws DataAccessObjectException {
		Criteria criteria = session.createCriteria(getTypeClass());
		criteria.addOrder(Order.asc(columnName));
		return (List<T>) criteria.list();

	}

	private Class<?> getTypeClass() {
		Class clazz = this.getClass();
		while (!AbstractDao.class.equals(clazz.getSuperclass())) {
			clazz = clazz.getSuperclass();
		}
		return (Class<?>) ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[0];
	}

}