package de.rocovomo.util;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Maps {

	public static <K, V> Dictionary<K, V> toDictionary(Map<K, V> source, Dictionary<K, V> destination) {
		Set<K> keys = source.keySet();
		Iterator<K> keyIterator = keys.iterator();
		while (keyIterator.hasNext()) {
			K key = keyIterator.next();
			destination.put(key, source.get(key));
		}
		return destination;
	}
	
	public static <K,V> Dictionary<K, V> toDictionary(Map<K, V> source){
		Dictionary<K, V> destination = new Hashtable<>();
		Set<K> keys = source.keySet();
		Iterator<K> keyIterator = keys.iterator();
		while (keyIterator.hasNext()) {
			K key = keyIterator.next();
			destination.put(key, source.get(key));
		}
		return destination;
	}

}
