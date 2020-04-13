package cradle.rancune.algo.leetcode

/**
 * Created by Rancune@126.com on 2020/4/13.
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
 */
class Q3 {
    fun removeDuplicates(nums: IntArray): Int {
        val size = nums.size
        if (size <= 1) {
            return size
        }
        var i = 0
        var j = 1
        while (j < size) {
            if (nums[j] != nums[i]) {
                nums[++i] = nums[j++]
            } else {
                j++
            }
        }
        // i是最后一个数字的index,这里需要+1
        return i + 1
    }
}