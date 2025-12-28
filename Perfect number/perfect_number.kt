fun isPerfectNumber(n: Int): Boolean {
    if (n <= 1) return false

    var sum = 0

    for (i in 1..n / 2) {
        if (n % i == 0) {
            sum += i
        }
    }

    return sum == n
}

fun main() {
    val number = 28

    if (isPerfectNumber(number)) {
        println("$number is a Perfect Number")
    } else {
        println("$number is NOT a Perfect Number")
    }
}
