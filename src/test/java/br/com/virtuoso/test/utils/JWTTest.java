package br.com.virtuoso.test.utils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.virtuoso.utils.JWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.MalformedJwtException;

public class JWTTest {

	@Test
	public void deveSerPossivelGerarUmToken() {

		String chavePublica = "meu_teste_de_token";
		String token = JWT.generateToken(chavePublica);

		assertNotNull("Deve ser gerado um token ao informar uma chave publica", token);
	}

	@Test
	public void deveSerPossivelValidarUmTokenAPartirDeUmaChavePublica() {

		String chavePublica = "meu_teste_de_token";
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtZXVfdGVzdGVfZGVfdG9rZW4ifQ.5wRZ4FnFxMib4BTxL5kb_Qv1O_dBddXPJZSADu238i8";

		Claims claims = JWT.parseToken(token);
		boolean isTokenValido = claims.getSubject().equals(chavePublica);

		assertTrue("Deve ser possivel validar um token a partir de uma chave publica", isTokenValido);
	}

	@Test
	public void deveFalharAValidacaoAoPassarUmaChavePublicaNaoCompativel() {

		String chavePublica = "outra_chave_publica_qualquer";
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtZXVfdGVzdGVfZGVfdG9rZW4ifQ.5wRZ4FnFxMib4BTxL5kb_Qv1O_dBddXPJZSADu238i8";

		Claims claims = JWT.parseToken(token);
		boolean isTokenInvalido = !claims.getSubject().equals(chavePublica);

		assertTrue("Deve falhar a validação ao passar uma chave publica diferente da responsavel pela geracao do token",
				isTokenInvalido);
	}

	@Test(expected = MalformedJwtException.class)
	public void deveFalharAValidacaoAoPassarUmTokenInvalido() {

		// Token valido....:
		// eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtZXVfdGVzdGVfZGVfdG9rZW4ifQ.5wRZ4FnFxMib4BTxL5kb_Qv1O_dBddXPJZSADu238i8
		// Token Alterado..:
		// eyJhb___OiJIUzI1NiJ9.eyJzdW_______XVfdGVzdGVfZGVfdG9rZW4ifQ.5wRZ4Fn_____4BTxL5kb_Qv1O_dBddXPJZSADu238i8

		String token = "eyJhb___OiJIUzI1NiJ9.eyJzdW_______XVfdGVzdGVfZGVfdG9rZW4ifQ.5wRZ4Fn_____4BTxL5kb_Qv1O_dBddXPJZSADu238i8";
		JWT.parseToken(token);

	}

}