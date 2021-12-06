package day4

import util.readResourceFile
import util.replace

fun main() {
    val fileLines = readResourceFile("day4_input.txt")
//    val fileLines = readResourceFile("day4_sample.txt")
//    fileLines.forEach { println(it) }
    val numbersAndBoards = fileLines
        .filter { it.isNotEmpty() }
        .map { it.trim() }

    val numbers = numbersAndBoards
        .take(1)
        .first()
        .split(",")
        .map { it.toInt() }

    println(numbers)

    val boards = numbersAndBoards
        .drop(1)
        .chunked(5)
        .map { it.map { s -> s.split(Regex("\\s+")).map { n -> n.toInt() } } }

    step1(numbers, boards)
    step2(numbers, boards)
}

private fun step2(
    numbers: List<Int>,
    boards: List<List<List<Int>>>
) {
    var boards1 = boards
    numbers.forEach { n ->
        boards1 = markCalledNumber(boards1, n)

        val remaininBoards = boards1.filterNot { checkBingo(it) }
        if (remaininBoards.size == 1) {
            val remainingNumbers = numbers.subList(numbers.indexOf(n) + 1, numbers.size)
            println("remaining: $remainingNumbers")
            step1(remainingNumbers, remaininBoards)
            return
        }

    }
}

private fun step1(
    numbers: List<Int>,
    boards: List<List<List<Int>>>
) {
    var markedBoards = boards
    numbers.forEach { n ->
        markedBoards = markCalledNumber(markedBoards, n)

        val bingoBoard = markedBoards.firstOrNull { checkBingo(it) }
        if (bingoBoard != null) {
            val sumBord = bingoBoard.flatten().filter { it != -1 }.sum()
            val score = n * sumBord
            println("With number $n the following board with sum $sumBord scored $score")
            printBoards(bingoBoard)
            return
        }

    }
}

private fun markCalledNumber(boards: List<List<List<Int>>>, calledNo: Int) =
    boards
        .map { it.flatten() }
        .map { it.replace(calledNo, -1).chunked(5) }

fun printBoards(board: List<List<Int>>) {
    board.forEach {
        println(it.joinToString(" ") { i -> String.format("%1$2s", i) })
    }
    println("")
}

fun checkBingo(board: List<List<Int>>): Boolean {
    if (checkRows(board)) return true
    if (checkCols(board)) return true

    return false
}

private fun checkCols(board: List<List<Int>>): Boolean {
    for (i in 0..4) {
        if (board.sumOf { it[i] } == -5) return true
    }
    return false
}

private fun checkRows(board: List<List<Int>>): Boolean {
    return board.any { it.sum() == -5 }
}

