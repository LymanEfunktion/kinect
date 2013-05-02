package de.rocovomo.osgi.api.provider;

import java.util.Dictionary;
import java.util.UUID;

/**
 * TODO: to be removed
 * @author voowoo <a href="mailto:vowe91@gmail.com">vowe91@gmail.com</a>
 * 
 * @param <T>
 *            class which is provided by the provider
 */
@Deprecated
public interface GenericProvider<T> {

	T getProvided();
	UUID getPid();
	String getType();
	Dictionary<String, Object> getProperties();

}