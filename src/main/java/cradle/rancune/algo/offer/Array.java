package cradle.rancune.algo.offer;

import java.util.HashMap;
import java.util.Map;

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
        if (end + 1 - start >= 0) System.arraycopy(nums, start, temp, start, end + 1 - start);
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
}
