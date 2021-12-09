package day5

import util.readResourceFile
import kotlin.math.abs
import kotlin.math.max

fun main() {
    val fileLines = readResourceFile("day5_input.txt")
    val noOfDangerAreas = noOfDangerAreas(mutableMapOf(), fileLines)
    println("part2: $noOfDangerAreas")
}

fun noOfDangerAreas(seaFloor: MutableMap<Pair<Int, Int>, Int>, lines: List<String>): Int {
    parseCoords(lines)
        .map { line(it) }
        .forEach { incrementCoordinates(seaFloor, it) }
    return seaFloor.values.count { it > 1 }
}

fun incrementCoordinates(seaFloor: MutableMap<Pair<Int, Int>, Int>, line: List<Pair<Int, Int>>) {
    line.forEach { p -> seaFloor.compute(p) { _, n -> (n ?: 0) + 1 } }
}

fun line(startStop: Pair<Pair<Int, Int>, Pair<Int, Int>>): List<Pair<Int, Int>> {
    fun min(a: Pair<Int, Int>, b: Pair<Int, Int>) = if (a.first + a.second <= b.first + b.second) a else b

    val (c1, c2) = startStop
    val xlength = c2.first - c1.first
    val ylength = c2.second - c1.second
    if (xlength == 0 || ylength == 0) {
        val startCoordinate = min(c1, c2)
        return straightLine(xlength, ylength, startCoordinate)
    } else if (abs(xlength) - abs(ylength) == 0) {
        return diagonalLine(c1, c2)
    }
    return listOf()
}

private fun diagonalLine(
    c1: Pair<Int, Int>,
    c2: Pair<Int, Int>
): List<Pair<Int, Int>> {
    // diagonal
    val leftToRight: (Pair<Int, Int>) -> Pair<Int, Int> = { (x, y) -> x + 1 to y + 1 }
    val rightToLeft: (Pair<Int, Int>) -> Pair<Int, Int> = { (x, y) -> x - 1 to y + 1 }

    val length = abs(c1.first - c2.first) + 1
    //   line          start          diagonal
    // (8,2)->(2,8)      c1           rightToLeft
    // (6,4)->(2,0)      c2           leftToRight
    // (0,2)->(4,6)      c1           leftToRight
    // (5,5)->(8,2)      c2           rightToLeft
    val start =
        if (c1.first > c2.first && c1.second < c2.second) c1
        else if (c1.first > c2.first && c1.second > c2.second) c2
        else if (c1.first < c2.first && c1.second > c2.second) c2
        else c1
    val end = if (c1 == start) c2 else c1
    val diagonal = if (start.first > end.first && start.second < end.second) rightToLeft else leftToRight
    return generateSequence(start) { diagonal(it) }
        .take(length)
        .toList()
}

private fun straightLine(
    xlength: Int,
    ylength: Int,
    startCoordinate: Pair<Int, Int>
): List<Pair<Int, Int>> {
    val incFirst: (Pair<Int, Int>) -> Pair<Int, Int> = { (a, b) -> a + 1 to b }
    val incSecond: (Pair<Int, Int>) -> Pair<Int, Int> = { (a, b) -> a to b + 1 }

    val length = max(abs(xlength), abs(ylength)) + 1
    val incFirstOrSecond = if (abs(xlength) < abs(ylength)) incSecond else incFirst
    return generateSequence(startCoordinate) { incFirstOrSecond(it) }
        .take(length)
        .toList()
}

fun parseCoords(lines: List<String>): List<Pair<Pair<Int, Int>, Pair<Int, Int>>> {
    return lines
        .filter { it.isNotBlank() }
        .map { it.trim() }
        .map { it.split(Regex("->")) }
        .flatMap { it.map { s -> s.trim() } }
        .map {
            val parts = it.split(",")
            parts.first().toInt() to parts.last().toInt()
        }
        .chunked(2)
        .map { list -> Pair(list.first(), list.last()) }
}
