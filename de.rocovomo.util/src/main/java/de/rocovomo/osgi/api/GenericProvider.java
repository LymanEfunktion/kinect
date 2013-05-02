package de.rocovomo.osgi.api;

import java.util.Map;

/**
 * 
 * @author voowoo <a href="mailto:vowe91@gmail.com">vowe91@gmail.com</a>
 * 
 * @param <T>
 *            class which is provided by the provider
 */
public interface GenericProvider<T> {

	T getProvided();
	Type getType();
	Map<String, Object> getProperties();

}