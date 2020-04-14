package cradle.rancune.algo.list

/**
 * Created by Rancune@126.com on 2020/4/14.
 */
class Node(var value: Int, var next: Node? = null) {

    override fun toString(): String {
        val builder = StringBuilder()
        builder.append("{ ")
        builder.append("$value ,")
        var n = next
        while (n != null) {
            builder.append("${n.value} ")
            if (n.next == null) {
                builder.append("}")
            } else {
                builder.append(", ")
            }
            n = n.next
        }
        return builder.toString()
    }

}