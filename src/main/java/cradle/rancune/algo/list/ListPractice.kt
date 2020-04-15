package cradle.rancune.algo.list

/**
 * Created by Rancune@126.com on 2020/4/14.
 * LeetCode
 * 2, 19,21, 23,24, 25, 61, 82,83, 86, 92,
 * 109, 138, 141, 142, 143, 147, 148, 160
 */

fun reverseList(node: Node?): Node? {
    if (node?.next == null) {
        return node
    }
    var result: Node? = null
    var n = node
    while (n != null) {
        val next = n.next
        n.next = result
        result = n
        n = next
    }
    return result
}

fun hasCircle(node: Node?): Boolean {
    if (node?.next == null) {
        return false
    }
    var fast = node
    var slow = node
    while (fast?.next?.next != null) {
        fast = fast.next?.next
        slow = slow?.next
        if (fast == slow) {
            return true
        }
    }
    return false
}

fun main() {
    println("reverseList")
    val list = Node(1)
    println(reverseList(list))
    list.next = Node(2, Node(3, Node(4)))
    println(list)
    println(reverseList(list))
}