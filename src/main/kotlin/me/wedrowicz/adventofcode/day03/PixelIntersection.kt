package me.wedrowicz.adventofcode.day03

import kotlin.math.abs
import kotlin.math.min

class PixelIntersection {
    private val points1: MutableSet<Point> = mutableSetOf()
    private val points2: MutableList<Point> = mutableListOf()


    constructor(wire1: List<String>, wire2: List<String>) {
        var startingPoint = Point(0,0 )
        for(path in wire1) {
            points1.addAll(convertStringPathToPoints(path, startingPoint))
            startingPoint = points1.last()
        }

        startingPoint = Point(0,0)
        for(path in wire2) {
            points2.addAll(convertStringPathToPoints(path, startingPoint))
            startingPoint = points2.last()
        }
    }

    fun getClosestIntersection(): Int {
        var res = Int.MAX_VALUE
        for(point in points2) {
            if(points1.contains(point)) {
                res = min(res, abs(point.x) + abs(point.y))
            }
        }

        return res
    }

    private fun convertStringPathToPoints(path: String, startingPoint: Point): List<Point> {
        val direction = path[0]
        val distance = path.substring(1).toInt()

        return when(direction) {
            'R' -> generateListOfPoints(startingPoint, distance, 1, 0)
            'L' -> generateListOfPoints(startingPoint, distance, -1, 0)
            'U' -> generateListOfPoints(startingPoint, distance, 0, 1)
            'D' -> generateListOfPoints(startingPoint, distance, 0, -1)
            else -> throw IllegalArgumentException()
        }
    }

    private fun generateListOfPoints(startingPoint: Point, distance: Int, x: Int, y: Int): List<Point> {
        val points = mutableListOf<Point>()
        for(i in 1..distance) {
            points.add(Point(startingPoint.x + i * x, startingPoint.y + i * y))
        }

        return points;
    }
}
