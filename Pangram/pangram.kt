fun isPangram(sentence: String): Boolean {
    val set = HashSet<Char>()

    for (ch in sentence.lowercase()) {
        if (ch in 'a'..'z') {
            set.add(ch)
        }
    }

    return set.size == 26
}

fun main() {
    println(isPangram("The quick brown fox jumps over the lazy dog")) // true
    println(isPangram("Hello World")) // false
}
