package cradle.rancune.algo.leetcode

/**
 * Created by Rancune@126.com on 2020/4/14.
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 * 因为是单向链表，本质是找到要删除结点的前一个节点，但如果是删除头结点
 */
class Q19 {
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        if (head == null) {
            return null
        }
        val dummy = ListNode(0)
        dummy.next = head
        var first: ListNode? = dummy
        var second: ListNode? = dummy
        for (i in 1..n + 1) {
            first = first?.next
            // 不存在这样的节点
            // {1}, n = 1
            if (first == null && i < n + 1) {
                throw IllegalArgumentException("not existed")
            }
        }
        while (first != null) {
            first = first.next
            second = second?.next
        }
        // second.next就是要移除的
        second?.next = second?.next?.next
        return dummy.next
    }
}