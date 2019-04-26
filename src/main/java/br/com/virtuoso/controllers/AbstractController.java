package br.com.virtuoso.controllers;

import java.util.ResourceBundle;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import br.com.virtuoso.annotations.PrecisaEstarLogado;
import br.com.virtuoso.daos.AbstractDao;
import br.com.virtuoso.exceptions.ControllerException;
import br.com.virtuoso.interfaces.IController;
import br.com.virtuoso.models.Persistivel;

public abstract class AbstractController<T extends Persistivel> implements IController<T> {

	protected AbstractDao<T> dao;
	protected Result result;
	protected ResourceBundle bundle;
	protected Validator validator;

	@Override
	@Post
	@Consumes(value = "application/json")
	@PrecisaEstarLogado
	public void salvar(T objeto) throws ControllerException {
		this.dao.salvar(objeto);
		this.result.use(Results.json()).withoutRoot().from(objeto).serialize();
	}

	@Override
	@Put
	@Consumes(value = "application/json")
	@PrecisaEstarLogado
	public void alterar(T objeto) throws ControllerException {
		this.dao.alterar(objeto);
		this.result.use(Results.json()).withoutRoot().from(objeto).serialize();
	}

	@Override
	@Post
	@Consumes(value = "application/json")
	@PrecisaEstarLogado
	public void excluir(T objeto) throws ControllerException {
		this.dao.excluir(objeto);
		this.result.use(Results.json()).withoutRoot().from(objeto).serialize();

	}

	@Override
	@Get("/{objeto.id}/listar")
	@PrecisaEstarLogado
	public void listar(T objeto) throws ControllerException {
		this.result.use(Results.json()).withoutRoot().from(this.dao.listar(objeto)).serialize();
	}

	@Override
	@Get("/listarTodos")
	@PrecisaEstarLogado
	public void listarTodos() throws ControllerException {
		this.result.use(Results.json()).withoutRoot().from(this.dao.listarTodos()).serialize();
	}

	@Override
	@Get("/listarTodosOrdenando/{columnName}")
	@PrecisaEstarLogado
	public void listarTodosOrdenando(String columnName) throws ControllerException {
		this.result.use(Results.json()).withoutRoot().from(this.dao.listarTodosOrdenando(columnName)).serialize();
	}

}