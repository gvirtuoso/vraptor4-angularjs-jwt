package br.com.virtuoso.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Classe responsavel por encapsular as logicas de criptografia que por ventura
 * o sistema venha utilizar
 * 
 * @author virtuoso
 *
 */
public class Crypto {

	public static String md5(String conteudo) {
		return DigestUtils.md5Hex(conteudo).toString();
	}

}