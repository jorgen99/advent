package day10

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import util.readResourceFile

internal class Day10Test {

    private val sample = """
        [({(<(())[]>[[{[]{<()<>>
        [(()[<>])]({[<{<<[]>>(
        {([(<{}[<>[]}>{[]{[(<()>
        (((({<>}<{<{<>}{[]{[]{}
        [[<[([]))<([[{}[[()]]]
        [{[{({}]{}}([{[{{{}}([]
        {<[[]]>}<{[{[{[]{()[[[]
        [<(<(<(<{}))><([]([]()
        <{([([[(<>()){}]>(<<{{
        <{([{{}}[<[[[<>{}]]]>[]]
    """
        .trimIndent()
        .lines()

    data class Sample(val chunks: String, val complete: String, val score: Int)

    private val sample2 = """
        [({(<(())[]>[[{[]{<()<>>  ;   }}]])})]    ;   288957 
        [(()[<>])]({[<{<<[]>>(    ;   )}>]})      ;   5566
        (((({<>}<{<{<>}{[]{[]{}   ;   }}>}>))))   ;   1480781
        {<[[]]>}<{[{[{[]{()[[[]   ;   ]]}}]}]}>   ;   995444
        <{([{{}}[<[[[<>{}]]]>[]]  ;   ])}>        ;   294
    """.trimIndent()
        .lines()
        .asSequence()
        .map { it.split(Regex(";")) }
        .flatten()
        .map { it.trim() }
        .chunked(3)
        .map { Sample(it[0], it[1], it[2].toInt()) }
        .toList()


    @Test
    fun it_should_calculate_the_ax_error_score_for_the_input() {
        val lines = readResourceFile("day10_input.txt")
        val sum = middleCompletionScore(lines)
        assertThat(sum).isEqualTo(2391385187)
    }

    @Test
    fun it_should_score() {
        val score = score("])]})>)]]}}}>]")
        assertThat(score).isEqualTo(2816133597L)
    }

    @Test
    fun it_should_find_sing_braces_for_the_sample() {
        val middleScore = middleCompletionScore(sample)
        assertThat(middleScore).isEqualTo(288957)
    }

    @Test
    fun it_should_find_closing_braces_for_the_sample() {
        sample2.forEach { (chunks, complete, _) ->
            assertThat(finishIncomplete(chunks)).isEqualTo(complete)
        }
    }


    @Test
    fun it_should_find_no_corrupted_lines_in_the_legal_chunks() {
        val anyCorrupt =
            listOf("([])", "{()()()}", "<([{}])>", "[<>({}){}[([])<>]]", "(((((((((())))))))))")
                .map { findCorruption(it) }
                .filter { (i, _) -> i != -1 }
                .map { (_, c) -> c }

        assertThat(anyCorrupt).hasSize(0)
    }

    @Test
    fun it_should_calculate_the_syntax_error_score_for_the_sample() {
        val sum = syntaxErrorScore(sample)
        assertThat(sum).isEqualTo(26397)
    }

    @Test
    fun it_should_calculate_the_syntax_error_score_for_the_input() {
        val lines = readResourceFile("day10_input.txt")
        val sum = syntaxErrorScore(lines)
        assertThat(sum).isEqualTo(390993)
    }

}