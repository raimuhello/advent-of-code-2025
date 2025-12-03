package day1.part2

import java.io.File

fun main() {

    val lines: List<String> = File("src\\day1\\Day1_input.txt").readLines()
    var currentDegrees = 50
    var password = 0

    for (line in lines)
    {
        val (newDegrees, clicks) = turnDial(currentDegrees, line)
        password += clicks
        currentDegrees = newDegrees
        println("$password \n")
    }

    println("Final tally: $password")
}


fun turnDial(degrees: Int, instruction: String): Pair<Int, Int> {
    var clicks = 0

    val direction: String = instruction.take(1)
    val valueFull: Int = instruction.drop(1).toInt()

    println("Start: $degrees, instruction: $instruction")

    val laps: Int = valueFull / 100
    val remainder: Int = valueFull % 100


    clicks += laps
    println("Added $laps laps")

    val newDegrees = if (direction == "R")
    {
        (degrees + remainder) % 100
    } else {
        (degrees - remainder + 100) % 100
    }

    if (direction == "R") {
        if (newDegrees < degrees) {
            clicks++
            println("Overflow from remainder, adding 1 click")
        }
    } else {
        if ((newDegrees > degrees || newDegrees == 0) && degrees != 0) {
            clicks++
            println("Underflow from remainder, adding 1 click")
        }
    }

    println("New degrees: $newDegrees")
    return Pair(newDegrees, clicks)
}