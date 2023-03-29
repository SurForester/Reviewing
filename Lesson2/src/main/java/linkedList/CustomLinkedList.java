package linkedList;

public interface CustomLinkedList<E> extends Iterable<E> {
    int size();
    boolean contains(E value);
    boolean isEmpty();
    void insertAtIndex(E value, int index);
    void insertHead(E value);
    void insertTail(E value);
    boolean remove(E value);
    void removeAtIndex(int index);
    E removeHead();
    E removeTail();
    E get(int index);
    E getFirst();
    E getLast();

    void printList();

    class Node<E> {
        E value;
        Node<E> next;
        Node<E> previous;

        public Node(E value, Node<E> next, Node<E> previous) {
            this.value = value;
            this.next = next;
            this.previous = previous;
        }
    }
}
