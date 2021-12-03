package day2

import util.readResourceFile

fun main() {
    val depths = readResourceFile("day2_input.txt")
//    val depths = readResourceFile("day2_sample.txt")
    val navigatePart1 = depths
        .fold(NavigatePart1(0, 0)) { nav, comm -> nav.newPosition(comm) }
    println("part 1: ${navigatePart1.finalNo()}")

    val navigatePart2 = depths
        .fold(NavigatePart2(0, 0, 0)) { nav, comm -> nav.newPosition(comm) }
    println("part 2: ${navigatePart2.finalNo()}")
}

class NavigatePart2(val depth: Int, val distance: Int, val aim: Int) {

    fun newPosition(commandAndVelocity: String): NavigatePart2 {
        val parts = commandAndVelocity.split(" ")
        val command = parts.first()
        val velocity = parts.last().toInt()
        var newDistance = distance
        var newDepth = depth
        var newAim = aim
        when (command) {
            "forward" -> {
                newDistance = distance + velocity
                newDepth = depth + aim * velocity
                newDepth = if (newDepth < 0) 0 else newDepth
            }
            "down" -> newAim = aim + velocity
            "up" -> {
                newAim = aim - velocity
                newAim = if (newAim < 0) 0 else newAim
            }
            else -> throw RuntimeException("Unknown command '$command'")
        }
        return NavigatePart2(newDepth, newDistance, newAim)
    }

    fun finalNo() : Int {
        return depth * distance
    }
}
class NavigatePart1(val depth: Int, val distance: Int) {

    fun newPosition(commandAndVelocity: String): NavigatePart1 {
        val parts = commandAndVelocity.split(" ")
        val command = parts.first()
        val velocity = parts.last().toInt()
        var newDistance = distance
        var newDepth = depth
        when (command) {
            "forward" -> newDistance = distance + velocity
            "down" -> newDepth = depth + velocity
            "up" -> {
                newDepth = depth - velocity
                newDepth = if (newDepth < 0) 0 else newDepth
            }
            else -> throw RuntimeException("Unknown command '$command'")
        }
        return NavigatePart1(newDepth, newDistance)
    }

    fun finalNo() : Int {
        return depth * distance
    }
}



