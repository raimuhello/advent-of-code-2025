package day3

import java.io.File

fun main() {
    val banks: List<String> = File("src\\day3\\Day3_input.txt").readLines()
    var joltage = 0

    for (bank in banks)
    {
        val firstIndex = indexOfLargestDigit(bank, true)
        val bankRemainder = bank.substring(firstIndex+1)
        val secondIndex = indexOfLargestDigit(bankRemainder, false)

        val builder = StringBuilder()

        val bankJoltage: String = (builder.append(bank[firstIndex]).append(bankRemainder[secondIndex])).toString()
        joltage += bankJoltage.toInt()
    }
    println(joltage)
}

fun indexOfLargestDigit(s: String, firstIndex: Boolean): Int {
    var max = s.maxOrNull() ?: return -1
    if (s.indexOf(max) == s.length-1 && firstIndex)
    {
        val newString = (s.substringBefore(max))
        max = newString.maxOrNull() ?: return -1
    }
    return s.indexOf(max)
}