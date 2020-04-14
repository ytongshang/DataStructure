package cradle.rancune.algo.list

/**
 * Created by Rancune@126.com on 2020/4/14.
 * LeetCode
 * 2,
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



fun main() {
    println("reverseList")
    val list = Node(1)
    println(reverseList(list))
    list.next = Node(2, Node(3, Node(4)))
    println(list)
    println(reverseList(list))
}