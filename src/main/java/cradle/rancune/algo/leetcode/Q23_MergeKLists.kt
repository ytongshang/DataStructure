@file:Suppress("DuplicatedCode")

package cradle.rancune.algo.leetcode

import java.util.*
import kotlin.Comparator

/**
 * Created by Rancune@126.com on 2020/4/15.
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/
 */
class Q23 {
    // 两两合并，变为最终的结果
    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        val size = lists.size
        if (size == 0) {
            return null
        }
        if (size == 1) {
            return lists[0]
        }
        if (size == 2) {
            return mergeTwoLists(lists[0], lists[1])
        }
        var merge = mergeTwoLists(lists[0], lists[1])
        for (i in 2 until size) {
            merge = mergeTwoLists(merge, lists[i])
        }
        return merge
    }

    fun mergeKLists2(lists: Array<ListNode?>): ListNode? {
        val size = lists.size
        if (size == 0) {
            return null
        }
        if (size == 1) {
            return lists[0]
        }
        // 优先对列默认为小顶堆
        val queue = PriorityQueue<ListNode>(Comparator<ListNode> { o1, o2 ->
            o1.`val` - o2.`val`
        })
        for (list in lists) {
            var n: ListNode? = list ?: continue
            while (n != null) {
                queue.add(n)
                n = n.next
            }
        }
        val dummy = ListNode(0)
        var node: ListNode = dummy
        while (!queue.isEmpty()) {
            node.next = queue.poll()
            node = node.next!!
        }
        return dummy.next
    }

    private fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
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