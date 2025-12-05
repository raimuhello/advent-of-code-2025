package day4.part2

import java.io.File

fun main() {
    val grid: MutableList<CharArray> =
        File("src\\day4\\Day4_input.txt")
            .readLines()
            .map { it.toCharArray() }
            .toMutableList()
    val directions = listOf(
        -1 to -1,
        -1 to  0,
        -1 to  1,
        0 to -1,
        0 to  1,
        1 to -1,
        1 to  0,
        1 to  1,
    )
    var accessibleRollsChanged: Boolean
    var rollsRemoved = 0

    do {
        accessibleRollsChanged = false
        for (y in grid.indices) {
            cell@ for (x in grid[y].indices) {
                if (grid[y][x] != '@')
                    continue@cell

                var rollNeighborCount = 0

                for((dx, dy) in directions) {
                    val nx = x + dx
                    val ny = y + dy

                    if (!inBounds(grid, nx, ny))
                        continue

                    if (grid[ny][nx] == '@') {
                        rollNeighborCount++

                        if(rollNeighborCount >= 4) {
                            continue@cell
                        }
                    }
                }
                rollsRemoved++
                accessibleRollsChanged = true
                grid[y][x] = '.'
            }
        }
    } while (accessibleRollsChanged)
    println(rollsRemoved)
}

fun inBounds(grid: MutableList<CharArray>, x: Int, y: Int): Boolean {
    return (y in grid.indices && x in grid[0].indices)
}

