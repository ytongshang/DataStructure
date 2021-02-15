package cradle.rancune.algo.leetcode;

import java.util.*;

/**
 * Created by Rancune@126.com on 2020/7/20.
 */
public class Leetcode {

    // 670. 最大交换
    // https://leetcode-cn.com/problems/maximum-swap/
    public int maximumSwap(int num) {
        String str = String.valueOf(num);
        if (str.length() == 1) {
            return num;
        }
        char[] chars = str.toCharArray();
        // 0-9，每个数字出现的位置，找到最近出现这个数字的位置
        int[] pos = new int[10];
        for (int i = 0; i < str.length(); i++) {
            pos[chars[i] - '0'] = i;
        }
        for (int i = 0; i < str.length(); i++) {
            //用最后一个比它大的数字替换最高位
            for (int j = 9; j > (chars[i] - '0'); j--) {
                if (pos[j] > i) {
                    char temp = chars[i];
                    chars[i] = chars[pos[j]];
                    chars[pos[j]] = temp;
                    return Integer.parseInt(String.valueOf(chars));
                }
            }
        }
        return num;
    }

    // 最长不重复字串
    public int lengthOfLongestSubstring(String s) {
        if (s == null) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        int i = -1;
        int result = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            if (map.containsKey(c)) {
                i = Math.max(map.get(c), i);
            }
            map.put(c, j);
            result = Math.max(result, j - i);
        }
        return result;
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
        for (int i = 1; i < size; i++) {
            if (sum > 0) {
                sum += nums[i];
                if (sum > max) {
                    max = sum;
                }
            } else {
                sum = nums[i];
            }
        }
        return max;
    }

    // 和>=s的长度最小的子数组
    // https://leetcode-cn.com/problems/minimum-size-subarray-sum/
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i = 0;
        int j = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;
        for (j = 0; j < nums.length; j++) {
            sum += nums[j];
            // 这里是while
            while (sum >= s) {
                minLength = Math.min(minLength, j - i + 1);
                sum -= nums[i];
                i++;
            }
        }

        //Integer.MAX_VALUE 表示没有找到
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null) {
            return result;
        }
        int length = nums.length;
        if (length < 3) {
            return result;
        }
        Arrays.sort(nums);
        for (int i = 0; i < length - 2; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int target = -nums[i];
            int left = i + 1;
            int right = length - 1;
            while (left < right) {
                if (nums[left] + nums[right] < target) {
                    left++;
                } else if (nums[left] + nums[right] > target) {
                    right--;
                } else {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                }
            }
        }
        return result;
    }
}
