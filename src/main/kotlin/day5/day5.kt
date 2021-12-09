package day5

import util.readResourceFile
import kotlin.math.abs

val sampleVentLines = """
  0,9 -> 5,9
  8,0 -> 0,8
  9,4 -> 3,4
  2,2 -> 2,1
  7,0 -> 7,4
  6,4 -> 2,0
  0,9 -> 2,9
  3,4 -> 1,4
  0,0 -> 8,8
  5,5 -> 8,2
"""

fun main() {
    val seaFloor = mutableMapOf<Pair<Int, Int>, Int>()
//    val noOfDangerAreas = part1(seaFloor)
    val fileLines = readResourceFile("day5_input.txt")
    val noOfDangerAreas = part1(mutableMapOf(), fileLines)
    println("step1: $noOfDangerAreas")
}

private fun part1(seaFloor: MutableMap<Pair<Int, Int>, Int>, lines: List<String>): Int {
    parseCoords(lines)
        .map { line(it) }
        .forEach { incrementCoordinates(seaFloor, it) }
    return noOfDangerAreas(seaFloor)
}

fun printMap(seaFloor: MutableMap<Pair<Int, Int>, Int>, hSize: Int, vSize: Int): List<String> {
    val map = mutableListOf<String>()
    for (x in 0..vSize) {
        var l = ""
        for (y in 0..hSize) {
            val v = seaFloor[y to x]
            val mark = when (v ?: 0) {
                0 -> "."
                else -> v.toString()
            }
            l += "$mark "
        }
        l.trim()
        map.add("$l\n")
    }
    return map.toList()
}

fun noOfDangerAreas(seaFloor: MutableMap<Pair<Int, Int>, Int>): Int {
    return seaFloor.values.count { it > 1 }
}

fun incrementCoordinates(seaFloor: MutableMap<Pair<Int, Int>, Int>, line: List<Pair<Int, Int>>) {
    line.forEach { p -> seaFloor.compute(p) { pair, n -> (n ?: 0) + 1 } }
}

fun line(startStop: Pair<Pair<Int, Int>, Pair<Int, Int>>): List<Pair<Int, Int>> {
    val (c1, c2) = startStop
    if (c1.first != c2.first && c1.second != c2.second) {
        if (abs(c1.first - c2.first) == abs(c1.second - c2.second)) {
            if (c1.first - c2.first <= 0 && c1.second - c2.second <= 0) {
                return generateSequence(c1) { (x, y) -> Pair(x + 1, y + 1) }
                    .take(abs(c2.second - c1.second)).toList()
            }
        }
    }
    if (c1.second == c2.second) {
        if (c1.first - c2.first <= 0) {
            return (c1.first..c2.first)
                .map { it to c1.second }
        }
        return (c2.first..c1.first)
            .map { it to c1.second }
    }
    if (c1.second - c2.second <= 0) {
        return (c1.second..c2.second)
            .map { c1.first to it }
    }
    return (c2.second..c1.second)
        .map { c1.first to it }
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