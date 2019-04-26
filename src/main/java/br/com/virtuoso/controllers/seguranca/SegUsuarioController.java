package br.com.virtuoso.controllers.seguranca;

import java.util.ResourceBundle;

import javax.inject.Inject;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import br.com.virtuoso.controllers.AbstractController;
import br.com.virtuoso.daos.seguranca.SegUsuarioDao;
import br.com.virtuoso.exceptions.ControllerException;
import br.com.virtuoso.interfaces.IKeys;
import br.com.virtuoso.models.seguranca.SegUsuario;
import br.com.virtuoso.utils.Crypto;
import br.com.virtuoso.utils.JWT;
import br.com.virtuoso.wrappers.seguranca.SegUsuarioLoginWrapper;

@Controller
@Path("/seguranca/usuario")
public class SegUsuarioController extends AbstractController<SegUsuario> {

	/**
	 * @deprecated Construtor utilizado apenas pelo CDI
	 */
	public SegUsuarioController() {
		this(null, null, null, null);
	}

	@Inject
	public SegUsuarioController(Result result, Validator validator, ResourceBundle bundle, SegUsuarioDao dao) {
		this.result = result;
		this.validator = validator;
		this.bundle = bundle;
		this.dao = dao;
	}

	@Override
	@Post
	@Consumes(value = "application/json")
	public void salvar(SegUsuario objeto) throws ControllerException {

		boolean isEmailEmUso = ((SegUsuarioDao) this.dao).isEmailEmUso(objeto.getEmail());
		String msgValidacao = bundle.getString(IKeys.APP_SEGURANCA_USUARIO_SALVAR_EMAIL_JA_EM_USO);

		validator.addIf(isEmailEmUso, new SimpleMessage("email", msgValidacao));
		validator.onErrorSendBadRequest();

		// Criptografando a senha para MD5
		objeto.setSenha(Crypto.md5(objeto.getSenha()));

		super.salvar(objeto);
	}

	@Post
	@Consumes(value = "application/json")
	public void login(SegUsuario objeto) throws ControllerException {

		// Criptografando a senha para MD5
		objeto.setSenha(Crypto.md5(objeto.getSenha()));

		SegUsuario usuario = ((SegUsuarioDao) this.dao).doLogin(objeto);

		boolean isLoginValido = usuario != null;
		String msgLoginSenhaNaoConferem = bundle.getString(IKeys.APP_SEGURANCA_USUARIO_LOGIN_EMAIL_SENHA_NAO_CONFEREM);

		validator.addIf(!isLoginValido, new SimpleMessage("login", msgLoginSenhaNaoConferem));
		validator.onErrorSendBadRequest();

		String token = JWT.generateToken(usuario.getEmail());

		SegUsuarioLoginWrapper wrapper = new SegUsuarioLoginWrapper(token, usuario);
		this.result.use(Results.json()).withoutRoot().from(wrapper).include("usuario").serialize();

	}
}