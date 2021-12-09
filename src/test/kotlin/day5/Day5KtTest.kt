package day5

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day5KtTest {

    private val sampleVentLines = """
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
    """.lines()

    @Test
    fun it_should_split_and_filter_into_lines() {
        val lines = parseCoords(sampleVentLines)
        assertThat(lines).hasSize(10)
    }

    @Test
    fun it_should_parse_into_coordinates() {
        val lines = parseCoords(sampleVentLines)
        assertThat(lines.first().first).isEqualTo(0 to 9)
        assertThat(lines.last().second).isEqualTo(8 to 2)
    }

    @Test
    fun it_should_calculate_coordinates_of_horizontal_line() {
        val l = Pair(Pair(0, 9), Pair(5, 9))
        val lineCoords = line(l)
        assertThat(lineCoords)
            .containsExactly(
                0 to 9,
                1 to 9,
                2 to 9,
                3 to 9,
                4 to 9,
                5 to 9
            )
    }

    @Test
    fun it_should_calculate_coordinates_of_a_reverse_horizontal_line() {
        val l = Pair(Pair(5, 9), Pair(0, 9))
        val lineCoords = line(l)
        assertThat(lineCoords)
            .containsExactly(
                0 to 9,
                1 to 9,
                2 to 9,
                3 to 9,
                4 to 9,
                5 to 9
            )
    }

    @Test
    fun it_should_calculate_coordinates_of_vertical_line() {
        val l = Pair(Pair(2, 1), Pair(2, 3))
        val lineCoords = line(l)
        assertThat(lineCoords)
            .containsExactly(
                2 to 1,
                2 to 2,
                2 to 3
            )
    }

    @Test
    fun it_should_calculate_coordinates_of_a_reverse_vertical_line() {
        val l = Pair(Pair(2, 3), Pair(2, 1))
        val lineCoords = line(l)
        assertThat(lineCoords)
            .containsExactly(
                2 to 1,
                2 to 2,
                2 to 3
            )
    }

    @Test
    fun it_should_calculate_coordinates_of_diagonal_line() {
        val l = Pair(Pair(0, 2), Pair(4, 6))
        val lineCoords = line(l)
        assertThat(lineCoords)
            .containsExactly(
                0 to 2,
                1 to 3,
                2 to 4,
                3 to 5,
                4 to 6
            )
    }

    @Test
    fun it_should_calculate_coordinates_of_a_reverse_diagonal_line() {
        val l = Pair(Pair(8, 2), Pair(2, 8))
        val lineCoords = line(l)
        assertThat(lineCoords)
            .containsExactly(
                8 to 2,
                7 to 3,
                6 to 4,
                5 to 5,
                4 to 6,
                3 to 7,
                2 to 8
            )
    }

    @Test
    fun it_should_calculate_coordinates_of_another_reverse_diagonal_line() {
        val l = Pair(Pair(5, 5), Pair(8, 2))
        val lineCoords = line(l)
        assertThat(lineCoords)
            .containsExactly(
                8 to 2,
                7 to 3,
                6 to 4,
                5 to 5
            )
    }

    @Test
    fun it_should_calculate_coordinates_of_a_non_symetrical_diagonal_line() {
        val l = Pair(Pair(6, 4), Pair(2, 0))
        val lineCoords = line(l)
        assertThat(lineCoords)
            .containsExactly(
                2 to 0,
                3 to 1,
                4 to 2,
                5 to 3,
                6 to 4
            )
    }

    @Test
    fun it_should_increment_coordinates_of_a_line() {
        val l = Pair(Pair(2, 3), Pair(2, 1))
        val lineCoords = line(l)
        val seaFloor = mutableMapOf<Pair<Int, Int>, Int>()
        incrementCoordinates(seaFloor, lineCoords)
        assertThat(seaFloor).usingRecursiveComparison()
            .isEqualTo(
                mapOf(
                    (2 to 1) to 1,
                    (2 to 2) to 1,
                    (2 to 3) to 1
                )
            )
        val l2 = Pair(Pair(1, 2), Pair(4, 2))
        val lineCoords2 = line(l2)
        incrementCoordinates(seaFloor, lineCoords2)
        assertThat(seaFloor).usingRecursiveComparison()
            .isEqualTo(
                mapOf(
                    (1 to 2) to 1,
                    (2 to 1) to 1,
                    (2 to 2) to 2,
                    (3 to 2) to 1,
                    (4 to 2) to 1,
                    (2 to 3) to 1
                )
            )
    }

    @Test
    fun it_should_print_a_map() {
        val l = Pair(Pair(2, 3), Pair(2, 1))
        val lineCoords = line(l)
        val seaFloor = mutableMapOf<Pair<Int, Int>, Int>()
        incrementCoordinates(seaFloor, lineCoords)
        assertThat(seaFloor).usingRecursiveComparison()
            .isEqualTo(
                mapOf(
                    (2 to 1) to 1,
                    (2 to 2) to 1,
                    (2 to 3) to 1
                )
            )
        val l2 = Pair(Pair(1, 2), Pair(4, 2))
        val lineCoords2 = line(l2)
        incrementCoordinates(seaFloor, lineCoords2)

        val theMap = mapRepresentation(seaFloor, 5, 5).map { it.trim() }
        theMap.forEach { println(it) }
        assertThat(theMap).containsExactly(
            ". . . . . .",
            ". . 1 . . .",
            ". 1 2 1 1 .",
            ". . 1 . . .",
            ". . . . . .",
            ". . . . . ."
        )
    }

    @Test
    fun it_should_print_a_map_of_the_sample() {
             val seaFloor = mutableMapOf<Pair<Int, Int>, Int>()
            val noOfDangerAreas = noOfDangerAreas(seaFloor, sampleVentLines)
            println("step2: $noOfDangerAreas")
            val theMap = mapRepresentation(seaFloor, 9, 9).map { it.trim() }
        theMap.forEach { println(it) }
        assertThat(theMap).containsExactly(
            "1 . 1 . . . . 1 1 .",
            ". 1 1 1 . . . 2 . .",
            ". . 2 . 1 . 1 1 1 .",
            ". . . 1 . 2 . 2 . .",
            ". 1 1 2 3 1 3 2 1 1",
            ". . . 1 . 2 . . . .",
            ". . 1 . . . 1 . . .",
            ". 1 . . . . . 1 . .",
            "1 . . . . . . . 1 .",
            "2 2 2 1 1 1 . . . ."

        )
    }
}

fun mapRepresentation(seaFloor: MutableMap<Pair<Int, Int>, Int>, hSize: Int, vSize: Int): List<String> {
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
