package de.rocovomo.osgi.api;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.UUID;

/**
 * Implementation for the Interface {@link GenericProvider}
 * 
 * @author voowoo <a href="mailto:vowe91@gmail.com">vowe91@gmail.com</a>
 * 
 * @param <T>
 *            class which is provided by the provider
 */
public abstract class AbstractGenericProvider<T> implements GenericProvider<T> {

	private final UUID PID = UUID.randomUUID();
	private final T provided;
	private Dictionary<String, Object> properties = new Hashtable<String, Object>();

	public AbstractGenericProvider(T provided) {
		this.provided = provided;
	}

	/**
	 * returns the class which is provided by this provider
	 */
	@Override
	public T getProvided() {
		return this.provided;
	}

	@Override
	public Dictionary<String, Object> getProperties() {
		return this.properties;
	}

	protected void addProperty(String key, Object value) {
		this.properties.put(key, value);
	}

	public UUID getPid() {
		return this.PID;
	}
}
