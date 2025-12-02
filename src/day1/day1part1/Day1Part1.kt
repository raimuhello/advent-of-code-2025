package day1.day1part1

import java.io.File
import kotlin.math.abs

fun main() {

    val lines: List<String> = File("src\\day1\\Day1_input.txt").readLines()
    var currentDegrees = 50
    var password = 0

    for (line in lines)
    {
        currentDegrees = turnDial(currentDegrees, line)
        if (currentDegrees == 0)
        {
            password++
        }
    }
    println(password)
}


fun turnDial(degrees: Int, instruction: String): Int {
    val direction = instruction.take(1)
    var valueText = instruction.drop(1)

    while (valueText.length > 2)
    {
        valueText = valueText.drop(1)
    }

    val value = valueText.toInt()

    var newDegrees: Int = degrees

    if (direction == "L")
    {
        newDegrees -= value
        if (newDegrees < 0)
        {
            newDegrees = 100 - abs(newDegrees)
        }
    } else {
        newDegrees += value
        if (newDegrees >= 100)
        {
            newDegrees -= 100
        }
    }
    return newDegrees
}