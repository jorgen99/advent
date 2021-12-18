package util

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UtilsTest {

    @Test
    fun it_should_transpose_a_matrix() {
        // 1 2 3
        // 4 5 6
        //
        // 1 4
        // 2 5
        // 3 6
        val m = listOf(
            listOf(1, 2, 3),
            listOf(4, 5, 6)
        )
        val mt = m.transpose()
        assertThat(mt.size).isEqualTo(3)
        assertThat(mt[0].size).isEqualTo(2)
        assertThat(mt[0]).containsExactly(1, 4)
        assertThat(mt[1]).containsExactly(2, 5)
        assertThat(mt[2]).containsExactly(3, 6)
    }
}