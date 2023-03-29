package arrayList;

public interface CustomArrayList<E> {
    // Define base
    int size();
    boolean isEmpty();
    boolean contains(Object o);
    Object[] toArray();

    // Define modifiers array
    boolean add(E value);
    boolean removeValue(Object o);
    void clear();
    void trimCapacity();

    // define work with positions
    E get(int index);
    E set(int index, E value);
    boolean add(int index, E value);
    E remove(int index);

    //searching
    int indexOf(Object o);
}
