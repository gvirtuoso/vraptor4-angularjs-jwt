package br.com.virtuoso.test.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import br.com.virtuoso.utils.Crypto;

public class CryptoTest {

	@Test
	public void deveGerarSempreAMesmaChaveParaUmMesmoValor() {

		String conteudo = "ValorParaSerTransformadoEmMD5";

		String chave01 = Crypto.md5(conteudo);
		String chave02 = Crypto.md5(conteudo);

		assertEquals("O mesmo conteudo deve sempre gerar o mesmo valor MD5", chave01, chave02);
	}

	@Test
	public void deveGerarChavesDiferentesParaValoresDiferentes() {

		String conteudo01 = "ValorParaSerTransformadoEmMD5";
		String conteudo02 = "OutroValorParaSerTransformadoEmMD5";

		String chave01 = Crypto.md5(conteudo01);
		String chave02 = Crypto.md5(conteudo02);

		assertNotEquals("Para conteúdos diferentes devem ser gerados chaves com diferentes valores MD5", chave01,
				chave02);
	}
}