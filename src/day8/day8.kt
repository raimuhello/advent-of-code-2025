package day8

import java.io.File

fun main() {
    val inputLines = File("src\\day8\\Day8_input.txt").readLines()

    val boxes = inputLines.map { line ->
        val(x, y, z) = line.split(",").map {
            it.trim().toInt()
        }
        Point(x, y, z)
    }
    val partOneBoxCount = boxes.size
    val connections = ArrayList<Edge>()

    for (i in 0 until partOneBoxCount) {
        for (j in i + 1 until partOneBoxCount) {
            val dx = boxes[i].x.toLong() - boxes[j].x.toLong()
            val dy = boxes[i].y.toLong() - boxes[j].y.toLong()
            val dz = boxes[i].z.toLong() - boxes[j].z.toLong()
            val distSq = (dx * dx + dy * dy + dz * dz)
            connections.add(Edge(i, j, distSq))
        }
    }

    connections.sortBy { it.distanceSquared }
    connections.sortBy {
        it.distanceSquared
    }

    // Part 1 -- First 1000 Kruskal edges
    var unionFind = UnionFind(partOneBoxCount)
    var connectionsProcessed = 0
    var index = 0

    while (connectionsProcessed < 1000) {
        val connection = connections[index++]
        unionFind.union(connection.a, connection.b)
        connectionsProcessed++
    }

    val circuitSizes = IntArray(partOneBoxCount)
    for (i in 0 until partOneBoxCount) {
        circuitSizes[unionFind.find(i)]++
    }

    val sortedCircuitSizes = circuitSizes.filter {
        it > 0
    }.sortedDescending()

    val partOneResult =
        sortedCircuitSizes[0].toLong() *
                sortedCircuitSizes[1].toLong() *
                sortedCircuitSizes[2].toLong()

    println(partOneResult)

    // Part 2 -- Last Kruskal edge that connects all boxes
    var partTwoBoxCount = partOneBoxCount
    unionFind = UnionFind(partTwoBoxCount)

    var indexA = 0
    var indexB = 0

    for (connection in connections)
    {
        if (unionFind.union(connection.a, connection.b)) {
            partTwoBoxCount--
            if (partTwoBoxCount == 1) {
                indexA = connection.a
                indexB = connection.b
            }
        }
    }

    val boxA: Point = boxes[indexA]
    val boxB: Point = boxes[indexB]

    val partTwoResult = boxA.x.toLong() * boxB.x.toLong()
    println(partTwoResult)

}

data class Point(val x: Int, val y: Int, val z: Int)

data class Edge(val a: Int, val b: Int, val distanceSquared: Long)
