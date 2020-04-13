package cradle.rancune.algo.leetcode

/**
 * Created by Rancune@126.com on 2020/4/13.
 */
class Q27 {
    fun removeElement(nums: IntArray, value: Int): Int {
        val size = nums.size
        if (size == 0) {
            return 0
        }
        var i = 0
        var j = 0
        while (j < size) {
            if (nums[j] != value) {
                nums[i++] = nums[j++]
            } else {
                j++
            }
        }
        return i
    }
}