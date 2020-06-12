package cradle.rancune.algo.binarysearch;

/**
 * Created by Rancune@126.com on 2019-06-16.
 */
public class BinarySearch {

    public static int binarySearch(int[] a, int fromIndex, int toIndex, int k) {
        if (a == null) {
            return -1;
        }
        checkRange(a, fromIndex, toIndex);
        int low = fromIndex;
        int high = toIndex;
        while (low <= high) {
            int mid = low + ((high - low) >>> 1);
            int value = a[mid];
            if (value == k) {
                return mid;
            } else if (value > k) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 查找第一个值等于给定值的元素
     */
    public static int findFirstEquals(int[] a, int fromIndex, int toIndex, int k) {
        if (a == null) {
            return -1;
        }
        checkRange(a, fromIndex, toIndex);
        int low = fromIndex;
        int high = toIndex;
        while (low <= high) {
            int mid = low + ((high - low) >>> 1);
            int value = a[mid];
            if (value > k) {
                high = mid - 1;
            } else if (value < k) {
                low = mid + 1;
            } else {
                if (mid == 0 || a[mid - 1] != k) {
                    return k;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 查找最后一个值等于给定值的元素
     */
    public static int findLastEquals(int[] a, int fromIndex, int toIndex, int k) {
        if (a == null) {
            return -1;
        }
        checkRange(a, fromIndex, toIndex);
        int low = fromIndex;
        int high = toIndex;
        while (low <= high) {
            int mid = low + ((high - low) >>> 1);
            int value = a[mid];
            if (value > k) {
                high = mid - 1;
            } else if (value < k) {
                low = mid + 1;
            } else {
                if (mid == toIndex || a[mid + 1] != k) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    /**
     * 查找第一个 >=给定值的元素
     */
    public static int findFirstNoLessThan(int[] a, int fromIndex, int toIndex, int k) {
        if (a == null) {
            return -1;
        }
        checkRange(a, fromIndex, toIndex);
        int low = fromIndex;
        int high = toIndex;
        while (low <= high) {
            int mid = low + ((high - low) >>> 1);
            int value = a[mid];
            if (value < k) {
                low = mid + 1;
            } else {
                if (mid == 0 || a[mid - 1] < k) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 查找最后一个<= 给定值的元素
     */
    public static int findLastNoMoreThan(int[] a, int fromIndex, int toIndex, int k) {
        if (a == null) {
            return -1;
        }
        checkRange(a, fromIndex, toIndex);
        int low = fromIndex;
        int high = toIndex;
        while (low <= high) {
            int mid = low + ((high - low) >>> 1);
            int value = a[mid];
            if (value > k) {
                high = mid - 1;
            } else {
                if (mid == toIndex || a[mid + 1] > k) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }


    private static void checkRange(int[] a, int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex >= a.length) {
            throw new IllegalArgumentException("index out of range");
        }
    }

    static int binarySearch(int[] array, int size, int value) {
        int lo = 0;
        int hi = size - 1;

        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            int midVal = array[mid];

            if (midVal < value) {
                lo = mid + 1;
            } else if (midVal > value) {
                hi = mid - 1;
            } else {
                return mid;  // value found
            }
        }
        System.out.println("lo" + lo);
        return ~lo;  // value not present
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4};
        int index1 = binarySearch(a, 4, 0);
        System.out.println(index1);
        System.out.println(~index1);
        int index2 = binarySearch(a, 4, 5);
        System.out.println(index2);
        System.out.println(~index2);
    }
}
