package kickstarter.storages;

import java.util.Iterator;

import kickstarter.engine.Data;

public interface Storage<T extends Data> {
	void add(T object);

	T get(int id);

	Iterator<T> getIterator();

	int size();

	boolean isEmpty();
}
