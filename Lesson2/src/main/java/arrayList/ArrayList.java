package arrayList;

import java.util.Arrays;

public class ArrayList<E> implements CustomArrayList<E> {
    protected E arr[];
    protected int size;
    protected int capacity;
    protected int START_CAPACITY = 10;

    public ArrayList() {
        capacity = START_CAPACITY;
        arr = (E[]) new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(0) >= 0;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(arr, size);
    }

    @Override
    public boolean add(E value) {
        return add(size, value);
    }

    @Override
    public boolean removeValue(Object o) {
        int i = indexOf(o);
        return remove(i).equals(o);
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            arr[i] = null;
        }
        size = 0;
        trimCapacity();
    }

    private E[] newCapacity(int capacity) {
        this.capacity = capacity;
        Object[] newArr = new Object[capacity];
        System.arraycopy(arr, 0, newArr, 0, size);
        return (E[]) newArr;
    }

    @Override
    public void trimCapacity() {
        if (size > 0) {
            arr = newCapacity(size);
        } else {
            arr = newCapacity(START_CAPACITY);
        }
    }

    @Override
    public E get(int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        } else {
            return arr[index];
        }
    }

    @Override
    public E set(int index, E value) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        } else {
            arr[index] = value;
            return arr[index];
        }
    }

    @Override
    public boolean add(int index, E value) {
        if (index < 0 || index > size()) {
            System.out.println("Index: " + index + " out of bonds, Size: " + size);
            return false;
        }
        if (capacity * 3 / 4 < size) {
            arr = newCapacity(capacity / 2 * 3 + 1);
        }
        if (index < size) {
            System.arraycopy(arr, index, arr, index + 1, size - index);
        }
        arr[index] = value;
        size++;
        return true;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index > size()) {
            System.out.println("Index: " + index + " out of bonds, Size: " + size);
            return null;
        }
        E removedElement = arr[index];
        final int newSize;
        if ((newSize = size - 1) > index)
            System.arraycopy(arr, index + 1, arr, index, newSize - index);
        arr[size = newSize] = null;
        return removedElement;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (arr[i] == null) return i;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(arr[i])) return i;
            }
        }
        return -1;
    }
}
