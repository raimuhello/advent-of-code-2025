package day6.part1

import java.io.File

fun main() {
    val inputRaw: List<String> = File("src\\day6\\Day6_input.txt").readLines()

    val rows = inputRaw.size-1
    val cols = inputRaw[0].split(Regex("\\s+")).size

    val valueArray = Array(rows, {IntArray(cols)})

    for (i in 0 until rows)
    {
        val parts = inputRaw[i].split(Regex("\\s+"))
        for (j in 0 until cols)
        {
            valueArray[i][j] = parts[j].toInt()
        }
    }

    val operationRow = inputRaw[4].split(Regex("\\s+"))
    val columnTotals = LongArray(cols)

    for (col in 0 until cols) {
        val op = operationRow[col]
        var sum: Long = if (op == "+") 0 else 1
        for (row in 0 until rows) {
            if (op == "+") {
                sum += valueArray[row][col]
            } else
                sum *= valueArray[row][col]
        }
        columnTotals[col] = sum
    }

    println(columnTotals.sum())
}