package by.it.group410972.zayats.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListC<E> implements List<E> {

    private E[] data;
    private int size;

    @SuppressWarnings("unchecked")
    public ListC() {
        data = (E[]) new Object[10];
        size = 0;
    }

    // -------------------- ВСПОМОГАТЕЛЬНЫЕ --------------------

    private void ensureCapacity(int capacity) {
        if (capacity <= data.length)
            return;
        int newCap = data.length * 2;
        if (newCap < capacity)
            newCap = capacity;

        @SuppressWarnings("unchecked")
        E[] newData = (E[]) new Object[newCap];
        for (int i = 0; i < size; i++)
            newData[i] = data[i];
        data = newData;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
    }

    // -------------------- ОБЯЗАТЕЛЬНЫЕ --------------------

    @Override
    public String toString() {
        if (size == 0) return "[]";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean add(E e) {
        ensureCapacity(size + 1);
        data[size++] = e;
        return true;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        E removed = data[index];
        for (int i = index; i < size - 1; i++)
            data[i] = data[i + 1];
        data[--size] = null;
        return removed;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        ensureCapacity(size + 1);
        for (int i = size; i > index; i--)
            data[i] = data[i - 1];
        data[index] = element;
        size++;
    }

    @Override
    public boolean remove(Object o) {
        int idx = indexOf(o);
        if (idx >= 0) {
            remove(idx);
            return true;
        }
        return false;
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);
        E old = data[index];
        data[index] = element;
        return old;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++)
            data[i] = null;
        size = 0;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (data[i] == null) return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(data[i])) return i;
        }
        return -1;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return data[index];
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size - 1; i >= 0; i--)
                if (data[i] == null) return i;
        } else {
            for (int i = size - 1; i >= 0; i--)
                if (o.equals(data[i])) return i;
        }
        return -1;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c)
            if (!contains(o)) return false;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean modified = false;
        for (E e : c) {
            add(e);
            modified = true;
        }
        return modified;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        int count = c.size();
        if (count == 0) return false;

        ensureCapacity(size + count);
        for (int i = size - 1; i >= index; i--)
            data[i + count] = data[i];

        int i = index;
        for (E e : c)
            data[i++] = e;

        size += count;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (int i = 0; i < size; i++) {
            if (c.contains(data[i])) {
                remove(i--);
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        for (int i = 0; i < size; i++) {
            if (!c.contains(data[i])) {
                remove(i--);
                modified = true;
            }
        }
        return modified;
    }

    // -------------------- ОПЦИОНАЛЬНЫЕ --------------------

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        for (int i = 0; i < size; i++)
            arr[i] = data[i];
        return arr;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public E next() {
                return data[cursor++];
            }
        };
    }
}
