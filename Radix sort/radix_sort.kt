fun radixSort(arr: IntArray) {
    if (arr.isEmpty()) return

    val max = arr.maxOrNull() ?: return
    var exp = 1  // 1, 10, 100, ...

    while (max / exp > 0) {
        countingSortByDigit(arr, exp)
        exp *= 10
    }
}

fun countingSortByDigit(arr: IntArray, exp: Int) {
    val output = IntArray(arr.size)
    val count = IntArray(10)  // digits 0–9

    // Count occurrences
    for (num in arr) {
        val digit = (num / exp) % 10
        count[digit]++
    }

    // Cumulative count
    for (i in 1 until 10) {
        count[i] += count[i - 1]
    }

    // Build output array (right to left for stability)
    for (i in arr.size - 1 downTo 0) {
        val digit = (arr[i] / exp) % 10
        output[count[digit] - 1] = arr[i]
        count[digit]--
    }

    // Copy back
    for (i in arr.indices) {
        arr[i] = output[i]
    }
}

fun main() {
    val arr = intArrayOf(170, 45, 75, 90, 802, 24, 2, 66)

    println("Before sorting:")
    println(arr.joinToString())

    radixSort(arr)

    println("After sorting:")
    println(arr.joinToString())
}
