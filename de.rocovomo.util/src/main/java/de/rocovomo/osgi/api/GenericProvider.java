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

	/**
	 * @return returns the object of class <code>T</code> encapsulated by this
	 *         object
	 */
	T getProvided();
	/**
	 * @return returns the {@link Type} of the encapsulated object
	 */
	Type getType();
	/**
	 * @return returns a {@link Map} of additional properties for the
	 *         encapsulated object
	 */
	Map<String, Object> getProperties();

}