@file:Suppress("DuplicatedCode")

package cradle.rancune.algo.leetcode

/**
 * Created by Rancune@126.com on 2020/4/14.
 * https://leetcode-cn.com/problems/add-two-numbers/
 */

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

class Q2 {
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        var head: ListNode? = null
        var last: ListNode? = null
        var flag = false
        var list1 = l1
        var list2 = l2
        while (list1 != null || list2 != null) {
            val v1 = list1?.`val` ?: 0
            val v2 = list2?.`val` ?: 0
            var sum = v1 + v2
            if (flag) {
                sum++
            }
            val remain = if (sum >= 10) {
                flag = true
                sum - 10
            } else {
                flag = false
                sum
            }
            val newNode = ListNode(remain)
            if (head == null) {
                head = newNode
                last = newNode
            } else {
                last!!.next = newNode
                last = last.next
            }
            if (list1 != null) {
                list1 = list1.next
            }
            if (list2 != null) {
                list2 = list2.next
            }
        }
        if (flag) {
            last?.next = ListNode(1)
        }
        return head
    }

    fun addTwoNumbers2(l1: ListNode?, l2: ListNode?): ListNode? {
        // 哨兵减少判断
        val head = ListNode(0)
        var last = head
        // 直接用carry而不是flag
        var carry = 0
        var list1 = l1
        var list2 = l2
        while (list1 != null || list2 != null) {
            val v1 = list1?.`val` ?: 0
            val v2 = list2?.`val` ?: 0
            val sum = v1 + v2 + carry
            carry = sum / 10
            last.next = ListNode(sum % 10)
            last = last.next!!
            list1 = list1?.next
            list2 = list2?.next
        }
        // 进位容易忘记
        if (carry > 0) {
            last.next = ListNode(carry)
        }
        return head.next
    }
}