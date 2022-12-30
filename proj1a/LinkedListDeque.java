/**
 * @author jerrymis
 * @version 1.0.0
 * @ClassName LinkedListDeque.java
 * @Description LinkedListDeque
 * @createTime 2022年12月23日 13:56:00
 */
public class LinkedListDeque<T> {
    private int size;
    private Node sentinel;

    private class Node {
        public T item;
        public Node next;
        public Node prev;

        public Node(T i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    public LinkedListDeque() {
        sentinel = new Node(null,null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /**
     * Adds an item of type T to the front of the deque.
     *
     * @param item
     */
    public void addFirst(T item) {
        sentinel.next = new Node(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    /**
     * Adds an item of type T to the back of the deque.
     *
     * @param item
     */
    public void addLast(T item) {
        sentinel.prev = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     *
     * @return
     */
    public boolean isEmpty() {
        return sentinel.next == sentinel;
    }

    /**
     * Returns the number of items in the deque.
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     */
    public void printDeque() {
        Node tmp = sentinel;
        while (tmp.next != sentinel) {
            tmp = tmp.next;
            System.out.print(tmp.item + " ");
        }
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item exists, returns null.
     *
     * @return
     */
    public T removeFirst() {
        T r;
        if (size == 0) {
            return null;
        } else {
            r = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size -= 1;
        }
        return r;
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null.
     *
     * @return
     */
    public T removeLast() {
        T r;
        if (sentinel.prev == sentinel) {
            return null;
        } else {
            r = sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size -= 1;
        }
        return r;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null. Must not alter the deque!
     *
     * @param index
     * @return the item of the given index
     */
    public T get(int index) {
        if (index > size) {
            return null;
        } else {
            Node tmp = sentinel;
            for (int i = 1; i <= index; i++) {
                tmp = tmp.next;
            }
            return tmp.item;
        }
    }

    /**
     * Same as get, but uses recursion.
     *
     * @param index
     * @return the item of the given index
     */
    public T getRecursive(int index) {
        if (index > size) {
            return null;
        } else {
            return getRecursive(sentinel.next, index - 1);
        }
    }

    /**
     * the method that will be use in last method,get with recurion
     *
     * @param node
     * @param index
     * @return
     */
    public T getRecursive(Node node, int index) {
        if (index == 0) {
            return node.item;
        } else {
            return getRecursive(node.next, index - 1);
        }
    }

}
