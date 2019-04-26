package br.com.virtuoso.interceptors;

import java.util.ResourceBundle;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.AcceptsWithAnnotations;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;
import br.com.caelum.vraptor.view.Results;
import br.com.virtuoso.annotations.PrecisaEstarLogado;
import br.com.virtuoso.interfaces.IKeys;
import br.com.virtuoso.utils.JWT;

@Intercepts
@RequestScoped
@AcceptsWithAnnotations(PrecisaEstarLogado.class)
public class PrecisaEstarLogadoInterceptor {

	private HttpServletRequest request;
	private Result result;
	private ResourceBundle bundle;

	/**
	 * @deprecated Construtor utilizado apenas pelo CDI
	 */
	public PrecisaEstarLogadoInterceptor() {
		this(null, null, null);
	}

	@Inject
	public PrecisaEstarLogadoInterceptor(HttpServletRequest request, Result result, ResourceBundle bundle) {
		this.request = request;
		this.result = result;
		this.bundle = bundle;
	}

	@AroundCall
	public void intercept(SimpleInterceptorStack stack) {

		boolean isToeknValido = false;
		String token = request.getHeader("authorization");

		if (token != null) {

			String[] tokenArray = token.split(" ");
			String emailUsuario = tokenArray[0];
			String jwtToken = tokenArray[1];

			isToeknValido = JWT.parseToken(jwtToken).getSubject().equals(emailUsuario);
		}

		if (isToeknValido) {
			stack.next();
		} else {
			result.use(Results.http()).setStatusCode(401);
			result.use(Results.json())
					.from(bundle.getString(IKeys.APP_SEGURANCA_USUARIO_LOGIN_ACESSO_NAO_AUTORIZADO), "msg").serialize();
		}

	}

}