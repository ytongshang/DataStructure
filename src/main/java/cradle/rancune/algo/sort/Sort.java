package cradle.rancune.algo.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rancune@126.com on 2019-06-12.
 */
@SuppressWarnings({"unused", "WeakerAccess", "Duplicates"})
public class Sort {

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**
     * 从第0个开始，两两比较，如果前面的比后面的大，则交换位置，一次冒泡后，最大的到了最右边
     * 最好：O(n)
     * 最坏/平均：O(n*n)
     * 原地排序
     * 稳定排序
     */
    public static void bubbleSort(int[] a) {
        if (a == null) {
            return;
        }
        int size = a.length;
        if (size <= 1) {
            return;
        }
        // 只要size -1个有序了，最后一个也有序了
        for (int i = 0; i < size - 1; ++i) {
            boolean flag = false;
            // 最后i个有序了，只要对前面的size - i 个冒泡
            // 比较是两个进行比较的，所以需要再减1
            for (int j = 0; j < size - i - 1; ++j) {
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                    flag = true;
                }
            }
            // 没有数据交换，提前退出
            if (!flag) break;
        }
    }

    /**
     * 假设前i-1有序，然后将第i个按从后向前，选择插入到前面有序的数组中合适的位置
     * 最好：O(n), 每次从后向前都只要比较一次就能确定插入的位置
     * 最坏：O(n*n), 完全倒序
     * 平均：O(n*n)
     * 原地排序
     * 稳定排序
     */
    public static void insertSort(int[] a) {
        if (a == null) {
            return;
        }
        int size = a.length;
        if (size <= 1) {
            return;
        }
        // 假设前i个有序列了
        for (int i = 1; i < size; ++i) {
            // 前i个的最后一个位置是i -1
            int temp = a[i];
            int j = i - 1;
            for (; j >= 0; --j) {
                if (a[j] > temp) {
                    a[j + 1] = a[j];
                } else {
                    break;
                }
            }
            // 因为循环会只会循环到了<= temp的才会跳出来
            // 所以这里j 需要 +1
            a[j + 1] = temp;
        }
    }

    /**
     * 选持最小的元素，放到正确的位置
     * 因为无论是否有序，都要选择最小的, 比较的次数与原数据是否有序无关
     * 最好/最坏/平均  O(n*n）
     * 原地排序
     * 非稳定排序
     */
    public static void selectionSort(int[] a) {
        if (a == null) {
            return;
        }
        int size = a.length;
        if (size <= 1) {
            return;
        }
        for (int i = 0; i < size - 1; ++i) {
            int min = i;
            for (int j = i + 1; j < size; ++j) {
                if (a[min] > a[j]) {
                    min = j;
                }
            }
            swap(a, i, min);
        }
    }

    /**
     * 将数组分成(0,mid)(mid + 1, size -1)两部分，分别排序，然后再合并，递归进行
     * 最好/最坏/平均  O(nlogn）
     * 空间复杂度o(n)
     * 稳定排序
     */
    public static void mergeSort(int[] a) {
        if (a == null) {
            return;
        }
        int size = a.length;
        if (size <= 1) {
            return;
        }
        _mergeSort(a, 0, size - 1);
    }

    private static void _mergeSort(int[] a, int left, int right) {
        if (left <= right) {
            return;
        }
        int middle = left + (right - left) / 2;
        _mergeSort(a, left, middle);
        _mergeSort(a, middle + 1, right);
        merge(a, left, middle, right);
    }

    private static void merge(int[] a, int left, int middle, int right) {
        int i = left;
        int j = middle + 1;
        int[] temp = new int[right - left + 1];
        int k = 0;
        while (i <= middle && j <= right) {
            if (a[i] <= a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }
        // 左边还有剩余
        while (i <= middle) {
            temp[k++] = a[i++];
        }
        // 右边还有剩余
        while (j <= right) {
            temp[k++] = a[j++];
        }
        System.arraycopy(temp, 0, a, left, right - left + 1);
    }

    /**
     * 任取一个pivot,将小于pivot放到左边，pivot放中间，大于pivot放右边
     * 最好/平均：O(nlog)
     * 最坏：O(n*n)
     * 原地排序
     * 不稳定排序
     */
    public static void quickSort(int[] a) {
        if (a == null) {
            return;
        }
        int size = a.length;
        if (size <= 1) {
            return;
        }
        _quickSort(a, 0, size - 1);
    }

    private static void _quickSort(int[] a, int left, int right) {
        if (left >= right) {
            return;
        }
        int q = partition(a, left, right);
        _quickSort(a, left, q - 1);
        _quickSort(a, q + 1, right);
    }

    static int partition(int[] a, int left, int right) {
        int pivot = a[left];
        while (left < right) {
            while (left < right && a[right] >= pivot) {
                right--;
            }
            if (left < right) {
                a[left++] = a[right];
            }
            while (left < right && a[left] <= pivot) {
                left++;
            }
            if (left < right) {
                a[right--] = a[left];
            }
        }
        a[left] = pivot;
        return left;
    }

    /**
     * 最佳情况：T(n) = O(n)
     * 最差情况：T(n) = O(n)
     * 平均情况：T(n) = O(n)
     * 空间复杂度O(n)
     * 稳定排序
     */
    public static void countingSort(int[] a) {
        if (a == null) {
            return;
        }
        int size = a.length;
        if (size <= 1) {
            return;
        }
        int max = a[0];
        int min = a[0];
        for (int i = 1; i < size; i++) {
            if (a[i] > max) {
                max = a[i];
            }
            if (a[i] < min) {
                min = a[i];
            }
        }
        // 找出最大最小值
        int range = max - min + 1;
        int[] b = new int[range];
        for (int i = 0; i < range; i++) {
            b[i] = 0;
        }
        // 统计每个值出现的次数
        for (int value : a) {
            b[value - min]++;
        }
        // 前后两两相加
        for (int i = 1; i < size; i++) {
            b[i] = b[i] + b[i - 1];
        }
        // 从数组中取一个数k, k-min在b中对应值m,说明a中有m个值<=k,所以k对应的位置为m-1
        int[] c = new int[size];
        for (int i = size - 1; i >= 0; i--) {
            int index = b[a[i] - min] - 1;
            c[index] = a[i];
            b[a[i] - min]--;
        }

        // 拷贝回原数组
        System.arraycopy(c, 0, a, 0, size);
    }

    public static void countingSort2(int[] a) {
        if (a == null) {
            return;
        }
        int size = a.length;
        if (size <= 1) {
            return;
        }
        int max = a[0];
        int min = a[0];
        for (int i = 1; i < size; i++) {
            if (a[i] > max) {
                max = a[i];
            }
            if (a[i] < min) {
                min = a[i];
            }
        }
        // 找出最大最小值
        int range = max - min + 1;
        int[] b = new int[range];
        for (int i = 0; i < range; i++) {
            b[i] = 0;
        }
        // 统计每个值出现的次数
        for (int value : a) {
            b[value - min]++;
        }
        // 另外一种思路，比较适用于int等比较
        // b每一个位置i对应的值b[i]不为0，说明min+i出现了b[i]次
        // 只要按顺序填上对应个数的值就可以了
        int[] result = new int[size];
        int j = 0;
        for (int i = 0; i < range; i++) {
            int count = b[i];
            for (int k = 0; k < count; k++) {
                result[j++] = min + i;
            }
        }
        // 拷贝回原数组
        System.arraycopy(result, 0, a, 0, size);
    }


    /**
     * 最佳情况：T(n) = O(n)
     * 最差情况：T(n) = O(n*n)
     * 平均情况：T(n) = O(n)
     * 稳定排序
     * 对于二维数组，List操作起来更加方便
     */
    public static List<Integer> bucketSort(List<Integer> a, int bucketSize) {
        if (a == null) {
            return null;
        }
        int size = a.size();
        if (size <= 1) {
            return a;
        }
        int max = a.get(0);
        int min = a.get(0);
        for (int i = 1; i < size; i++) {
            if (a.get(i) > max) {
                max = a.get(i);
            }
            if (a.get(i) < min) {
                min = a.get(i);
            }
        }
        int bucketCount = (max - min) / bucketSize + 1;
        List<List<Integer>> list = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            list.add(new ArrayList<>());
        }
        for (int value : a) {
            list.get((value - min) / bucketSize).add(value);
        }
        List<Integer> result = new ArrayList<>(size);
        for (int i = 0; i < bucketCount; i++) {
            // 当 bucketCount只有1时，需要bucketSize-1
            // 否则会死循环
            if (bucketCount == 1) {
                bucketSize--;
            }
            List<Integer> temp = bucketSort(list.get(i), bucketSize);
            if (temp != null) {
                result.addAll(temp);
            }
        }
        return result;
    }

}
