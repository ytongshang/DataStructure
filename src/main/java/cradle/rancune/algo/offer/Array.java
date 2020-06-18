package cradle.rancune.algo.offer;

import java.util.HashMap;
import java.util.Map;

public class Array {
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
        return mergeCount(nums, 0, nums.length - 1);
    }

    // 面试题51. 数组中的逆序对
    // https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/
    private int mergeCount(int[] nums, int start, int end) {
        return -1;
    }

}
