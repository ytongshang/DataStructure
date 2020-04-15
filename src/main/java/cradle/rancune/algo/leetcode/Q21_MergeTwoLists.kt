@file:Suppress("DuplicatedCode")

package cradle.rancune.algo.leetcode

/**
 * Created by Rancune@126.com on 2020/4/14.
 */
class Q21 {
    fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
        if (list1 == null) {
            return list2
        }
        if (list2 == null) {
            return list1
        }
        val dummy = ListNode(0)
        var last = dummy
        var l1 = list1
        var l2 = list2
        while (l1 != null && l2 != null) {
            if (l1.`val` <= l2.`val`) {
                last.next = l1
                l1 = l1.next
            } else {
                last.next = l2
                l2 = l2.next
            }
            last = last.next!!
        }
        if (l1 != null) {
            last.next = l1
        }
        if (l2 != null) {
            last.next = l2
        }
        return dummy.next
    }
}