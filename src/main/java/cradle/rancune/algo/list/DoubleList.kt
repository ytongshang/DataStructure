@file:Suppress("DuplicatedCode")

package cradle.rancune.algo.list

/**
 * Created by Rancune@126.com on 2020/4/14.
 */
class DoubleList<T> {
    class Node<T>(t: T?, var previous: Node<T>? = null, var next: Node<T>? = null) {
        var value: T? = t

        override fun toString(): String {
            return value?.toString() ?: "null"
        }
    }

    var first: Node<T>? = null
        private set
    var last: Node<T>? = null
        private set
    var size: Int = 0
        private set

    fun clear() {
        first = null
        last = null
        size = 0
    }

    fun linkFirst(t: T?) {
        val node = Node(t, null, first)
        if (first == null) {
            first = node
            last = node
        } else {
            first!!.previous = node
            first = node
        }
        size++
    }

    fun linkLast(t: T?) {
        val node = Node(t, last, null)
        if (last == null) {
            first = node
            last = node
        } else {
            last!!.next = node
            last = node
        }
        size++
    }

    fun unlinkFirst(): T? {
        if (first == null) {
            return null
        }
        val f = first
        first = f!!.next
        f.next = null
        if (first == null) {
            last = null
        } else {
            first!!.previous = null
        }
        size--
        return f.value
    }

    fun unlinkLast(): T? {
        if (last == null) {
            return null
        }
        val l = last
        last = l!!.previous
        l.previous = null
        if (last == null) {
            first = null
        } else {
            last!!.next = null
        }
        size--
        return l.value
    }

    fun linkBefore(node: Node<T>, t: T?) {
        val pre = node.previous
        val newNode = Node(t, pre, node)
        node.previous = newNode
        if (pre == null) {
            first = newNode
        } else {
            pre.next = newNode
        }
        size++
    }

    fun linkAfter(node: Node<T>, t: T?) {
        val next = node.next
        val newNode = Node(t, node, next)
        node.next = newNode
        if (next == null) {
            last = newNode
        } else {
            next.previous = newNode
        }
        size++
    }

    fun unlink(node: Node<T>): T? {
        val pre = node.previous
        val next = node.next
        node.next = null
        node.previous = null
        if (pre == null) {
            first = next
        } else {
            pre.next = next
        }
        if (next == null) {
            last = pre
        } else {
            next.previous = pre
        }
        size--
        return node.value
    }

    override fun toString(): String {
        if (first == null) {
            return "{}"
        }
        val builder = StringBuilder()
        builder.append("{ ")
        var n = first
        while (n != null) {
            builder.append("${n.value} ")
            if (n == last) {
                builder.append("}")
            } else {
                builder.append(", ")
            }
            n = n.next
        }
        return builder.toString()
    }
}

fun DoubleList<*>?.print() {
    if (this == null) {
        println("null")
    } else {
        println("size: ${this.size}, first:${this.first}, last:${this.last}, list: $this")
    }
}

fun main() {
    val list = DoubleList<Int>()
    println(list)

    println("linkFirst")
    list.linkFirst(1)
    list.print()
    list.linkFirst(2)
    list.print()

    list.clear()

    println("linkLast")
    list.linkLast(1)
    list.print()
    list.linkLast(2)
    list.print()

    list.clear()

    println("unlinkFirst")
    list.unlinkFirst()
    list.print()
    list.linkLast(1)
    list.linkLast(2)
    list.linkLast(3)
    list.print()
    list.unlinkFirst()
    list.print()
    list.unlinkFirst()
    list.print()
    list.unlinkFirst()
    list.print()

    println("unlinkLast")
    list.unlinkLast()
    list.print()
    list.linkLast(1)
    list.linkLast(2)
    list.linkLast(3)
    list.print()
    list.unlinkLast()
    list.print()
    list.unlinkLast()
    list.print()
    list.unlinkLast()
    list.print()

    list.clear()
    println("linkBefore")
    list.linkLast(1)
    list.print()
    val first = list.first
    list.linkBefore(first!!, 2)
    list.print()
    val last = list.last
    list.linkBefore(last!!, 3)
    list.print()

    list.clear()
    println("linkAfter")
    list.linkLast(1)
    list.print()
    val f1 = list.first
    list.linkAfter(f1!!, 2)
    list.print()
    val f2 = list.first
    list.linkAfter(f2!!, 3)
    list.print()

    list.clear()
    println("unlink")
    list.linkLast(1)
    list.linkLast(2)
    list.linkLast(3)
    list.print()
    val middle = list.first!!.next
    list.unlink(middle!!)
    list.print()
    list.unlink(list.first!!)
    list.print()
    list.linkLast(5)
    list.print()
    list.unlink(list.last!!)
    list.print()
    list.unlink(list.first!!)
    list.print()
}

