package Entities;

import java.util.Arrays;

public class myArrayList<T> {
	private T[] elements;
	private int size;

	// Constructor without initial size (= 0).
	@SuppressWarnings("unchecked")
	public myArrayList() {
		elements = (T[]) new Object[0];
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
		return elements;
	}

	@SuppressWarnings("unchecked")
	public void add(Object o) {
		size++;

		Object[] temporalArray = Arrays.copyOf(elements, size);
		temporalArray[size - 1] = o;
		elements = (T[]) temporalArray;
	}

	@SuppressWarnings("unchecked")
	public void remove(Object o) {
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
			size++;
			Object[] temporalArray = new Object[size];

			// Copy array until index
			for (int i = 0; i < index; i++) {
				temporalArray[i] = elements[i];
			}

			temporalArray[index] = newElement;

			// Copy rest of array
			for (int i = 0; i < size; i++) {
				temporalArray[i + index + 1] = elements[index + i];
			}

			elements = (T[]) temporalArray;
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

			for (int i = 0; i < offset; i++) {
				temporalArray[i] = elements[fromIndex + i];
			}

			return (T[]) temporalArray;
		}

		return null;
	}
}
