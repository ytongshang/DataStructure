package cradle.rancune.algo.leetcode

/**
 * Created by Rancune@126.com on 2020/4/23.
 */
object Q3 {
    // "abcabcbb"
    fun lengthOfLongestSubstring(s: String): Int {
        val len = s.length
        if (len <= 1) {
            return len
        }
        var max = 0
        var left = 0
        val map = hashMapOf<Char, Int>()
        for (right in 0 until len) {
            val c = s[right]
            val index = map[c]
            if (index != null) {
                // 前面存在c字符
                // 那么index需要移动到index的下一个位置
                left = Math.max(index + 1, left)
                // 并且 index和left之前的数据需要清除掉
                map[c] = right
            } else {
                val size = right - left + 1
                max = size.coerceAtLeast(max)
                map[c] = right
            }

        }
        return max
    }
}

fun main() {
    val str = "tmmzuxt"
    print(Q3.lengthOfLongestSubstring(str))
}