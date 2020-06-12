package cradle.rancune.algo.heap;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Rancune@126.com on 2019-06-22.
 * 小顶堆
 */
@SuppressWarnings("unchecked")
public class Heap<T> {
    private static final int DEFAULT_CAPACITY = 10;

    private transient Object[] elementData;
    private transient Comparator<? super T> comparator;
    private int size;

    public Heap(int capacity, Comparator<? super T> comparator) {
        if (capacity < 0) {
            throw new IllegalArgumentException();
        }
        if (comparator == null) {
            throw new NullPointerException();
        }
        elementData = new Object[capacity];
        this.comparator = comparator;
        size = 0;
    }

    // 插入是自下向上的堆化
    public void insert(T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        ensureCapacity(size + 1);
        // size个元素，新增一个加到index为size上面
        shiftUpUsingComparator(size, t, elementData, comparator);
        size++;
    }

    private static <T> void shiftUpUsingComparator(int k, T x, Object[] array,
                                                   Comparator<? super T> cmp) {
        while (k > 0) {
            int parentIndex = (k - 1) / 2;
            Object parent = array[parentIndex];
            if (cmp.compare(x, (T) parent) >= 0) {
                // 如果子节点比父节点大了，符合小顶堆
                break;
            }
            array[k] = parent;
            k = parentIndex;
        }
        array[k] = x;
    }

    public void sort() {
        // 堆排序
        // 小顶堆，最后生成的的是从大到小的数据
        if (size == 0 || size == 1) {
            return;
        }
        buildHeay(size);
        // 最小的到了顶部
        for (int i = size - 1; i >= 1; i--) {
            // 交换最顶部与最后一位的值
            swap(0, i);
            heapify(i);
        }
    }

    // heapify一般是自上向下的堆化
    private void buildHeay(int size) {
        if (size <= 1) {
            return;
        }
        int half = (size - 1 - 1) / 2;
        for (int i = half; i >= 1; i--) {
            heapify(i);
        }
    }

    private void heapify(int i) {
        while (true) {
            int min = i;
            if (2 * i + 1 < size && comparator.compare((T) elementData[i], (T) elementData[2 * i + 1]) > 0) {
                min = 2 * i + 1;
            }
            if (2 * i + 2 < size && comparator.compare((T) elementData[min], (T) elementData[2 * i + 2]) > 0) {
                min = 2 * i + 2;
            }
            if (min == i) {
                break;
            }
            swap(i, min);
            i = min;
        }
    }


    private void ensureCapacity(int capacity) {
        capacity = Math.max(capacity, DEFAULT_CAPACITY);
        if (capacity > elementData.length) {
            grow(capacity);
        }
    }

    private void grow(int capacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity < capacity) {
            newCapacity = capacity;
        }
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private void swap(int a, int b) {
        Object temp = elementData[a];
        elementData[a] = elementData[b];
        elementData[b] = temp;
    }
}
