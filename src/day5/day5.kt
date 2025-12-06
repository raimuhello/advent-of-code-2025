package day5

import java.io.File

fun main() {
    val inputRaw: List<String> = File("src\\day5\\Day5_input.txt").readLines()
    val freshRanges: MutableList<Pair<Long, Long>> = mutableListOf()
    var blankIndexMarker = 1

    var freshAvailableIngredients = 0

    for (string in inputRaw) {
        if (string == "")
        {
            blankIndexMarker = inputRaw.indexOf(string)
            break
        } else {
            val (startId, endId) = string.split('-')
            freshRanges.add(Pair(startId.toLong(), endId.toLong()))
        }
    }

    inputRaw.subList(blankIndexMarker+1, inputRaw.size).forEachIndexed outer@{ _, availableId ->
        freshRanges.forEachIndexed { _, pair ->
            if (availableId.toLong() >= pair.first && availableId.toLong() <= pair.second )
            {
                freshAvailableIngredients++
                return@outer
            }
        }
    }
    println("Fresh & Available ingredients: $freshAvailableIngredients")
    println("Fresh Ingredients: ${countUniqueCoveredValues(freshRanges)}")
}

fun countUniqueCoveredValues(ranges: MutableList<Pair<Long, Long>>): Long {
    val sorted = ranges.sortedBy { it.first }
    val mergedRanges = mutableListOf<Pair<Long, Long>>()

    for (range in sorted) {
        if (mergedRanges.isEmpty()) {
            mergedRanges.add(range)
        } else {
            val (lastStart, lastEnd) = mergedRanges.last()
            val (currentStart, currentEnd) = range

            if (currentStart <= lastEnd + 1) {
                mergedRanges[mergedRanges.lastIndex] =
                    lastStart to maxOf(lastEnd, currentEnd)
            } else {
                mergedRanges.add(range)
            }
        }
    }

    return mergedRanges.sumOf { (start, end) -> end - start + 1 }
}
