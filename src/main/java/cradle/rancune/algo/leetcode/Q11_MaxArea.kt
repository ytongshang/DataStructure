package cradle.rancune.algo.leetcode

/**
 * Created by Rancune@126.com on 2020/4/13.
 * https://leetcode-cn.com/problems/container-with-most-water/comments/
 */
class Q11 {
    // 面积 = 宽*高
    // 而高取决于height小的那个值
    // 最开始在两端，宽最大，所以只能矮向高的靠，否则高不变，宽变小了，面积肯定变小了
    fun maxArea(height: IntArray): Int {
        val size = height.size
        if (size <= 1) {
            return 0
        }
        var left = 0
        var right = size - 1
        var maxArea = 0
        while (left < right) {
            val area = (right - left) * height[left].coerceAtMost(height[right])
            maxArea = maxArea.coerceAtLeast(area)
            if (height[left] < height[right]) {
                left++
            } else {
                right--
            }
        }
        return maxArea
    }
}