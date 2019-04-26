package br.com.virtuoso.controllers.compra;

import java.util.ResourceBundle;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import br.com.virtuoso.annotations.PrecisaEstarLogado;
import br.com.virtuoso.controllers.AbstractController;
import br.com.virtuoso.daos.compra.ComListaItemDao;
import br.com.virtuoso.exceptions.ControllerException;
import br.com.virtuoso.models.compra.ComListaItem;

@Controller
@Path("/compra/listaitem")
public class ComListaItemController extends AbstractController<ComListaItem> {

	/**
	 * @deprecated Construtor utilizado apenas pelo CDI
	 */
	public ComListaItemController() {
		this(null, null, null, null);
	}

	@Inject
	public ComListaItemController(Result result, Validator validator, ResourceBundle bundle, ComListaItemDao dao) {
		this.result = result;
		this.validator = validator;
		this.bundle = bundle;
		this.dao = dao;
	}

	@Get("/listarTodos/{listaItem.comLista.id}")
	@PrecisaEstarLogado
	public void listarTodos(ComListaItem listaItem) throws ControllerException {
		this.result.use(Results.json()).withoutRoot().from(((ComListaItemDao) this.dao).listarTodos(listaItem))
				.recursive().serialize();
	}

	@Post("/excluirItensPorLista/{idComLista}")
	@PrecisaEstarLogado
	public void excluirItensPorLista(Long idComLista) throws ControllerException {
		((ComListaItemDao) this.dao).excluirItensPorLista(idComLista);
		this.result.use(Results.json()).withoutRoot().from("sucesso").serialize();
	}

}