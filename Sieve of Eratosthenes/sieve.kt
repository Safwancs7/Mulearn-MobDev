fun sieveOfEratosthenes(n: Int): List<Int> {
    if (n < 2) return emptyList()

    val isPrime = BooleanArray(n + 1) { true }
    isPrime[0] = false
    isPrime[1] = false

    var p = 2
    while (p * p <= n) {
        if (isPrime[p]) {
            // Mark multiples of p
            var multiple = p * p
            while (multiple <= n) {
                isPrime[multiple] = false
                multiple += p
            }
        }
        p++
    }

    // Collect primes
    val primes = mutableListOf<Int>()
    for (i in 2..n) {
        if (isPrime[i]) {
            primes.add(i)
        }
    }

    return primes
}

fun main() {
    val limit = 50
    val primes = sieveOfEratosthenes(limit)

    println("Prime numbers up to $limit:")
    println(primes.joinToString())
}
