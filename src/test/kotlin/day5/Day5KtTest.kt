package day5

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day5KtTest {

    @Test
    fun it_should_split_and_filter_into_lines() {
        val lines = parseCoords(sampleVentLines.lines())
        assertThat(lines).hasSize(10)
    }

    @Test
    fun it_should_parse_into_coordinates() {
        val lines = parseCoords(sampleVentLines.lines())
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

//    @Test
//    fun it_should_calculate_coordinates_of_diagonal_line() {
//        val l = Pair(Pair(0, 2), Pair(4, 6))
//        val lineCoords = line(l)
//        assertThat(lineCoords)
//            .containsExactly(
//                0 to 2,
//                1 to 3,
//                2 to 4,
//                3 to 5,
//                4 to 6
//            )
//    }

    @Test
    fun it_should_increment_coordinates_of_a_line() {
        val l = Pair(Pair(2, 3), Pair(2, 1))
        val lineCoords = line(l)
        val seaFloor = mutableMapOf<Pair<Int, Int>, Int>()
        incrementCoordinates(seaFloor, lineCoords)
        assertThat(seaFloor).usingRecursiveComparison()
            .isEqualTo(mapOf(
                (2 to 1) to 1,
                (2 to 2) to 1,
                (2 to 3) to 1)
            )
        val l2 = Pair(Pair(1, 2), Pair(4, 2))
        val lineCoords2 = line(l2)
        incrementCoordinates(seaFloor, lineCoords2)
        assertThat(seaFloor).usingRecursiveComparison()
            .isEqualTo(mapOf(
                (1 to 2) to 1,
                (2 to 1) to 1,
                (2 to 2) to 2,
                (3 to 2) to 1,
                (4 to 2) to 1,
                (2 to 3) to 1)
            )
    }

    @Test
    fun it_should_print_a_map() {
        val l = Pair(Pair(2, 3), Pair(2, 1))
        val lineCoords = line(l)
        val seaFloor = mutableMapOf<Pair<Int, Int>, Int>()
        incrementCoordinates(seaFloor, lineCoords)
        assertThat(seaFloor).usingRecursiveComparison()
            .isEqualTo(mapOf(
                (2 to 1) to 1,
                (2 to 2) to 1,
                (2 to 3) to 1)
            )
        val l2 = Pair(Pair(1, 2), Pair(4, 2))
        val lineCoords2 = line(l2)
        incrementCoordinates(seaFloor, lineCoords2)

        val theMap = printMap(seaFloor, 5, 5).map { it.trim() }
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
}
