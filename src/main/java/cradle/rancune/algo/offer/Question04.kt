package cradle.rancune.algo.offer

class Solution4 {
    fun findNumberIn2DArray(matrix: Array<IntArray>, target: Int): Boolean {
        val row = matrix.size
        if (row <= 0) {
            return false
        }
        val column = matrix[0].size
        if (column <= 0) {
            return false
        }
        var i = 0
        var j = column -1
        while (i < row && j >= 0) {
            val value = matrix[i][j]
            when {
                value == target -> {
                    return true
                }
                value < target -> {
                    i++
                }
                else -> {
                    j--
                }
            }
        }
        return false
    }
}