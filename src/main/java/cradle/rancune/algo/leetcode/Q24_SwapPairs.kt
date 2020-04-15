package cradle.rancune.algo.leetcode

/**
 * Created by Rancune@126.com on 2020/4/15.
 * https://leetcode-cn.com/problems/swap-nodes-in-pairs/
 */
class Q24 {
    fun swapPairs(head: ListNode?): ListNode? {
        if (head?.next == null) {
            return head
        }
        val dummy = ListNode(0)
        dummy.next = head
        var current = dummy
        var first: ListNode
        var second: ListNode
        var third: ListNode?
        while (current.next != null && current.next?.next != null) {
            first = current.next!!
            second = first.next!!
            third = second.next
            current.next = second
            second.next = first
            first.next = third
            current = first
        }
        return dummy.next
    }

    /**
     * 递归核心
     * 递归不用考虑过程，思想就是假设后面解决了，然后再怎么怎么样
     * first->second third
     * 我们假设third后面的已经反转好了，那么我们要做的就是second first third
     */
    fun swapPairs2(head: ListNode?): ListNode? {
        if (head?.next == null) {
            return head
        }
        val first: ListNode = head
        val second = head.next!!
        val third = swapPairs2(second.next)
        second.next = first
        first.next = third
        return second
    }
}