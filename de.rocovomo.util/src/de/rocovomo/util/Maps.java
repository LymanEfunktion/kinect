package de.rocovomo.util;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Map;

public class Maps {

	public static <K, V> Map<K, V> toMap(Dictionary<K, V> source,
			Map<K, V> destination) {
		for (Enumeration<K> keys = source.keys(); keys.hasMoreElements();) {
			K key = keys.nextElement();
			destination.put(key, source.get(key));
		}
		return destination;
	}

}
