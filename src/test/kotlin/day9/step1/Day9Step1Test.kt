package day9.step1

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day9Step1Test {

    private val sample = """
        2199943210
        3987894921
        9856789892
        8767896789
        9899965678
    """.trimIndent()
        .lines()

    private var matrix = parse(sample)

    @Test
    fun it_should_parse_the_sample_to_a_matrix() {
        assertThat(matrix).hasSize(5)
        assertThat(matrix[3]).hasSize(10)
        assertThat(matrix[3][2]).isEqualTo(6)
        assertThat(matrix[1][8]).isEqualTo(2)
        assertThat(matrix[2][9]).isEqualTo(2)
    }

    @Test
    fun it_should_get_the_hights_of_one_location() {
        val surroundingHeights = surroundingHeights(1, 3, matrix)
        assertThat(surroundingHeights)
            .containsExactly(6, 8, 8, 8)
    }

    @Test
    fun it_should_get_the_hights_of_one_edge_location() {
        val surroundingHeights = surroundingHeights(9, 2, matrix)
        assertThat(surroundingHeights)
            .containsExactly(9, 9, 1)
    }

    @Test
    fun it_should_determin_a_low_point() {
        assertThat(isLowPoint(1, 0, matrix)).isTrue
        assertThat(isLowPoint(9, 0, matrix)).isTrue
        assertThat(isLowPoint(2, 2, matrix)).isTrue
        assertThat(isLowPoint(6, 4, matrix)).isTrue
    }

    @Test
    fun it_should_find_all_lowpoints() {
        assertThat(lowPoints(matrix))
            .containsAll(
                listOf(
                    (1 to 0),
                    (9 to 0),
                    (2 to 2),
                    (6 to 4)
                )
            )
    }

    @Test
    fun it_should_calculate_the_risk_level() {
        assertThat(riskLevel(matrix)).isEqualTo(15)
    }
}