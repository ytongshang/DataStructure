@file:Suppress("DuplicatedCode")

package cradle.rancune.algo.leetcode

import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList

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
        // 整个算法的思想是 4 -> (3 -> (2 -> 1))
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
        // 这种算法实际就是一种暴力解法，只不过用堆排序效获得最小节点的效率更高
        // 实际相当于将所有节点放到优先队列中，然后构造小顶堆，然后弹出堆顶的数据
        // 所有的节点都参与到了，最后一节点必须要设置为null
        // 因为其实原先的链是没断掉的，这里不设置的话，会出问题
        node.next = null
        return dummy.next
    }

    fun mergeKLists3(lists: Array<ListNode?>): ListNode? {
        val size = lists.size
        if (size == 0) {
            return null
        }
        if (size == 1) {
            return lists[0]
        }
        // 优先队列默认为小顶堆
        val queue = PriorityQueue<ListNode>(Comparator<ListNode> { o1, o2 ->
            o1.`val` - o2.`val`
        })
        for (list in lists) {
            if (list != null) {
                queue.add(list)
                // 这里和上面的区别是上面的解法是加入所有的节点，只加入每个链的首节点
                // 因为所有的链表实际上是有序的
                // 有点类似于归并排序
            }
        }
        val dummy = ListNode(0)
        var node: ListNode = dummy
        while (!queue.isEmpty()) {
            val top = queue.poll()
            node.next = top
            node = top
            // poll出的这条链的下一个节点
            if (top.next != null) {
                queue.add(top.next)
            }
        }
        node.next = null
        return dummy.next
    }

    fun mergeKLists4(lists: Array<ListNode?>): ListNode? {
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
        return merge4Helper(lists, 0, size - 1)
    }

    private fun merge4Helper(lists: Array<ListNode?>, begin: Int, end: Int): ListNode? {
        if (begin == end) {
            return lists[begin]
        }
        if (begin + 1 == end) {
            return mergeTwoLists(lists[begin], lists[end])
        }
        val mid = begin + (end - begin) / 2
        // 有点类似于归并排序
        // 左边的排序好，右边的排序好，再两者合并
        val left = merge4Helper(lists, begin, mid)
        val right = merge4Helper(lists, mid + 1, end)
        return mergeTwoLists(left, right)
    }

    fun mergeKLists5(lists: Array<ListNode?>): ListNode? {
        var list = ArrayList<ListNode?>()
        for (item in lists) {
            if (item != null) {
                list.add(item)
            }
        }
        if (list.size == 0) {
            return null
        }
        if (list.size == 1) {
            return list[0]
        }
        // 也是两两配对合并，用循环实现
        while (list.size > 1) {
            list = merge(list)
        }
        return list[0]
    }

    private fun merge(list: ArrayList<ListNode?>): ArrayList<ListNode?> {
        val size = list.size
        if (size == 0 || size == 1) {
            return list
        }
        val newList = ArrayList<ListNode?>()
        for (i in 0 until size - 1 step 2) {
            newList.add(mergeTwoLists(list[i], list[i + 1]))
        }
        if (size % 2 == 1) {
            newList.add(list[size - 1])
        }
        return newList
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

fun main() {
    val l1 = ListNode(1, ListNode(4, ListNode(5)))
    val l2 = ListNode(1, ListNode(3, ListNode(4)))
    val l3 = ListNode(2, ListNode(6))
    val array: Array<ListNode?> = arrayOf(l1, l2, l3)
    val merge = Q23().mergeKLists4(array)
    println(merge)
}