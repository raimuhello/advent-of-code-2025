package day7

import java.io.File

fun main() {
    val lines = File("src\\day7\\Day7_input.txt").readLines().toMutableList()
    val grid = lines.map { it.toMutableList() }.toMutableList()

    // Iterative Part 1
    var splitCount = 0

    for (y in 1 until grid.size - 1) {
        val aboveRow = grid[y - 1]
        val currRow = grid[y]
        val belowRow = grid[y + 1]

        for (x in currRow.indices) {
            val aboveChar = aboveRow.getOrNull(x)
            val currChar = currRow[x]
            val belowChar = belowRow.getOrNull(x)

            // First char
            if (aboveChar == 'S') {
                currRow[x] = '|'
            }

            // Beam down
            if (currChar == '.' && aboveChar == '|') {
                currRow[x] = '|'
            }

            // Beam hit splitter
            if (currRow[x] == '|' && belowChar == '^') {
                if (x > 0) belowRow[x - 1] = '|'
                if (x < belowRow.lastIndex) belowRow[x + 1] = '|'
                splitCount++
            }
        }
    }
    println(splitCount)

    // DP Part 2
    val height = grid.size
    val width = grid[0].size

    val ways = Array(height) { LongArray(width) { 0L } }

    for (x in 0 until width) {
        if (grid[0][x] == 'S') {
            ways[0][x] = 1
        }
    }

    for (y in 1 until height) {
        for (x in 0 until width) {

            // Above
            if (grid[y - 1][x] == '|' || grid[y - 1][x] == 'S') {
                ways[y][x] += ways[y - 1][x]
            }

            // Above-left
            if (x > 0 && grid[y - 1][x - 1] == '^') {
                ways[y][x] += ways[y - 1][x - 1]
            }

            // Above-right
            if (x < width - 1 && grid[y - 1][x + 1] == '^') {
                ways[y][x] += ways[y - 1][x + 1]
            }
        }
    }
    println(ways[height - 1].sum())
}