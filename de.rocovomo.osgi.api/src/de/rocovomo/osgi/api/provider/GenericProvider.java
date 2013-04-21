package de.rocovomo.osgi.api;

import java.util.Dictionary;
import java.util.UUID;

/**
 * 
 * @author voowoo <a href="mailto:vowe91@gmail.com">vowe91@gmail.com</a>
 * 
 * @param <T>
 *            class which is provided by the provider
 */
public interface GenericProvider<T> {

	T getProvided();
	UUID getPid();
	String getType();
	Dictionary<String, Object> getProperties();

}