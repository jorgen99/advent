package day1

import util.readResourceFile

fun main() {
    val depths = readResourceFile("day1_input.txt")
    println("part 1 count: ${part1(depths)}")
    println("part 2 count: ${part2(depths)}")
}

private fun part1(depths: List<String>): Int {
    return depths
        .filter { it.isNotEmpty() }
        .map { it.toInt() }
        .windowed(2)
        .count { it[1] > it[0] }
}

private fun part2(depths: List<String>): Int {
    return depths
        .asSequence()
        .filter { it.isNotEmpty() }
        .map { it.toInt() }
        .windowed(3)
        .map { it.sum() }
        .windowed(2)
        .count { it[1] > it[0] }
}
