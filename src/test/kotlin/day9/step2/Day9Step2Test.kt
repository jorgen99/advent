package day9.step2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import util.readResourceFile

internal class Day9Step2Test {

    private val sample = """
        2199943210
        3987894921
        9856789892
        8767896789
        9899965678
    """.trimIndent()
        .lines()

    private var heightMap = parse(sample)

    @Test
    fun it_should_find_the_three_largest_basins() {
        val input = readResourceFile("day9_input.txt")
        val heightMap = parse(input)
        val product = productOfThreeLargest(heightMap)
        assertThat(product).isEqualTo(1069200)
    }

    @Test
    fun it_should_find_the_thre_largest_basins_of_the_sample() {
        val product = productOfThreeLargest(heightMap)
        assertThat(product).isEqualTo(1134)
    }

    @Test
    fun it_should_get_neighbors() {
        val graph = RectangularGrid(heightMap)
        val neighbors = graph.neighbors(GridLocation(3, 1))
        assertThat(neighbors).hasSize(3)
        assertThat(neighbors).containsExactly(
            GridLocation(4, 1, 8),
            GridLocation(3, 2, 6),
            GridLocation(2, 1, 8),
        )
    }

    @Test
    fun it_should_get_all_the_surrounding_points() {
        val surrounding = surrounding(3, 1, heightMap)
        assertThat(surrounding).hasSize(4)
        assertThat(surrounding).containsExactly(
            GridLocation(4, 1, 8),
            GridLocation(3, 2, 6),
            GridLocation(2, 1, 8),
            GridLocation(3, 0, 9),
        )
    }

}