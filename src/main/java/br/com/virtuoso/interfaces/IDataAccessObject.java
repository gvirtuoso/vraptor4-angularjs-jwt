package br.com.virtuoso.interfaces;

import java.util.List;

import br.com.virtuoso.exceptions.DataAccessObjectException;
import br.com.virtuoso.models.Persistivel;

public interface IDataAccessObject<T extends Persistivel> extends ICrud<T, DataAccessObjectException> {

	public T listar(T objeto) throws DataAccessObjectException;

	public List<T> listarTodos() throws DataAccessObjectException;

	public List<T> listarTodosOrdenando(String columnName) throws DataAccessObjectException;

}