package day7

import util.readResourceFile
import kotlin.math.abs

fun main() {
    val crabPositions = readResourceFile("day7_input.txt")
        .filter { it.isNotBlank() }
        .flatMap { it.split(",") }
        .map { it.trim() }
        .map { it.toInt() }

    val fuel = fuel(crabPositions)
    println("step1: fuel = $fuel")
}

fun fuel(positions: List<Int>): Int {
    fun fuelToPosition(position: Int, positions: List<Int>): Int {
        return positions.sumOf { abs(position - it) }
    }

    val median = positions.median()
    return ((median - 1)..(median + 1))
        .minOfOrNull { fuelToPosition(it, positions) } ?: 0
}

fun List<Int>.median() =
    this
        .sorted()
        .chunked(this.size / 2)
        .first()
        .last()

