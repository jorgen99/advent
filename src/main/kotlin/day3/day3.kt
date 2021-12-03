package day3

import util.readResourceFile

fun main() {
    val values = readResourceFile("day3_input.txt")
//    val values = readResourceFile("day3_sample.txt")
    println("values: ${values.size}")
    val result = gammaAndEpsilon(values)
    println("step1: ${result.power()}")
    val pair = oxygenAndCo2(values, result.gamma())
    val oxygenBinary = pair.first
    val oxy = Integer.parseInt(oxygenBinary, 2)
    println("oxygen: $oxygenBinary, asInt: $oxy")
    val co2Binary = pair.second
    val co2 = Integer.parseInt(co2Binary, 2)
    println("co2: $co2Binary, asInt: $co2")
    println("step2: ${oxy * co2}")
}

fun gammaAndEpsilon(values: List<String>) : Accumulator {
    return values
        .fold(Accumulator(IntArray(values[0].length), 0)) { acc, bits -> acc.add(bits) }
}

fun oxygenAndCo2(values: List<String>, bitCount: String): Pair<String, String> {

    fun filterOnMask(xs: List<String>, index: Int, mask: String, isEpsilon: Boolean): List<String> {
        if(xs.size == 1 || index > mask.length) {
            return xs
        }
        val remainingValues = xs.filter { it[index] == mask[index] }
        val accForRest = gammaAndEpsilon(remainingValues)
        val newMask = if(isEpsilon) accForRest.epsilon() else accForRest.gamma()
        return filterOnMask(remainingValues, index + 1, newMask, isEpsilon)
    }

    val oxygen = filterOnMask(values, 0, bitCount, false).first()
    val co2 = filterOnMask(values, 0, bitCount.invertBinaryString(), true).first()
    return Pair(oxygen, co2)
}

class Accumulator(private var bitCount: IntArray, private val count: Int) {
    fun add(bits: String): Accumulator {
        if (bitCount.isEmpty()) bitCount = IntArray(bits.length)
        bits
            .map { it.toString().toInt() }
            .forEachIndexed { i, d ->
                bitCount[i] = bitCount[i] + d
            }
        return Accumulator(bitCount, count + 1)
    }

    fun power(): Int {
        val gammaString = gamma()
        val epsilonString = gammaString.invertBinaryString()
        val gamma = gammaString.toBinaryInt()
        val epsilon = epsilonString.toBinaryInt()
        return gamma * epsilon
    }

    fun gamma(): String {
        return bitCount.joinToString("") { if (it >= count - it) "1" else "0" }
    }

    fun epsilon() : String {
        return gamma().invertBinaryString()
    }
}

fun String.toBinaryInt(): Int = Integer.parseInt(this, 2)
fun String.invertBinaryString() : String {
    return this.map { if (it == '1') "0" else "1"}.joinToString("")
}
