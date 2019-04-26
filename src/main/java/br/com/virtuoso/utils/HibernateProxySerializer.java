package br.com.virtuoso.utils;

import java.lang.reflect.Type;

import org.hibernate.proxy.HibernateProxy;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * https://gist.github.com/dipold/9781039 Classe responsavel por serializar o
 * proxy do hibernate quando manipula Json
 *
 */
public class HibernateProxySerializer implements JsonSerializer<HibernateProxy> {

	@Override
	public JsonElement serialize(HibernateProxy src, Type typeOfSrc, JsonSerializationContext context) {
		Object deProxied = src.getHibernateLazyInitializer().getImplementation();

		return context.serialize(deProxied);
	}
}