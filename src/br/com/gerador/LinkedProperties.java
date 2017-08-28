package br.com.gerador;

import java.util.*;
import java.io.*;

public class LinkedProperties extends Properties {
	private static final long serialVersionUID = 1L;
	private Map<Object, Object> linkMap = new java.util.LinkedHashMap();

	public void clear() {
		this.linkMap.clear();
	}

	public boolean contains(Object value) {
		return this.linkMap.containsValue(value);
	}

	public boolean containsKey(Object key) {
		return this.linkMap.containsKey(key);
	}

	public boolean containsValue(Object value) {
		return this.linkMap.containsValue(value);
	}

	public Enumeration elements() {
		throw new RuntimeException("Method elements is not supported in LinkedProperties class");
	}

	public Set entrySet() {
		return this.linkMap.entrySet();
	}

	public boolean equals(Object o) {
		return this.linkMap.equals(o);
	}

	public Object get(Object key) {
		return this.linkMap.get(key);
	}

	public String getProperty(String key) {
		Object oval = get(key);
		if (oval == null)
			return null;
		return (oval instanceof String) ? (String) oval : null;
	}

	public boolean isEmpty() {
		return this.linkMap.isEmpty();
	}

	public Enumeration keys() {
		Set keys = this.linkMap.keySet();
		return Collections.enumeration(keys);
	}

	public Set keySet() {
		return this.linkMap.keySet();
	}

	public void list(PrintStream out) {
		list(new PrintWriter(out, true));
	}

	public Object put(Object key, Object value) {
		return this.linkMap.put(key, value);
	}

	public int size() {
		return this.linkMap.size();
	}

	public Collection values() {
		return this.linkMap.values();
	}
}