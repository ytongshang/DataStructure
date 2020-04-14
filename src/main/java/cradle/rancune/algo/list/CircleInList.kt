package cradle.rancune.algo.list

/**
 * Created by Rancune@126.com on 2020/4/14.
 */

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