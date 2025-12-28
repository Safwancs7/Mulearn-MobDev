import kotlin.math.ceil
import kotlin.math.sqrt

fun cryptoSquare(text: String): String {

    // Step 1: Normalize
    val normalized = text
        .lowercase()
        .filter { it.isLetterOrDigit() }

    if (normalized.isEmpty()) return ""

    // Step 2: Determine grid size
    val length = normalized.length
    val columns = ceil(sqrt(length.toDouble())).toInt()

    // Step 3 & 4: Read column-wise
    val encoded = StringBuilder()

    for (col in 0 until columns) {
        var index = col
        while (index < length) {
            encoded.append(normalized[index])
            index += columns
        }
    }

    return encoded.toString()
}

fun main() {
    val input =
        "If man was meant to stay on the ground, God would have given us roots."

    val result = cryptoSquare(input)

    println("Original: $input")
    println("Encoded : $result")
}
