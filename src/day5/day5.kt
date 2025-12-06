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
    println(freshAvailableIngredients)
}