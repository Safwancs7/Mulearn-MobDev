import kotlin.math.min

fun minCoins(coins: IntArray, amount: Int): Int {
    if (amount < 0) return -1

    val INF = amount + 1
    val dp = IntArray(amount + 1) { INF }
    dp[0] = 0

    for (i in 1..amount) {
        for (coin in coins) {
            if (coin <= i) {
                dp[i] = min(dp[i], dp[i - coin] + 1)
            }
        }
    }

    return if (dp[amount] > amount) -1 else dp[amount]
}

fun main() {

    val coins = intArrayOf(1, 3, 4)
    val amount = 6

    println("Coins available: ${coins.joinToString()}")
    println("Target amount: $amount")

    val result = minCoins(coins, amount)

    if (result != -1) {
        println("Minimum number of coins required: $result")
    } else {
        println("Change cannot be made with the given coins")
    }
}
