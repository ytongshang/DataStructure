package cradle.rancune.algo.offer;

import java.util.*;

@SuppressWarnings("DuplicatedCode")
public class Array {

    // int[][] ints = new int[3][4];
    // 行数 3
    // System.out.println(ints.length);
    // 列数 4
    // System.out.println(ints[0].length);

    // 面试题03. 数组中重复的数字
    // https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/
    public int findRepeatNumber(int[] nums) {
        if (nums == null || nums.length == 1) {
            return -1;
        }
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                } else {
                    swap(nums, i, nums[i]);
                }
            }
        }
        return -1;
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[b];
        nums[b] = nums[a];
        nums[a] = temp;
    }

    // 面试题04. 二维数组中的查找
    // https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int row = matrix.length;
        int column = matrix[0].length;
        int i = 0;
        int j = column - 1;
        while (i < row && j >= 0) {
            if (target == matrix[i][j]) {
                return true;
            } else if (target < matrix[i][j]) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }

    // 面试题39. 数组中出现次数超过一半的数字
    // https://leetcode-cn.com/problems/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-ban-de-shu-zi-lcof/
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int length = nums.length;
        for (int i : nums) {
            int size = map.getOrDefault(i, 0);
            if ((size + 1) > length / 2) {
                return i;
            } else {
                map.put(i, size);
            }
        }
        throw new IllegalArgumentException("");
    }

    //摩尔投票法
    public int majorityElement2(int[] nums) {
        int x = 0, votes = 0;
        for (int num : nums) {
            if (votes == 0) x = num;
            votes += num == x ? 1 : -1;
        }
        return x;
    }

    // 面试题51. 数组中的逆序对
    // https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        if (nums.length == 2) {
            return nums[0] > nums[1] ? 1 : 0;
        }
        int[] copy = new int[nums.length];
        System.arraycopy(nums, 0, copy, 0, nums.length);
        int[] temp = new int[nums.length];
        return mergeCount(copy, 0, nums.length - 1, temp);
    }

    private int mergeCount(int[] nums, int start, int end, int[] temp) {
        if (start == end) {
            return 0;
        }
        int middle = (end - start) / 2 + start;
        int left = mergeCount(nums, start, middle, temp);
        int right = mergeCount(nums, middle + 1, end, temp);
        if (nums[middle] <= nums[middle + 1]) {
            return left + right;
        }
        return left + right + merge(nums, start, end, middle, temp);
    }

    private int merge(int[] nums, int start, int end, int middle, int[] temp) {
        int i = middle;
        int j = end;
        int k = end;
        int count = 0;
        while (i >= start && j >= middle + 1) {
            if (nums[i] > nums[j]) {
                temp[k--] = nums[i--];
                count += j - (middle + 1) + 1;
            } else {
                temp[k--] = nums[j--];
            }
        }
        while (i >= start) {
            temp[k--] = nums[i--];
        }
        while (j >= middle + 1) {
            temp[k--] = nums[j--];
        }
        if (end + 1 - start >= 0) System.arraycopy(temp, start, nums, start, end + 1 - start);

        return count;
    }

    // 剑指 Offer 11. 旋转数组的最小数字
    // https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/
    // 将中间的值与最右边的值比较
    // 当我们想找到某一个特定的值时，跳出条件一定要是i和j相等的时候，写出即是while(i<j>),跳出的时候即是i==j
    // 当我们想找一个范围的时候，跳出条件就需要是i和j之间没有元素的时候了,写出即是while(i<=j>)跳出的时候即是i>j
    public int minArray(int[] numbers) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int middle = left + (right - left) / 2;
            if (numbers[middle] > numbers[right]) {
                // middle 在 左排序
                left = middle + 1;
            } else if (numbers[middle] < numbers[right]) {
                // middle 的的 右排序，因为可能middle也可能是第一个
                right = middle;
            } else {
                right--;
            }
        }
        return numbers[left];
    }

    // 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
    // https://leetcode-cn.com/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/
    public int[] exchange(int[] nums) {
        if (nums == null) {
            return null;
        }
        int size = nums.length;
        if (size <= 1) {
            return nums;
        }
        int left = 0;
        int right = size - 1;
        while (left < right) {
            while (left < right && (nums[left] & 1) == 1) {
                left++;
            }
            while (left < right && (nums[left] & 1) == 0) {
                right--;
            }
            if (left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }
        }
        return nums;
    }

    // 剑指 Offer 40. 最小的k个数
    // https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/
    // 前k小，大顶堆
    // 前k大，小顶堆
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k <= 0) {
            return new int[0];
        }
        if (arr == null) {
            return new int[0];
        }
        int length = arr.length;
        if (length < k) {
            return arr;
        }
        // 大顶堆
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < k; i++) {
            queue.offer(arr[i]);
        }
        for (int i = k; i < length; i++) {
            if (arr[i] < queue.peek()) {
                queue.poll();
                queue.offer(arr[i]);
            }
        }
        int[] result = new int[k];
        int i = 0;
        for (Integer item : queue) {
            result[i++] = item;
        }
        return result;
    }

    @SuppressWarnings("DuplicatedCode")
    public static int partition(int[] array, int left, int right) {
        int pivot = array[left];
        while (left < right) {
            while (left < right && array[right] >= pivot) {
                right--;
            }
            if (left < right) {
                array[left++] = array[right];
            }
            while (left < right && array[left] <= pivot) {
                left++;
            }
            if (left < right) {
                array[right--] = array[left];
            }
        }
        array[left] = pivot;
        return left;
    }

    // 相比较于优先队列的实现
    // 快速选择需要全部加进内存，不适用于流数据
    // 会改变原来的数据
    public int[] getLeastNumbers2(int[] arr, int k) {
        if (arr == null || k <= 0) {
            return new int[0];
        }
        int length = arr.length;
        if (length <= k) {
            return arr;
        }
        findLeastK(arr, 0, arr.length - 1, k);
        int[] result = new int[k];
        System.arraycopy(arr, 0, result, 0, k);
        return result;
    }

    private void findLeastK(int[] arr, int left, int right, int k) {
        int pivot = partition(arr, left, right);
        if (pivot < k) {
            findLeastK(arr, pivot + 1, right, k);
        } else if (pivot > k) {
            findLeastK(arr, left, pivot - 1, k);
        }
    }

    // 剑指 Offer 42. 连续子数组的最大和
    // https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/
    public int maxSubArray(int[] nums) {
        if (nums == null) {
            return 0;
        }
        int size = nums.length;
        if (size == 1) {
            return nums[0];
        }
        int max = nums[0];
        int sum = nums[0];
        for (int num : nums) {
            sum += num;
            if (sum < 0) {
                sum = 0;
            } else {
                max = Math.max(max, sum);
            }
        }
        return max;
    }

    // 剑指 Offer 45. 把数组排成最小的数
    // https://leetcode-cn.com/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof/
    // 3, 30, 34, 5, 9
    // 最终生成的数字的位数一样，要看第一位，首先要从 3, 30, 34中选一个数字
    // 然后看第二位，会是 30 < 3 < 34
    public String minNumber(int[] nums) {
        int size = nums.length;
        if (size == 1) {
            return String.valueOf(nums[0]);
        }
        String[] strs = new String[size];
        for (int i = 0; i < size; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strs, (o1, o2) -> (o1 + o2).compareTo(o2 + o1));
        StringBuilder builder = new StringBuilder();
        for (String str : strs) {
            builder.append(str);
        }
        return builder.toString();
    }

    // 剑指 Offer 53 - I. 在排序数组中查找数字 I
    // https://leetcode-cn.com/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof/
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int size = nums.length;
        if (size == 1) {
            return nums[0] == target ? 1 : 0;
        }
        int first = firstIndex(nums, 0, size - 1, target);
        int last = lastIndex(nums, 0, size - 1, target);
        if (first == -1 || last == -1) {
            return 0;
        }
        return last - first + 1;
    }

    private static int firstIndex(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (nums[middle] > target) {
                right = middle - 1;
            } else if (nums[middle] < target) {
                left = middle + 1;
            } else {
                if (middle == 0 || nums[middle - 1] != target) {
                    return middle;
                } else {
                    right = middle - 1;
                }
            }
        }
        return -1;
    }

    private static int lastIndex(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (nums[middle] > target) {
                right = middle - 1;
            } else if (nums[middle] < target) {
                left = middle + 1;
            } else {
                if (middle == right || nums[middle + 1] != target) {
                    return middle;
                } else {
                    left = middle + 1;
                }
            }
        }
        return -1;
    }

    // 剑指 Offer 53 - II. 0～n-1中缺失的数字
    // https://leetcode-cn.com/problems/que-shi-de-shu-zi-lcof/solution/er-fen-cha-zhao-by-lhdlhd/
    public int missingNumber(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = left + (right - left) >>> 1;
            if (nums[middle] == middle) {
                // 中间相等
                if (middle == nums.length - 1) {
                    return middle + 1;
                } else {
                    left = middle + 1;
                }
            } else {
                if (middle == 0 || nums[middle - 1] == middle - 1) {
                    return middle;
                } else {
                    right = middle - 1;
                }
            }
        }
        return -1;
    }


    // https://mp.weixin.qq.com/s/63pmj6Vxbec1u-iEZnbAxw
    // https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/solution/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-by-leetcode/
    // 剑指 Offer 56 - I. 数组中数字出现的次数
    public int[] singleNumbers(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        int mask = 1;
        while ((res & mask) == 0) {
            mask = mask << 1;
        }
        int a = 0;
        int b = 0;
        for (int num : nums) {
            if ((num & mask) == 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }
        return new int[]{a, b};
    }
}