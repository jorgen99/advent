package day6

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


internal class Day6KtTest {

    private val initialFish = listOf(3, 4, 3, 1, 2)

    @Test
    fun it_should_count_numbers() {
        val c = count(initialFish)
        assertThat(c)
            .usingRecursiveComparison()
            .isEqualTo(mapOf(3 to 2, 4 to 1, 1 to 1, 2 to 1))
    }

    @Test
    fun it_should_increment_one_day() {
        val fish = count(initialFish)
        val nextDay = nextGen(fish)

        val fistDayCount = mapOf(2 to 2, 3 to 1, 0 to 1, 1 to 1, 8 to 0, 6 to 0)
        assertThat(nextDay)
            .usingRecursiveComparison()
            .isEqualTo(fistDayCount)

        val oneDayForward = fishAfterDays(initialFish, 1)
        assertThat(oneDayForward)
            .usingRecursiveComparison()
            .isEqualTo(fistDayCount)
    }

    @Test
    fun it_should_increment_two_days() {
        val fish = count(initialFish)
        val twoDaysForward = nextGen(nextGen(fish))

        val secondDayCount = mapOf(1 to 2, 2 to 1, 6 to 1, 0 to 1, 8 to 1, 7 to 0, 5 to 0)
        assertThat(twoDaysForward)
            .usingRecursiveComparison()
            .isEqualTo(secondDayCount)
    }

    @Test
    fun it_should_increment_two_days_with_fish_afterDays() {
        val secondDayCount = count(listOf(1,2,1,6,0,8)) + (5 to 0) + (7 to 0)
        val twoForward = fishAfterDays(initialFish, 2)
        assertThat(twoForward)
            .usingRecursiveComparison()
            .isEqualTo(secondDayCount)
    }

    @Test
    fun it_should_increment_three_days() {
        val fish = count(initialFish)
        val threeDaysForward = nextGen(nextGen(nextGen(fish)))

        val threeDayCount = count(listOf(0,1,0,5,6,7,8)) + (4 to 0)
        assertThat(threeDaysForward)
            .usingRecursiveComparison()
            .isEqualTo(threeDayCount)
    }

    @Test
    fun it_should_increment_tree_days_with_fish_afterDays() {
        val threeDayCount = count(listOf(0,1,0,5,6,7,8)) + (4 to 0)
        val threeDays = fishAfterDays(initialFish, 3)
        assertThat(threeDays)
            .usingRecursiveComparison()
            .isEqualTo(threeDayCount)
    }

    @Test
    fun is_should_increment_18_days() {
        val fish = "6,0,6,4,5,6,0,1,1,2,6,0,1,1,1,2,2,3,3,4,6,7,8,8,8,8"
            .split(Regex(","))
            .map { it.toInt() }

        val fishCount = count(fish)
        val eightteenDays= fishAfterDays(initialFish, 18)
        assertThat(eightteenDays)
            .usingRecursiveComparison()
            .isEqualTo(fishCount)
    }
}


