package cradle.rancune.algo.leetcode

import java.util.*

/**
 * Created by Rancune@126.com on 2020/4/13.
 * https://leetcode-cn.com/problems/valid-parentheses/
 */
class Q20 {
    fun isValid(s: String): Boolean {
        val size = s.length
        if (size == 0) {
            return true
        }
        if (size == 1) {
            return false
        }
        val stack = Stack<Char>()
        for (i in 0 until size) {
            val c = s[i]
            if (c == '{' || c == '[' || c == '(') {
                stack.push(c)
            } else {
                if (stack.isEmpty()) {
                    return false
                }
                val top = stack.pop()
                if (c == '}' && top != '{') return false
                if (c == ']' && top != '[') return false
                if (c == ')' && top != '(') return false
            }
        }
        return stack.isEmpty()
    }
}
