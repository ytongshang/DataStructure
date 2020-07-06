package cradle.rancune.algo.offer;

import java.util.*;

/**
 * Created by Rancune@126.com on 2020/7/6.
 */
public class Detail {
    public static int hammingWeight(int n) {
        int result = 0;
        while (n != 0) {
            result += n & 1;
            n = n >>> 1;
        }
        return result;
    }

    // 剑指 Offer 16. 数值的整数次方
    // https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/
    public static double myPow(double x, int n) {
        if (x == 0) {
            return 0;
        }

        long pow = n;
        if (pow < 0) {
            x = 1.0 / x;
            // n进行倒数会越界
            pow = -pow;
        }
        double result = 1;
        while (pow > 0) {
            // 奇数
            if ((pow & 1) == 1) {
                result *= x;
            }
            x *= x;
            pow = pow >>> 1;
        }
        return result;
    }

    public int[] twoSum(int[] nums, int target) {
        if (nums == null) {
            return new int[0];
        }
        int size = nums.length;
        if (size <= 1) {
            return new int[0];
        }
        int i = 0;
        int j = size - 1;
        while (i < j) {
            if (nums[i] + nums[j] > target) {
                j--;
            } else if (nums[i] + nums[j] < target) {
                i++;
            } else {
                return new int[]{nums[i], nums[j]};
            }
        }
        return new int[0];
    }

    // 剑指 Offer 57 - II. 和为s的连续正数序列
    // https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof/
    public int[][] findContinuousSequence(int target) {
        if (target <= 2) {
            return null;
        }
        List<int[]> result = new ArrayList<>();
        int i = 1;
        int j = 1;
        int sum = 0;
        while (i <= target / 2) {
            if (sum < target) {
                sum += j;
                j++;
            } else if (sum > target) {
                sum -= i;
                i++;
            } else {
                int[] res = new int[j - i];
                for (int k = i; k < j; k++) {
                    res[k - i] = k;
                }
                result.add(res);
                // 左边界向台移动
                sum -= i;
                i++;
            }
        }
        return result.toArray(new int[result.size()][]);
    }

    // 剑指 Offer 61. 扑克牌中的顺子
    // https://leetcode-cn.com/problems/bu-ke-pai-zhong-de-shun-zi-lcof/
    public boolean isStraight(int[] nums) {
        Arrays.sort(nums);
        int zeroCount = 0;
        for (int i = 0; i < 4; i++) {
            if (nums[i] == 0) {
                zeroCount++;
                continue;
            }
            if (nums[i] == nums[i + 1]) {
                return false;
            }
            zeroCount -= nums[i + 1] - nums[i] - 1;
        }
        // 9 ,11 , 0 , 0, 0
        return zeroCount >= 0;
    }

    // 剑指 Offer 64. 求1+2+…+n
    // https://leetcode-cn.com/problems/qiu-12n-lcof/
    public int sumNums(int n) {
        boolean flag = n > 1 && (n += sumNums(n - 1)) > 0;
        return n;
    }

    // 剑指 Offer 41. 数据流中的中位数
    // https://leetcode-cn.com/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof/
    static class MedianFinder {
        private final Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        private final Queue<Integer> minHeap = new PriorityQueue<>();

        /**
         * initialize your data structure here.
         */
        public MedianFinder() {

        }

        public void addNum(int num) {
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
            if (minHeap.size() > maxHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
        }

        public double findMedian() {
            if (maxHeap.size() == 0 && minHeap.size() == 0) {
                return 0;
            }
            if (maxHeap.size() == minHeap.size()) {
                return (maxHeap.peek() + minHeap.peek()) * 0.5;
            }
            return maxHeap.peek();
        }
    }
}
