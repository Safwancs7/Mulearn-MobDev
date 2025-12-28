fun isValidISBN(isbn: String): Boolean {
    val clean = isbn.replace("-", "")

    return when (clean.length) {
        10 -> isValidISBN10(clean)
        13 -> isValidISBN13(clean)
        else -> false
    }
}

/* ---------- ISBN-10 ---------- */
fun isValidISBN10(isbn: String): Boolean {
    if (!isbn.substring(0, 9).all { it.isDigit() }) return false

    var sum = 0
    for (i in 0 until 9) {
        sum += (10 - i) * (isbn[i] - '0')
    }

    val last = isbn[9]
    sum += when {
        last.isDigit() -> last - '0'
        last == 'X' -> 10
        else -> return false
    }

    return sum % 11 == 0
}

/* ---------- ISBN-13 ---------- */
fun isValidISBN13(isbn: String): Boolean {
    if (!isbn.all { it.isDigit() }) return false

    var sum = 0
    for (i in 0 until 12) {
        val digit = isbn[i] - '0'
        sum += if (i % 2 == 0) digit else digit * 3
    }

    val checkDigit = (10 - (sum % 10)) % 10
    return checkDigit == (isbn[12] - '0')
}

/* ---------- TESTING ---------- */
fun main() {
    val testISBNs = listOf(
        "0-306-40615-2",   // valid ISBN-10
        "0306406152",     // valid ISBN-10
        "978-3-16-148410-0", // valid ISBN-13
        "9783161484100",  // valid ISBN-13
        "123456789X",     // valid ISBN-10
        "1234567890",     // invalid
        "9783161484101"   // invalid
    )

    for (isbn in testISBNs) {
        println("$isbn -> ${isValidISBN(isbn)}")
    }
}
