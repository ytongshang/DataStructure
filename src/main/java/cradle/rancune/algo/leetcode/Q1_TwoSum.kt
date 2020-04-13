package cradle.rancune.algo.leetcode

/**
 * Created by Rancune@126.com on 2020/4/13.
 */
class Q1 {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val result = IntArray(2)
        val map = hashMapOf<Int, Int>()
        for (i in nums.indices) {
            val value = nums[i]
            val another = target - value
            val index = map[another]
            if (index != null) {
                result[0] = i
                result[1] = index
                return result
            } else {
                map[value] = i
            }
        }
        result[0] = -1
        result[1] = -1
        return result
    }
}