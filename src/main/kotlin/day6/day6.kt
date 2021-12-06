package day6

import util.readResourceFile
import util.replace

fun main() {
//    val initialFish = listOf("3,4,3,1,2")
    val initialFish = readResourceFile("day6_input.txt")
        .first()
        .split(",")
        .map { it.toInt() }

    val fish = fishAfterDays(initialFish, 80)
    println("step1: ${fish.size}")
}

fun Iterable<Int>.addEights() = this + this.filter { it == -1 }.map { 8 }
fun Iterable<Int>.reduceAllByOne() = this.map { i -> i - 1 }

fun fishAfterDays(fish: List<Int>, days: Int) : List<Int> {
    tailrec fun oneDay(fish: List<Int>, dayNo: Int) : List<Int> {
        if(dayNo == 0) {
            return fish
        }
        return oneDay(fish.nextGen(), dayNo - 1)
    }
    return oneDay(fish, days)
}

fun Iterable<Int>.nextGen() =
    this
        .reduceAllByOne()
        .addEights()
        .replace(-1, 6)









