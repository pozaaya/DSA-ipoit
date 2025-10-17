package by.it.group410972.zayats.lesson10;

import java.util.Deque;
import java.util.Iterator;

public class MyArrayDeque<E> implements Deque<E> {

    private E[] data;
    private int size;
    private int head;
    private int tail;

    @SuppressWarnings("unchecked")
    public MyArrayDeque() {
        data = (E[]) new Object[8];
        size = 0;
        head = 0;
        tail = 0;
    }

    // ========================= ВСПОМОГАТЕЛЬНЫЕ =========================
    private int next(int index) {
        return (index + 1) % data.length;
    }

    private int prev(int index) {
        return (index - 1 + data.length) % data.length;
    }

    @SuppressWarnings("unchecked")
    private void grow() {
        E[] newData = (E[]) new Object[data.length * 2];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(head + i) % data.length];
        }
        data = newData;
        head = 0;
        tail = size;
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
        if (size == data.length) grow();
        head = prev(head);
        data[head] = element;
        size++;
    }

    @Override
    public void addLast(E element) {
        if (size == data.length) grow();
        data[tail] = element;
        tail = next(tail);
        size++;
    }

    @Override
    public E element() {
        return getFirst();
    }

    @Override
    public E getFirst() {
        if (size == 0) return null;
        return data[head];
    }

    @Override
    public E getLast() {
        if (size == 0) return null;
        return data[prev(tail)];
    }

    @Override
    public E poll() {
        return pollFirst();
    }

    @Override
    public E pollFirst() {
        if (size == 0) return null;
        E result = data[head];
        data[head] = null;
        head = next(head);
        size--;
        return result;
    }

    @Override
    public E pollLast() {
        if (size == 0) return null;
        tail = prev(tail);
        E result = data[tail];
        data[tail] = null;
        size--;
        return result;
    }

    @Override
    public String toString() {
        if (size == 0) return "[]";
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[(head + i) % data.length]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    // ========================= Методы для компиляции =========================

    @Override
    public E pop() {
        return pollFirst();
    }

    @Override
    public void push(E e) {
        addFirst(e);
    }

    @Override
    public boolean offer(E e) {
        return add(e);
    }

    @Override
    public boolean offerFirst(E e) {
        addFirst(e);
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        addLast(e);
        return true;
    }

    @Override
    public E peek() {
        return getFirst();
    }

    @Override
    public E peekFirst() {
        return getFirst();
    }

    @Override
    public E peekLast() {
        return getLast();
    }

    // ========================= Не поддерживаемые методы =========================
    @Override public boolean remove(Object o) { throw new UnsupportedOperationException(); }
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
