enum class Color { RED, GREEN, IVORY, YELLOW, BLUE }
enum class Nationality { ENGLISHMAN, SPANIARD, UKRAINIAN, NORWEGIAN, JAPANESE }
enum class Pet { DOG, SNAILS, FOX, HORSE, ZEBRA }
enum class Drink { COFFEE, TEA, MILK, ORANGE_JUICE, WATER }
enum class Smoke { OLD_GOLD, KOOLS, CHESTERFIELDS, LUCKY_STRIKE, PARLIAMENT }

data class House(
    val position: Int,
    val color: Color,
    val nationality: Nationality,
    val pet: Pet,
    val drink: Drink,
    val smoke: Smoke
)

fun <T> permutations(list: List<T>): List<List<T>> {
    if (list.size == 1) return listOf(list)
    val result = mutableListOf<List<T>>()
    for (i in list.indices) {
        val rest = list.toMutableList()
        val head = rest.removeAt(i)
        for (perm in permutations(rest)) {
            result.add(listOf(head) + perm)
        }
    }
    return result
}

fun isNextTo(a: Int, b: Int): Boolean = kotlin.math.abs(a - b) == 1

fun main() {

    val colors = permutations(Color.values().toList())
    val nations = permutations(Nationality.values().toList())
    val pets = permutations(Pet.values().toList())
    val drinks = permutations(Drink.values().toList())
    val smokes = permutations(Smoke.values().toList())

    for (c in colors) {
        // Green is immediately right of Ivory
        if (c.indexOf(Color.GREEN) != c.indexOf(Color.IVORY) + 1) continue

        for (n in nations) {
            // Englishman lives in the red house
            if (c[n.indexOf(Nationality.ENGLISHMAN)] != Color.RED) continue
            // Norwegian lives in first house
            if (n[0] != Nationality.NORWEGIAN) continue
            // Norwegian lives next to blue house
            if (!isNextTo(c.indexOf(Color.BLUE), n.indexOf(Nationality.NORWEGIAN))) continue

            for (d in drinks) {
                // Coffee in green house
                if (d[c.indexOf(Color.GREEN)] != Drink.COFFEE) continue
                // Ukrainian drinks tea
                if (d[n.indexOf(Nationality.UKRAINIAN)] != Drink.TEA) continue
                // Milk in middle house
                if (d[2] != Drink.MILK) continue

                for (s in smokes) {
                    // Old Gold smoker owns snails
                    // (checked later with pets)
                    // Kools in yellow house
                    if (s[c.indexOf(Color.YELLOW)] != Smoke.KOOLS) continue
                    // Lucky Strike drinks orange juice
                    if (d[s.indexOf(Smoke.LUCKY_STRIKE)] != Drink.ORANGE_JUICE) continue
                    // Japanese smokes Parliament
                    if (s[n.indexOf(Nationality.JAPANESE)] != Smoke.PARLIAMENT) continue

                    for (p in pets) {
                        // Spaniard owns dog
                        if (p[n.indexOf(Nationality.SPANIARD)] != Pet.DOG) continue
                        // Old Gold owns snails
                        if (p[s.indexOf(Smoke.OLD_GOLD)] != Pet.SNAILS) continue
                        // Chesterfields next to fox
                        if (!isNextTo(s.indexOf(Smoke.CHESTERFIELDS), p.indexOf(Pet.FOX))) continue
                        // Kools next to horse
                        if (!isNextTo(s.indexOf(Smoke.KOOLS), p.indexOf(Pet.HORSE))) continue

                        // ✅ FOUND SOLUTION
                        val houses = (0..4).map {
                            House(
                                position = it + 1,
                                color = c[it],
                                nationality = n[it],
                                pet = p[it],
                                drink = d[it],
                                smoke = s[it]
                            )
                        }

                        println("=== ZEBRA PUZZLE SOLUTION ===\n")
                        houses.forEach {
                            println(
                                "House ${it.position}: ${it.color}, ${it.nationality}, " +
                                "${it.pet}, ${it.drink}, ${it.smoke}"
                            )
                        }

                        val zebraOwner = houses.first { it.pet == Pet.ZEBRA }.nationality
                        val waterDrinker = houses.first { it.drink == Drink.WATER }.nationality

                        println("\n🦓 Zebra owner: $zebraOwner")
                        println("💧 Water drinker: $waterDrinker")
                        return
                    }
                }
            }
        }
    }
}
