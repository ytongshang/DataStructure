package cradle.rancune.algo.offer

import java.util.*

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

// Question 06
// 从尾到头打印链表
fun reversePrint(head: ListNode?): IntArray {
    if (head == null) {
        return IntArray(0)
    }
    val deque = ArrayDeque<Int>()
    var n = head
    while (n != null) {
        deque.addLast(n.`val`)
        n = n.next
    }
    val result = IntArray(deque.size)
    var i = 0
    while (!deque.isEmpty()) {
        result[i++] = deque.removeLast()
    }
    return result
}