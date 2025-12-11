package day9

import java.awt.geom.Area
import java.awt.geom.Path2D
import java.awt.geom.Rectangle2D
import java.io.File
import kotlin.math.max
import kotlin.math.min

data class Point(val x: Long, val y: Long)

fun main() {
    val inputLines = File("src\\day9\\Day9_input.txt").readLines()

    val points = inputLines.map { line ->
        val (a, b) = line.split(',').map { it.toLong() }
        Point(a, b)
    }

    // Part 1 (yay ^^)
    var maxArea = 0L
    for (i in 0 until points.size) {
        for (j in 0 until points.size) {
            val minX = min(points[i].x, points[j].x)
            val maxX = max(points[i].x, points[j].x)
            val minY = min(points[i].y, points[j].y)
            val maxY = max(points[i].y, points[j].y)

            val area = (maxX - minX + 1) * (maxY - minY + 1)

            maxArea = max(maxArea, area)
        }
    }
    println(maxArea)

    // Part 2 (hell)
    var maxAreaInsidePolygon = 0L
    val polyArea = buildPolygonArea(points)

    for (i in 0 until points.size) {
        for (j in 0 until points.size) {
            val minX = min(points[i].x, points[j].x)
            val maxX = max(points[i].x, points[j].x)
            val minY = min(points[i].y, points[j].y)
            val maxY = max(points[i].y, points[j].y)

            val area = (maxX - minX + 1) * (maxY - minY + 1)

            if (area <= maxAreaInsidePolygon) continue

            // if polygon area is subtracted from rect area, rect should be empty if it's fully inside polygon :)
            val rectArea = Area(
                Rectangle2D.Double(
                    minX.toDouble(),
                    minY.toDouble(),
                    (maxX - minX).toDouble(),
                    (maxY - minY).toDouble())
            )
            rectArea.subtract(polyArea)

            if (rectArea.isEmpty) {
                maxAreaInsidePolygon = area
            }
        }
    }
    println(maxAreaInsidePolygon)
}

fun buildPolygonArea(points: List<Point>): Area {
    val path = Path2D.Double()
    if (points.isNotEmpty()) {
        path.moveTo(points[0].x.toDouble(), points[0].y.toDouble())
        for (i in 1 until points.size) {
            val point = points[i]
            path.lineTo(point.x.toDouble(), point.y.toDouble())
        }
        path.closePath()
    }
    return Area(path)
}