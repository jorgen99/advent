package day7

import util.readResourceFile
import kotlin.math.abs

fun main() {
    val crabPositions = readResourceFile("day7_input.txt")
        .filter { it.isNotBlank() }
        .flatMap { it.split(",") }
        .map { it.trim() }
        .map { it.toInt() }

    val fuelStep1 = fuel(crabPositions, differance)
    println("step1: fuel = $fuelStep1")

    val fuelStep2 = fuel(crabPositions, increasing)
    println("step2: fuel = $fuelStep2")
}

val differance: (Int, Int) -> Int = { i, j -> abs(i - j) }
val increasing: (Int, Int) -> Int = { i, j ->
    val diff = differance(i, j)
    tailrec fun inc(i: Int, lastSum: Int): Int {
        return if (i > diff) lastSum else
            inc(i + 1, i + lastSum)
    }
    inc(1, 0)
}

fun fuel(positions: List<Int>, distanceToFuel: (Int, Int) -> Int): Int {
    tailrec fun fuelToPosition(position: Int, positions: List<Int>, lastFuel: Int): Int {
        if (position > positions.size) {
            return lastFuel
        }
        val fuel: Int = positions.sumOf { distanceToFuel(it, position) }
//        println("position: $position, lastFuel: $lastFuel, fuel = $fuel")
        return if (fuel >= lastFuel) lastFuel else
            fuelToPosition(position + 1, positions, fuel)
    }

    val median = positions.median()
//    println("median = $median")
    return fuelToPosition(median, positions, Int.MAX_VALUE)
}

fun List<Int>.median() =
    this
        .sorted()
        .chunked(this.size / 2)
        .first()
        .last()

