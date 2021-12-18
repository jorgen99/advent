package day9.step2

import util.product

fun productOfThreeLargest(heatMap: List<List<GridLocation>>): Int {
    val threeLargest = basins(heatMap)
        .map { it.size }
        .sortedDescending()
        .take(3)

    println("largest areas = $threeLargest")
    return threeLargest.reduce { i, a -> i * a }
}

fun basins(heatMap: List<List<GridLocation>>): List<List<GridLocation>> {
    return lowPoints(heatMap)
        .map { low ->
            val graph = RectangularGrid(heatMap)
            breadthFirstSearch(graph, low)
                .toList()
        }
}

fun lowPoints(matrix: List<List<GridLocation>>): List<GridLocation> {
    return (0 until matrix[0].size)
        .product(matrix.indices)
        .filter { (x, y) -> isLowPoint(x, y, matrix) }
        .map { (x, y) -> GridLocation(x, y, matrix[y][x].height) }
}

fun isLowPoint(x: Int, y: Int, matrix: List<List<GridLocation>>): Boolean {
    val point = matrix[y][x].height
    return surrounding(x, y, matrix).all { it.height > point }
}

data class GridLocation(val x: Int, val y: Int, val height: Int = -1)

interface Graph {
    fun neighbors(point: GridLocation): List<GridLocation>
}

class RectangularGrid(private val heightMap: List<List<GridLocation>>) : Graph {

    private fun passable(id: GridLocation): Boolean {
        return id.height != 9
    }

    override fun neighbors(point: GridLocation): List<GridLocation> {
        return surrounding(point.x, point.y, heightMap)
            .filter { passable(it) }
            .toList()
    }
}

fun breadthFirstSearch(graph: Graph, start: GridLocation): Set<GridLocation> {
    tailrec fun walkFrontier(frontier: ArrayDeque<GridLocation>, basin: Set<GridLocation>): Set<GridLocation> {
        if (frontier.isEmpty()) {
            return basin
        }
        val current = frontier.removeFirst()
        frontier.addAll(
            graph.neighbors(current)
                .filter { it !in basin })
        return walkFrontier(frontier, basin + current)
    }

    val frontier = ArrayDeque<GridLocation>()
    frontier.add(start)
    return walkFrontier(frontier, mutableSetOf())
}

fun surrounding(x: Int, y: Int, matrix: List<List<GridLocation>>): List<GridLocation> {
    return orthogonalDirections.values
        .asSequence()
        .map { (dx, dy) -> x + dx to y + dy }
        .filterNot { (x, _) -> x < 0 }
        .filterNot { (x, _) -> x >= matrix[0].size }
        .filterNot { (_, y) -> y < 0 }
        .filterNot { (_, y) -> y >= matrix.size }
        .map { (x, y) -> matrix[y][x] }
        .toList()
}

fun parse(heightMap: List<String>): List<List<GridLocation>> {
    return heightMap.mapIndexed { y, line ->
        line.toList().mapIndexed { x, c ->
            GridLocation(x, y, c.toString().toInt())
        }
    }
}

val orthogonalDirections = mapOf(
    "E" to (1 to 0),
    "S" to (0 to 1),
    "W" to (-1 to 0),
    "N" to (0 to -1)
)
