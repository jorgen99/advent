package day3

import util.readResourceFile

fun main() {
    val depths = readResourceFile("day3_input.txt")
//    val depths = readResourceFile("day3_sample.txt")
    println("depths: ${depths.size}")
    val result =
        depths
            .fold(Accumulator(IntArray(depths[0].length), 0)) { acc, bits -> acc.add(bits) }
    println("step1: ${result.power()}")
}

class Accumulator(private var bitCount: IntArray, private val count: Int) {
    fun add(bits: String): Accumulator {
        println(bits)
        if (bitCount.isEmpty()) bitCount = IntArray(bits.length)
        bits
            .map { it.toString().toInt() }
            .forEachIndexed { i, d ->
                bitCount[i] = bitCount[i] + d
            }
        return Accumulator(bitCount, count + 1)
    }

    fun power(): Int {
        val half = count / 2
        val gammaString = bitCount.joinToString("") { if (it > half) "1" else "0" }
        println("gamma:   $gammaString")
        val epsilonString = bitCount.joinToString("") { if (it > half) "0" else "1" }
        println("epsilon: $epsilonString")
        val gamma = gammaString.toBinaryInt()
        val epsilon = epsilonString.toBinaryInt()
        println("gamma: $gamma, epsilon: $epsilon")
        return gamma * epsilon
    }
}
fun String.toBinaryInt(): Int = Integer.parseInt(this, 2)


