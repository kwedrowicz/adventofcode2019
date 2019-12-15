package me.wedrowicz.adventofcode.day10

fun main() {
    val fileContent = {}.javaClass.classLoader.getResource("10.in")!!.readText(Charsets.UTF_8).trim()
    val map = fileContent.split("\n").map { it.trim().split("") }
    val asteroids = mutableListOf<Point>()
    for((y, line) in map.withIndex()) {
        for((x, cell) in line.withIndex()) {
            if(cell == "#") {
                asteroids.add(Point(x-1,y))
            }
        }
    }
    println(MonitoringStation(asteroids).getPositionOfNthAsteroidToDestroy(Point(29,28), 200))
}
