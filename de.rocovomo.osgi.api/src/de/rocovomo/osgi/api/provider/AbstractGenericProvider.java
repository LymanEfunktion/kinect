package de.rocovomo.osgi.api.provider;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

/**
 * abstract generic base class for all Provider Implementations
 * 
 * @author voowoo <a href="mailto:vowe91@gmail.com">vowe91@gmail.com</a>
 * 
 * @param <T>
 *            class to be provieded by this implementation
 */
public abstract class AbstractGenericProvider<T> implements GenericProvider<T>{

	private final T provided;
	private final Type type;
	private Map<String, Object> properties;
	/**
	 * Constructor
	 * 
	 * @param provided
	 *            provided object of class <code>T</code>
	 * @param type
	 *            {@link Type} of the provided object
	 * @see {@link Type}
	 */
	public AbstractGenericProvider(T provided, Type type) {
		this.properties = new Hashtable<>();
		this.provided = provided;
		this.type = type;
	}

	/**
	 * Constructor
	 * 
	 * @param provided
	 *            provided object of class <code>T</code>
	 * @param type
	 *            {@link Type} of the provided object
	 * @param properties
	 *            additional properties
	 */
	public AbstractGenericProvider(T provided, Type type,
			Map<String, Object> properties) {
		this.provided = provided;
		this.type = type;
		this.properties = new HashMap<>();
		this.properties.putAll(properties);
	}

	/**
	 * @return the provided object of class <code>T</code>
	 */
	public T getProvided() {
		return this.provided;
	}

	/**
	 * @return type {@link Type} of the provided object
	 * @see {@link Type}
	 */
	public Type getType() {
		return this.type;
	}

	public Set<String> getKeys() {
		return this.properties.keySet();
	}

	public Object getProperty(Object key) {
		return properties.get(key);
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

}
