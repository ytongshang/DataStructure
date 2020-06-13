package cradle.rancune.algo.offer

class Solution {
    fun findRepeatNumber(nums: IntArray): Int {
        val size = nums.size
        if (size == 0 || size == 1) {
            return -1;
        }
        for(i in 0 until size) {
            while (nums[i] != i) {
                if (nums[nums[i]] == nums[i]) {
                    return nums[i]
                } else {
                    swap(nums, i, nums[i])
                }
            }
        }
        return -1
    }

    private fun swap(nums: IntArray, a: Int, b: Int) {
        val temp = nums[a]
        nums[a] = nums[b]
        nums[b] = temp
    }
}