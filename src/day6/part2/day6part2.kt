package day6.part2

import java.io.File

fun main() {
    val lines: List<String> = File("src\\day6\\Day6_input.txt").readLines()

    val charMatrix: Array<CharArray> = lines
        .dropLast(1)
        .map { it.toCharArray() }
        .toTypedArray()

    val operators: List<Char> = lines.last()
        .trim()
        .split(Regex("\\s+"))
        .map { it[0] }


    val height = charMatrix.size
    val width = charMatrix[0].size

    val verticalNumbersMatrix = mutableListOf<MutableList<Int>>()
    var currentBlock = mutableListOf<Int>()

    for (col in 0 until width) {

        val isBlankColumn = (0 until height).all { row ->
            charMatrix[row][col] == ' '
        }

        if (isBlankColumn) {
            if (currentBlock.isNotEmpty()) {
                verticalNumbersMatrix.add(currentBlock)
                currentBlock = mutableListOf()
            }
            continue
        }

        val sb = StringBuilder()
        for (row in 0 until height) {
            val c = charMatrix[row][col]
            if (c != ' ') sb.append(c)
        }

        currentBlock.add(sb.toString().toInt())
    }

    if (currentBlock.isNotEmpty()) {
        verticalNumbersMatrix.add(currentBlock)
    }

    val blockCount = verticalNumbersMatrix.size
    val blockTotals = LongArray(blockCount)

    for (i in 0 until blockCount) {
        val op = operators[i]
        val values = verticalNumbersMatrix[i]

        var total: Long = if (op == '+') 0 else 1

        for (v in values) {
            if (op == '+') {
                total += v
            }
            else {
                total *= v
            }
        }
        blockTotals[i] = total
    }
    println(blockTotals.sum())
}