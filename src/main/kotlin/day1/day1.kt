package day1

fun main() {
//    val depths = util.readResourceFile("day1_input.txt")
    val depths = util.readResourceFile("day1_sample.txt")
    val count = depths.windowed(2)
        .filter { d ->
            println(d)
            d[1] > d[0]
        }
        .count()
    println("count: $count")
}