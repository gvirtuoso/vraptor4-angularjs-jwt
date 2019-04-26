package br.com.virtuoso.interfaces;

import br.com.virtuoso.exceptions.ControllerException;
import br.com.virtuoso.models.Persistivel;

public interface IController<T extends Persistivel> extends ICrud<T, ControllerException> {

	public void listar(T objeto) throws ControllerException;

	public void listarTodos() throws ControllerException;

	public void listarTodosOrdenando(String columnName) throws ControllerException;

}