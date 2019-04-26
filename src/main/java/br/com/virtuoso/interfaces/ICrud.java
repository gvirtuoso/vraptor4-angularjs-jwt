package br.com.virtuoso.interfaces;

import br.com.virtuoso.models.Persistivel;

public interface ICrud<T extends Persistivel, E extends RuntimeException> {

	public void salvar(T objeto) throws E;

	public void alterar(T objeto) throws E;

	public void excluir(T objeto) throws E;

}