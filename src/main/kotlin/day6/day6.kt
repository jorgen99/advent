package day6

import util.readResourceFile

fun main() {
//    val sample = listOf(3, 4, 3, 1, 2)
    val initialFish = toInts(readResourceFile("day6_input.txt"))

    val fish = fishAfterDays(initialFish, 80)
    println("step1: ${finalCount(fish)}")

    val lotsOfFish = fishAfterDays(initialFish, 256)
    println("step2: ${finalCount(lotsOfFish)}")
}

fun fishAfterDays(initialFish: List<Int>, days: Int): Map<Int, Long> {
    tailrec fun oneDay(fish: Map<Int, Long>, d: Int): Map<Int, Long> {
        if (d == 0) {
            return fish
        }
        return oneDay(nextGen(fish), d - 1)
    }

    return oneDay(count(initialFish), days)
}

fun nextGen(fish: Map<Int, Long>): Map<Int, Long> {
    val oneLessEach = fish
        .map { (i, no) -> (i - 1) to no }
        .toMap()
        .toMutableMap()
    val minusOnes = oneLessEach[-1] ?: 0
    oneLessEach[8] = minusOnes
    val sixes = oneLessEach[6] ?: 0
    oneLessEach[6] = sixes + minusOnes
    return oneLessEach.minus(-1)
}

fun finalCount(fish: Map<Int, Long>): Long {
    return fish.values.sum()
}

fun toInts(input: List<String>): List<Int> =
    input
        .first()
        .split(",")
        .map { it.toInt() }

fun count(fish: List<Int>): Map<Int, Long> {
    return fish.groupBy { it }.map { (i, l) -> i to l.size.toLong() }.toMap()
}

