package Auxiliar;

import java.util.Arrays;
import java.util.Iterator;

public class MyArrayList<T> implements Iterable<T> {
	private T[] elements;
	private int size;

	// Constructor without initial size (= 0).
	@SuppressWarnings("unchecked")
	public MyArrayList() {
		elements = (T[]) new Object[0];
	}

	@SuppressWarnings("unchecked")
	public MyArrayList(int num) {
		elements = (T[]) new Object[num];
		size = num;
	}

	// Constructor that allows to deep copy (without references) another ArrayList.
	public MyArrayList(MyArrayList<T> originalArray) {
		elements = (T[]) Arrays.copyOf(originalArray.toArray(), originalArray.size(), Object[].class);
		size = originalArray.size();
	}

	// Constructor that allows to deep copy (without references) another list.
	public MyArrayList(T[] originalArray) {
		elements = originalArray;
		size = originalArray.length;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return (size == 0);
	}

	public boolean contains(Object o) {

		for (T element : elements) {
			if (o == element) {
				return true;
			}
		}

		return false;
	}

	public T[] toArray() {
		return (T[]) elements;
	}

	@SuppressWarnings("unchecked")
	public T[] toArray(T[] array) {
		return (T[]) Arrays.copyOf(elements, size, array.getClass());
	}

	@SuppressWarnings("unchecked")
	public void add(T o) {
		size++;

		Object[] temporalArray = Arrays.copyOf(elements, size);
		temporalArray[size - 1] = o;
		elements = (T[]) temporalArray;
	}

	@SuppressWarnings("unchecked")
	public void remove(T o) {
		if (size > 0) {
			size--;
			Object[] temporalArray = new Object[size];

			int objectIndex = 0;
			for (T element : elements) {
				if (o != element) {
					temporalArray[objectIndex] = element;
					objectIndex++;
				}
			}

			elements = (T[]) temporalArray;
		}
	}

	@SuppressWarnings("unchecked")
	public void clear() {
		elements = (T[]) new Object[0];
	}

	public T get(int index) {

		if (size > index) {
			return elements[index];
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public void set(int index, Object newElement) {
		if (size > index) {
			elements[index] = (T) newElement;
		}
	}

	@SuppressWarnings("unchecked")
	public boolean remove(int index) {
		if (size > index) {
			size--;
			Object[] temporalArray = new Object[size];

			int objectIndex = 0;
			for (int i = 0; i <= size; i++) {
				if (i != index) {
					temporalArray[objectIndex] = elements[i];
					objectIndex++;
				}
			}

			elements = (T[]) temporalArray;
			return true;
		}

		return false;
	}

	@Override
	public String toString() {
		StringBuilder array = new StringBuilder();

		for (int i = 0; i < size; i++) {
			if (i == size - 1) {
				array.append(elements[i]);
			}
			else {
				array.append(elements[i]).append(" ");
			}
		}

		return String.valueOf(array);
	}

	public int indexOf(Object o) {

		for (int elementIndex = 0; elementIndex < size; elementIndex++) {
			if (elements[elementIndex] == o) {
				return elementIndex;
			}
		}

		return -1;
	}

	@SuppressWarnings("unchecked")
	public T[] subList(int fromIndex, int toIndex) {

		if (fromIndex < size && toIndex < size) {
			int offset = fromIndex - toIndex;
			Object[] temporalArray = new Object[offset];

			System.arraycopy(elements, fromIndex, temporalArray, 0, offset);

			return (T[]) temporalArray;
		}

		return null;
	}


	// Implement the interface Iterable and override the method iterator().
	// This code is based on: https://stackoverflow.com/questions/5849154/can-we-write-our-own-iterator-in-java
	@Override
	public Iterator<T> iterator() {
		return new Iterator<>() {

			private int index = 0;

			@Override
			public boolean hasNext() {
				return (index < size) && (elements[index] != null);
			}

			@Override
			public T next() {
				return elements[index++];
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}
}