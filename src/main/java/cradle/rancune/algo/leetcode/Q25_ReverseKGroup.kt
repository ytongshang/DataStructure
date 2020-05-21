@file:Suppress("DuplicatedCode")

package cradle.rancune.algo.leetcode

import java.util.*

/**
 * Created by Rancune@126.com on 2020/4/17.
 * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
 */
class Q25 {
    fun reverseKGroup(head: ListNode?, k: Int): ListNode? {
        if (head == null) {
            return null
        }
        if (k <= 1) {
            return head
        }
        var first: ListNode? = head
        var result: ListNode? = null
        var resultTail: ListNode? = null
        while (first != null) {
            var current: ListNode? = first
            for (i in 1 until k) {
                current = current?.next
                if (current == null) {
                    // 不足K项
                    return if (result == null) {
                        // 总体不足K
                        first
                    } else {
                        // 前面排好了，加上剩下的不足K的链表
                        resultTail?.next = first
                        result
                    }
                }
            }
            // 下一个需要reverse的head
            val next = current?.next
            // 先把后面的断掉，否则会有问题
            current?.next = null
            // 对头为first开始，尾为current的链表reverse
            val reversed = reverse(first)
            if (result == null) {
                result = reversed
                resultTail = first
            } else {
                resultTail?.next = reversed
                resultTail = first
            }
            first = next
        }
        return result
    }

    private fun reverse(node: ListNode?): ListNode? {
        if (node?.next == null) {
            return node
        }
        var n = node
        var result: ListNode? = null
        while (n != null) {
            val next = n.next
            n.next = result
            result = n
            n = next
        }
        return result
    }

    // 看了题解，使用stack试一下
    fun reverseKGroup2(head: ListNode?, k: Int): ListNode? {
        if (head == null) {
            return null
        }
        if (k <= 1) {
            return head
        }
        var result: ListNode? = null
        var resultTail: ListNode? = null
        val stack = Stack<ListNode>()
        var n = head
        // todo
        while (n != null) {

        }
        return null
    }
}