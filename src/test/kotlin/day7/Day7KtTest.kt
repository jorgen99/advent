package day7

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.math.abs

internal class Day7KtTest {

    private val samplePositions =
        "16,1,2,0,4,2,7,1,2,14"
            .split(",")
            .map { it.toInt() }

    @Test
    fun it_should_calculate_the_median_position() {
        // sorted: 0,1,1,2,2,2,4,7,12,14
        // median = 2
        assertThat(samplePositions.median()).isEqualTo(2)
    }

    @Test
    fun it_should_calculate_step1_fuel_cosumption() {
        val fuel = fuel(samplePositions, differance)
        assertThat(fuel).isEqualTo(37)
    }

    @Test
    fun it_should_calculate_the_diff_between_two_ints() {
            // 1, 2, 3, 4, 5, 6, 7, 6
            // 1, 3, 6, 10, 15, 21
            //   2  3
            // n + sum(n + 1)
        assertThat(differance(2, 7)).isEqualTo(5)
        assertThat(differance(10, 3)).isEqualTo(7)
    }
    @Test
    fun it_should_calculate_the_exponential_inc() {
        // 1, 2, 3, 4,   5,  6, 7,  6
        //   1, 3, 6, 10, 15, 21, 28
        //   2  3
        // n + sum(n + 1)
        assertThat(increasing(2, 4)).isEqualTo(3)
        assertThat(increasing(52, 56)).isEqualTo(10)
        assertThat(increasing(20, 27)).isEqualTo(28)
    }

    @Test
    fun it_should_calculate_step2_fuel_cosumption() {
        val fuel = fuel(samplePositions, increasing)
        assertThat(fuel).isEqualTo(168)
    }

}