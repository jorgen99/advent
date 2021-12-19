package day10

fun syntaxErrorScore(sample: List<String>) =
    sample.map { isCorrupt(it) }
        .filter { (i, _) -> i != -1 }
        .sumOf { (_, p) -> p }

fun isCorrupt(line: String): Pair<Int, Int> {
    val open = ArrayDeque<Char>()
    for (i in line.indices) {
        val c = line[i]
        if (open.isEmpty()) {
            if (c in closing.keys) return i to (points[c] ?: 0)
            open.addFirst(c)
            continue
        }
        if (c in closing.values) {
            open.addFirst(c)
            continue
        }
        val first = open.first()
        val matching = first == closing[c]
        if (matching) open.removeFirst() else return i to (points[c] ?: 0)
    }
    return -1 to 1
}

private val closing = mapOf(
    ')' to '(',
    ']' to '[',
    '}' to '{',
    '>' to '<'
)
private val points = mapOf(
    ')' to 3,
    ']' to 57,
    '}' to 1197,
    '>' to 25137
)
