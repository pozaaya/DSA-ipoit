package by.it.group410972.zayats.lesson10;

import java.util.Deque;
import java.util.Iterator;

public class MyLinkedList<E> implements Deque<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E item, Node<E> next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    // ========================= ОБЯЗАТЕЛЬНЫЕ МЕТОДЫ =========================

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean add(E element) {
        addLast(element);
        return true;
    }

    @Override
    public void addFirst(E element) {
        Node<E> newNode = new Node<>(null, element, head);
        if (head != null) head.prev = newNode;
        head = newNode;
        if (tail == null) tail = head;
        size++;
    }

    @Override
    public void addLast(E element) {
        Node<E> newNode = new Node<>(tail, element, null);
        if (tail != null) tail.next = newNode;
        tail = newNode;
        if (head == null) head = tail;
        size++;
    }

    @Override
    public E getFirst() {
        return (head != null) ? head.item : null;
    }

    @Override
    public E getLast() {
        return (tail != null) ? tail.item : null;
    }

    @Override
    public E element() {
        return getFirst();
    }

    @Override
    public E poll() {
        return pollFirst();
    }

    @Override
    public E pollFirst() {
        if (head == null) return null;
        E result = head.item;
        head = head.next;
        if (head != null) head.prev = null;
        else tail = null;
        size--;
        return result;
    }

    @Override
    public E pollLast() {
        if (tail == null) return null;
        E result = tail.item;
        tail = tail.prev;
        if (tail != null) tail.next = null;
        else head = null;
        size--;
        return result;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node<E> current = head;
        for (int i = 0; i < index; i++) current = current.next;
        E result = current.item;
        unlink(current);
        return result;
    }

    private void unlink(Node<E> node) {
        Node<E> prevNode = node.prev;
        Node<E> nextNode = node.next;

        if (prevNode != null) prevNode.next = nextNode;
        else head = nextNode;

        if (nextNode != null) nextNode.prev = prevNode;
        else tail = prevNode;

        node.item = null;
        node.next = null;
        node.prev = null;
        size--;
    }

    @Override
    public boolean remove(Object element) {
        Node<E> current = head;
        while (current != null) {
            if ((element == null && current.item == null) ||
                    (element != null && element.equals(current.item))) {
                unlink(current);
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> current = head;
        while (current != null) {
            sb.append(current.item);
            if (current.next != null) sb.append(", ");
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    // ========================= Методы для Deque =========================
    @Override
    public E pop() { return pollFirst(); }

    @Override
    public void push(E e) { addFirst(e); }

    @Override
    public boolean offer(E e) { return add(e); }

    @Override
    public boolean offerFirst(E e) { addFirst(e); return true; }

    @Override
    public boolean offerLast(E e) { addLast(e); return true; }

    @Override
    public E peek() { return getFirst(); }

    @Override
    public E peekFirst() { return getFirst(); }

    @Override
    public E peekLast() { return getLast(); }

    // ========================= Не поддерживаемые методы =========================
    @Override public E remove() { throw new UnsupportedOperationException(); }
    @Override public E removeFirst() { throw new UnsupportedOperationException(); }
    @Override public E removeLast() { throw new UnsupportedOperationException(); }
    @Override public boolean contains(Object o) { throw new UnsupportedOperationException(); }
    @Override public Iterator<E> iterator() { throw new UnsupportedOperationException(); }
    @Override public Iterator<E> descendingIterator() { throw new UnsupportedOperationException(); }
    @Override public Object[] toArray() { throw new UnsupportedOperationException(); }
    @Override public <T> T[] toArray(T[] a) { throw new UnsupportedOperationException(); }
    @Override public boolean removeFirstOccurrence(Object o) { throw new UnsupportedOperationException(); }
    @Override public boolean removeLastOccurrence(Object o) { throw new UnsupportedOperationException(); }
    @Override public boolean addAll(java.util.Collection<? extends E> c) { throw new UnsupportedOperationException(); }
    @Override public void clear() { throw new UnsupportedOperationException(); }
    @Override public boolean retainAll(java.util.Collection<?> c) { throw new UnsupportedOperationException(); }
    @Override public boolean removeAll(java.util.Collection<?> c) { throw new UnsupportedOperationException(); }
    @Override public boolean containsAll(java.util.Collection<?> c) { throw new UnsupportedOperationException(); }

}
