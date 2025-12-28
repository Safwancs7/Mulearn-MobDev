fun caesarEncrypt(text: String, shift: Int): String {
    val actualShift = shift % 26
    val result = StringBuilder()

    for (ch in text) {
        when {
            ch in 'A'..'Z' -> {
                val encrypted =
                    ((ch - 'A' + actualShift) % 26 + 'A'.code).toChar()
                result.append(encrypted)
            }

            ch in 'a'..'z' -> {
                val encrypted =
                    ((ch - 'a' + actualShift) % 26 + 'a'.code).toChar()
                result.append(encrypted)
            }

            else -> result.append(ch)
        }
    }

    return result.toString()
}

fun caesarDecrypt(text: String, shift: Int): String {
    return caesarEncrypt(text, 26 - (shift % 26))
}

fun main() {
    val message = "Hello, World!"
    val shift = 3

    val encrypted = caesarEncrypt(message, shift)
    val decrypted = caesarDecrypt(encrypted, shift)

    println("Original : $message")
    println("Encrypted: $encrypted")
    println("Decrypted: $decrypted")
}
