package day2.part1

import java.io.File


fun main() {
    var sum: Long = 0
    val ranges: List<String> = File("src\\day2\\Day2_input.txt").readText().split(",")
    val pairs: MutableList<Pair<Long, Long>> = mutableListOf()
    val regex = Regex("^(.+)\\1$")

    for (range in ranges)
    {
        val (pairA, pairB) = range.split("-")
        pairs.add(Pair(pairA.toLong(), pairB.toLong()))
    }

    for (pair in pairs)
    {
        for (i in pair.first .. pair.second)
        {
            if (regex.matches(i.toString()))
            {
                sum += i
            }
        }
    }
    println(sum)
}
