package de.rocovomo.osgi.api;

import java.util.Collection;


public interface ServiceProvider<T> {

	Collection<T> getServiceS();
}
