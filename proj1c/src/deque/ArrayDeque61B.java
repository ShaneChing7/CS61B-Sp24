package deque;

import org.apache.commons.collections.iterators.ArrayIterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class ArrayDeque61B<T> implements Deque61B<T> {

    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;
    private static final int INITIAL_CAPACITY = 8;
    private int capacity;
    private static final int factor = 2;

    public ArrayDeque61B() {
        items = (T[]) new Object[INITIAL_CAPACITY];
        capacity = items.length;
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }

    public void resize(int newCapacity) {
        if(size == 0) {
            return;
        }
        T[] newItems = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newItems[i] = get(i);
        }

        this.capacity = newCapacity;
        nextFirst = newCapacity - 1;
        nextLast = size;

        items = newItems;
    }

    @Override
    public void addFirst(T x) {
        if (size == capacity) {
            resize(factor * capacity);
        }
        items[nextFirst] = x;
        nextFirst = Math.floorMod(nextFirst - 1, capacity);
        size++;
    }

    @Override
    public void addLast(T x) {
        if (size == capacity) {
            resize(factor * capacity);
        }
        items[nextLast] = x;
        nextLast = Math.floorMod(nextLast + 1, capacity);
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(items[(nextFirst + 1 + i) % capacity]);
        }
        return list;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        if (capacity > 15 && size <= capacity /4) {
            resize(capacity / 2);
        }
        nextFirst = Math.floorMod(nextFirst + 1, capacity);
        T item = items[nextFirst];
        items[nextFirst] = null;
        size--;

        return item;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if (capacity > 15 && size <= capacity /4) {
            resize(capacity / 2);
        }
        nextLast = Math.floorMod(nextLast - 1, capacity);
        T item = items[nextLast];
        items[nextLast] = null;
        size--;

        return item;
    }

    @Override
    public T get(int index) {
        if (index > size || index < 0) {
            return null;
        }
        return items[(nextFirst + 1 + index) % capacity];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<T>{

        int seer;
        int index;

        ArrayIterator(){
            seer = 0;
            index = Math.floorMod(nextFirst + 1, items.length);
        }

        @Override
        public boolean hasNext() {
            return seer < size;
        }

        @Override
        public T next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            T x = items[index];
            index = Math.floorMod(index + 1,items.length);
            seer += 1;
            return x;
        }
    }

    @Override
    public boolean equals(Object other){
        if(this == other){
            return true;
        }
        if(other instanceof ArrayDeque61B<?> array) {
            int index = Math.floorMod(nextFirst + 1, capacity);
            int otherIndex = Math.floorMod(((ArrayDeque61B<?>) other).nextFirst + 1, ((ArrayDeque61B<?>) other).capacity);
            for (int i = 0; i < size; i++) {
                if (items[index] == ((ArrayDeque61B<?>) other).items[otherIndex]){
                    index = Math.floorMod(index + 1,capacity);
                    otherIndex = Math.floorMod(otherIndex + 1,capacity);

                }else{
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int index = Math.floorMod(nextFirst + 1, capacity);
        for(int i = 0 ;i < size;i ++){
            T item = items[index];
            if(i == size - 1){
                sb.append(item);
            }else {
                sb.append(item).append(", ");
            }
            index = Math.floorMod(index + 1,capacity);
        }
        sb.append("]");
        return sb.toString();
    }
}

