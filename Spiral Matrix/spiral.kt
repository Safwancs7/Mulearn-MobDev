fun generateSpiralMatrix(n: Int): Array<IntArray> {
    val matrix = Array(n) { IntArray(n) }

    var top = 0
    var bottom = n - 1
    var left = 0
    var right = n - 1
    var num = 1

    while (top <= bottom && left <= right) {

        // Move left → right
        for (i in left..right) {
            matrix[top][i] = num++
        }
        top++

        // Move top → bottom
        for (i in top..bottom) {
            matrix[i][right] = num++
        }
        right--

        // Move right → left
        if (top <= bottom) {
            for (i in right downTo left) {
                matrix[bottom][i] = num++
            }
            bottom--
        }

        // Move bottom → top
        if (left <= right) {
            for (i in bottom downTo top) {
                matrix[i][left] = num++
            }
            left++
        }
    }

    return matrix
}

fun main() {
    val n = 4
    val result = generateSpiralMatrix(n)

    for (row in result) {
        for (value in row) {
            print("${value.toString().padStart(4)}")
        }
        println()
    }
}
