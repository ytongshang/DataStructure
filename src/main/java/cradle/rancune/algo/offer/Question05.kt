package cradle.rancune.algo.offer

import java.lang.StringBuilder

class Question05 {
    fun replaceSpace(s: String): String {
        val builder = StringBuilder()
        for (char in s) {
            if (char == ' ') {
                builder.append("%20")
            } else {
                builder.append(char)
            }
        }
        return builder.toString()
    }
}