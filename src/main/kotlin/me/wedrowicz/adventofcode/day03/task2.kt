package me.wedrowicz.adventofcode.day03

fun main() {
    val fileContent = {}.javaClass.classLoader.getResource("3.in")!!.readText(Charsets.UTF_8)
    val paths = fileContent.trim().split("\n").map { path -> path.split(",") }
    val pixelIntersection = PixelIntersectionShortestPath(paths[0], paths[1])

    println(pixelIntersection.calculateShortestPathToIntersection())
}
