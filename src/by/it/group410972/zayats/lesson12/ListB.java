package by.it.group410972.zayats.lesson12;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListB<E> implements List<E> {

    private static class Node<E> {
        E value;
        Node<E> next;

        Node(E value) {
            this.value = value;
        }
    }

    private Node<E> head;
    private int size;

    // ---------------- ОБЯЗАТЕЛЬНЫЕ ----------------

    @Override
    public String toString() {
        if (size == 0) return "[]";
        StringBuilder sb = new StringBuilder("[");
        Node<E> cur = head;
        while (cur != null) {
            sb.append(cur.value);
            if (cur.next != null) sb.append(", ");
            cur = cur.next;
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean add(E e) {
        if (head == null) {
            head = new Node<>(e);
        } else {
            Node<E> cur = head;
            while (cur.next != null) cur = cur.next;
            cur.next = new Node<>(e);
        }
        size++;
        return true;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        if (index == 0) {
            E val = head.value;
            head = head.next;
            size--;
            return val;
        }
        Node<E> prev = node(index - 1);
        E val = prev.next.value;
        prev.next = prev.next.next;
        size--;
        return val;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        if (index == 0) {
            Node<E> n = new Node<>(element);
            n.next = head;
            head = n;
        } else {
            Node<E> prev = node(index - 1);
            Node<E> n = new Node<>(element);
            n.next = prev.next;
            prev.next = n;
        }
        size++;
    }

    @Override
    public boolean remove(Object o) {
        if (head == null) return false;
        if (o == null ? head.value == null : o.equals(head.value)) {
            head = head.next;
            size--;
            return true;
        }
        Node<E> cur = head;
        while (cur.next != null) {
            if (o == null ? cur.next.value == null : o.equals(cur.next.value)) {
                cur.next = cur.next.next;
                size--;
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);
        Node<E> n = node(index);
        E old = n.value;
        n.value = element;
        return old;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public int indexOf(Object o) {
        int i = 0;
        for (Node<E> cur = head; cur != null; cur = cur.next, i++) {
            if (o == null ? cur.value == null : o.equals(cur.value))
                return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int idx = -1;
        int i = 0;
        for (Node<E> cur = head; cur != null; cur = cur.next, i++) {
            if (o == null ? cur.value == null : o.equals(cur.value))
                idx = i;
        }
        return idx;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return node(index).value;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    // ---------------- НЕ ОБЯЗАТЕЛЬНЫЕ ----------------

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> cur = head;

            @Override
            public boolean hasNext() {
                return cur != null;
            }

            @Override
            public E next() {
                E val = cur.value;
                cur = cur.next;
                return val;
            }
        };
    }

    @Override public boolean containsAll(Collection<?> c) { throw new UnsupportedOperationException(); }
    @Override public boolean addAll(Collection<? extends E> c) { throw new UnsupportedOperationException(); }
    @Override public boolean addAll(int index, Collection<? extends E> c) { throw new UnsupportedOperationException(); }
    @Override public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); }
    @Override public boolean retainAll(Collection<?> c) { throw new UnsupportedOperationException(); }
    @Override public List<E> subList(int fromIndex, int toIndex) { throw new UnsupportedOperationException(); }
    @Override public ListIterator<E> listIterator() { throw new UnsupportedOperationException(); }
    @Override public ListIterator<E> listIterator(int index) { throw new UnsupportedOperationException(); }
    @Override public Object[] toArray() { throw new UnsupportedOperationException(); }
    @Override public <T> T[] toArray(T[] a) { throw new UnsupportedOperationException(); }

    // ---------------- ВСПОМОГАТЕЛЬНЫЕ ----------------

    private Node<E> node(int index) {
        Node<E> cur = head;
        for (int i = 0; i < index; i++) cur = cur.next;
        return cur;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
    }
}
