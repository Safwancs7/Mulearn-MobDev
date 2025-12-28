import kotlin.math.abs

/* =======================
   UTILITY FUNCTIONS
   ======================= */

fun gcd(a: Int, b: Int): Int {
    return if (b == 0) abs(a) else gcd(b, a % b)
}

fun modInverse(a: Int, m: Int): Int {
    for (x in 1 until m) {
        if ((a * x) % m == 1) return x
    }
    throw IllegalArgumentException("No modular inverse exists for a = $a")
}

/* =======================
   AFFINE CIPHER
   ======================= */

fun affineEncrypt(text: String, a: Int, b: Int): String {
    require(gcd(a, 26) == 1) { "Key 'a' must be coprime with 26" }

    val result = StringBuilder()

    for (ch in text.uppercase()) {
        if (ch in 'A'..'Z') {
            val x = ch - 'A'
            val encrypted = (a * x + b) % 26
            result.append((encrypted + 'A'.code).toChar())
        } else {
            result.append(ch)
        }
    }
    return result.toString()
}

fun affineDecrypt(text: String, a: Int, b: Int): String {
    val aInv = modInverse(a, 26)
    val result = StringBuilder()

    for (ch in text.uppercase()) {
        if (ch in 'A'..'Z') {
            val y = ch - 'A'
            val decrypted = (aInv * (y - b + 26)) % 26
            result.append((decrypted + 'A'.code).toChar())
        } else {
            result.append(ch)
        }
    }
    return result.toString()
}

/* =======================
   RAIL FENCE CIPHER
   ======================= */

fun railFenceEncrypt(text: String, rails: Int): String {
    if (rails <= 1) return text

    val fence = Array(rails) { StringBuilder() }
    var rail = 0
    var direction = 1

    for (ch in text) {
        fence[rail].append(ch)
        rail += direction
        if (rail == 0 || rail == rails - 1) {
            direction *= -1
        }
    }

    return fence.joinToString("") { it.toString() }
}

fun railFenceDecrypt(cipher: String, rails: Int): String {
    if (rails <= 1) return cipher

    val pattern = IntArray(cipher.length)
    var rail = 0
    var direction = 1

    for (i in cipher.indices) {
        pattern[i] = rail
        rail += direction
        if (rail == 0 || rail == rails - 1) {
            direction *= -1
        }
    }

    val railCounts = IntArray(rails)
    for (r in pattern) railCounts[r]++

    val railsArray = Array(rails) { CharArray(railCounts[it]) }
    var index = 0
    for (r in 0 until rails) {
        for (i in railsArray[r].indices) {
            railsArray[r][i] = cipher[index++]
        }
    }

    val railPointers = IntArray(rails)
    val result = StringBuilder()

    for (r in pattern) {
        result.append(railsArray[r][railPointers[r]++])
    }

    return result.toString()
}

/* =======================
   MAIN FUNCTION
   ======================= */

fun main() {

    // ===== Affine Cipher Test =====
    val affineText = "HELLO WORLD"
    val a = 5
    val b = 8

    val affineEncrypted = affineEncrypt(affineText, a, b)
    val affineDecrypted = affineDecrypt(affineEncrypted, a, b)

    println("Affine Cipher")
    println("Original : $affineText")
    println("Encrypted: $affineEncrypted")
    println("Decrypted: $affineDecrypted")

    println("\n---------------------------\n")

    // ===== Rail Fence Cipher Test =====
    val railText = "WEAREDISCOVEREDFLEEATONCE"
    val rails = 3

    val railEncrypted = railFenceEncrypt(railText, rails)
    val railDecrypted = railFenceDecrypt(railEncrypted, rails)

    println("Rail Fence Cipher")
    println("Original : $railText")
    println("Encrypted: $railEncrypted")
    println("Decrypted: $railDecrypted")
}
