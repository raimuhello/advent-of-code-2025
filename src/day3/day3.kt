package day3

import java.io.File

fun main() {
    val banks: List<String> = File("src\\day3\\Day3_input.txt").readLines()
    var firstTotalJoltage = 0
    var secondTotalJoltage: Long = 0

    for (bank in banks)
    {
        val firstIndex = indexOfLargestDigit(bank, 2)
        val bankRemainder = bank.substring(firstIndex+1)
        val secondIndex = indexOfLargestDigit(bankRemainder, 1)

        val builder = StringBuilder()

        val bankJoltage: String = (builder.append(bank[firstIndex]).append(bankRemainder[secondIndex])).toString()
        firstTotalJoltage += bankJoltage.toInt()
    }
    println(firstTotalJoltage)

    for (bank in banks)
    {
        val builder = StringBuilder()
        var joltage = ""
        var bankRemainder = ""
        for (i in 12 downTo  1)
        {
            if (i == 12)
            {
                val index = indexOfLargestDigit(bank, i)
                joltage += builder.append(bank[index])
                bankRemainder = bank.substring(index+1)
            } else {
                val index = indexOfLargestDigit(bankRemainder, i)
                joltage = builder.append(bankRemainder[index]).toString()
                bankRemainder = bankRemainder.substring(index+1)
            }
        }
        secondTotalJoltage += joltage.toLong()
    }
    println(secondTotalJoltage)
}

fun indexOfLargestDigit(s: String, number: Int): Int {
    val allowedMaxIndex = s.length - number

    var max = s.maxOrNull() ?: return -1
    var idx = s.indexOf(max)

    val lastAllowedRange = s.take(allowedMaxIndex + 1)

    if (!lastAllowedRange.contains(max)) {
        max = lastAllowedRange.maxOrNull() ?: return -1
        idx = s.indexOf(max)
    }

    return idx
}