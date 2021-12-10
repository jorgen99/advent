package day7

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

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
    fun it_should_calculate_fuel_cosumption() {
        val fuel = fuel(samplePositions)
        assertThat(fuel).isEqualTo(37)
    }
}