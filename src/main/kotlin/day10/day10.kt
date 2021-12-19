package day10

fun middleCompletionScore(chunks: List<String>): Long {
    val sorted = chunks
        .asSequence()
        .map { it to findCorruption(it) }
        .filter { (_, p) -> p.first == -1 }
        .map { (chunk, _) -> finishIncomplete(chunk) }
        .map { score(it) }
        .sorted()
        .toList()
    return sorted
        .drop(sorted.size / 2)
        .first()
}

fun score(completedBy: String): Long {
    return completedBy
        .map { completeScore[it] }
        .fold(0L) { acc, i -> acc * 5 + (i ?: 0) }
}

fun finishIncomplete(line: String): String {
    val open = ArrayDeque<Char>()
    for (i in line.indices) {
        val c = line[i]
        if (open.isEmpty()) {
            if (c in matchingOpening.keys)
                throw RuntimeException("This should have been detected")
            open.addFirst(c)
            continue
        }
        if (c in matchingOpening.values) {
            open.addFirst(c)
            continue
        }
        val first = open.first()
        val matching = first == matchingOpening[c]
        if (!matching)
            throw RuntimeException("This should have been detected")

        open.removeFirst()
    }
    return open.map { matchingClosing[it] }
        .joinToString("")
}

fun syntaxErrorScore(chunks: List<String>) =
    chunks.map { findCorruption(it) }
        .filter { (i, _) -> i != -1 }
        .sumOf { (_, p) -> p }

fun findCorruption(line: String): Pair<Int, Int> {
    val open = ArrayDeque<Char>()
    for (i in line.indices) {
        val c = line[i]
        if (open.isEmpty()) {
            if (c in matchingOpening.keys) return i to (invalidScore[c] ?: 0)
            open.addFirst(c)
            continue
        }
        if (c in matchingOpening.values) {
            open.addFirst(c)
            continue
        }
        val first = open.first()
        val matching = first == matchingOpening[c]
        if (matching) open.removeFirst() else return i to (invalidScore[c] ?: 0)
    }
    return -1 to 1
}

private val matchingOpening = mapOf(
    ')' to '(',
    ']' to '[',
    '}' to '{',
    '>' to '<'
)
private val matchingClosing = mapOf(
    '(' to ')',
    '[' to ']',
    '{' to '}',
    '<' to '>'
)
private val invalidScore = mapOf(
    ')' to 3,
    ']' to 57,
    '}' to 1197,
    '>' to 25137
)
private val completeScore = mapOf(
    ')' to 1,
    ']' to 2,
    '}' to 3,
    '>' to 4
)
