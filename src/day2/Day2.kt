package day2

import java.io.File


fun main() {
    val ranges: List<String> = File("src\\day2\\Day2_input.txt").readText().split(",")
    val pairs: MutableList<Pair<Long, Long>> = mutableListOf()
    
    var partOneSum: Long = 0
    var partTwoSum: Long = 0
    val partOneRegex = Regex("^(.+)\\1$")
    val partTwoRegex = Regex("^(.+)\\1+$")

    for (range in ranges)
    {
        val (pairA, pairB) = range.split("-")
        pairs.add(Pair(pairA.toLong(), pairB.toLong()))
    }

    for (pair in pairs)
    {
        for (i in pair.first .. pair.second)
        {
            if (partOneRegex.matches(i.toString()))
            {
                partOneSum += i
            }
            if (partTwoRegex.matches(i.toString()))
            {
                partTwoSum += i
            }
        }
    }
    println("Part 1 sum: $partOneSum")
    println("Part 2 sum: $partTwoSum")
}
