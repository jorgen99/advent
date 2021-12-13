package day9

import util.product
import util.readResourceFile

fun main() {
    val input = readResourceFile("day9_input.txt")
    val heatMap = parse(input)
    val riskLevel = riskLevel(heatMap)
    println("step1: $riskLevel")
}

fun riskLevel(matrix: List<List<Int>>) : Int {
    return lowPoints(matrix).sumOf { (x, y) -> matrix[y][x] + 1 }
}

fun lowPoints(matrix: List<List<Int>>): List<Pair<Int, Int>> {
    return (0 until matrix[0].size)
        .product(matrix.indices)
        .filter { (x, y) -> isLowPoint(x, y, matrix) }
}

fun isLowPoint(x: Int, y: Int, matrix: List<List<Int>>): Boolean {
    val point = matrix[y][x]
    return surroundingHeights(x, y, matrix).all { it > point }
}

fun surroundingHeights(x: Int, y: Int, matrix: List<List<Int>>): List<Int> {
    return orthogonalDirections
        .values.map { (dx, dy) -> (x + dx) to (y + dy) }
        .filter { (px, py) ->
            px > -1 && px < matrix[0].size &&
                    py > -1 && py < matrix.size
        }
        .map { (px, py) -> matrix[py][px] }
}

fun parse(heightMap: List<String>): List<List<Int>> {
    return heightMap.map { it.toList().map { c -> c.toString().toInt() } }
}

val orthogonalDirections = mapOf(
    "E" to (1 to 0),
    "S" to (0 to 1),
    "W" to (-1 to 0),
    "N" to (0 to -1)
)
