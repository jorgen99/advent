package day10

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import util.readResourceFile

internal class Day10Test {

    val sample = """
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

    @Test
    fun it_should_find_no_corrupted_lines_in_the_legal_chunks() {
        val anyCorrupt =
            listOf("([])", "{()()()}", "<([{}])>", "[<>({}){}[([])<>]]", "(((((((((())))))))))")
                .map { isCorrupt(it) }
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