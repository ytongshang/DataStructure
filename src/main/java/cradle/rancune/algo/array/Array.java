package cradle.rancune.algo.array;

import cradle.rancune.algo.Kt;

import java.util.Arrays;

/**
 * Created by Rancune@126.com on 2020/4/13.
 */
@SuppressWarnings("WeakerAccess")
public class Array<T> {
    private static final int DEFAULT_CAPACITY = 4;
    private static final Object[] EMPTY = {};

    private transient Object[] array;
    private int size;

    public Array() {
        this(0);
    }

    public Array(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity is less than 0");
        } else if (capacity == 0) {
            this.array = EMPTY;
        } else {
            this.array = new Object[capacity];
        }
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index out range");
        }
        //noinspection unchecked
        return (T) array[index];
    }

    public void add(T t) {
        ensureCapacity(size + 1);
        this.array[size++] = t;
    }

    public T set(int index, T t) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index out range");
        }
        //noinspection unchecked
        T old = (T) this.array[index];
        this.array[index] = t;
        return old;
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index out range");
        }
        //noinspection unchecked
        T old = (T) this.array[index];
        int removed = (size - 1) - (index + 1) + 1;
        System.arraycopy(this.array, index + 1, this.array, index, removed);
        // gc
        this.array[--size] = null;
        return old;
    }

    private void ensureCapacity(int capacity) {
        int length = array.length;
        if (length < capacity) {
            int newCapacity = length + length >> 1;
            if (newCapacity < DEFAULT_CAPACITY) {
                newCapacity = DEFAULT_CAPACITY;
            }
            if (newCapacity < capacity) {
                newCapacity = capacity;
            }
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(this.array, 0, newArray, 0, size);
            this.array = newArray;
        }
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Array)) {
            return false;
        }
        Array<?> other = (Array<?>) obj;
        return Arrays.equals(other.array, this.array);
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(array[i]);
            if (i == size - 1)
                return b.append(']').toString();
            b.append(", ");
        }
    }

    public static void main(String[] args) {
        Array<Integer> array = new Array<>();
        array.add(0);
        array.add(1);
        array.add(3);
        array.add(8);
        Kt.print(array);
        array.set(0, 100);
        Kt.print(array);
        Kt.print(array.get(2));
        Kt.print(array.remove(3));
        Kt.print(array);
    }
}
