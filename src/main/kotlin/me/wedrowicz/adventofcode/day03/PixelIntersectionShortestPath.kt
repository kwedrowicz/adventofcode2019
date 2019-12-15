package me.wedrowicz.adventofcode.day03

import kotlin.math.max
import kotlin.math.min

class PixelIntersectionShortestPath {
    private val points1: MutableList<Point> = mutableListOf()
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

    fun calculateShortestPathToIntersection(): Int {
        var res = Int.MAX_VALUE
        val maxPointsSize = max(points1.size, points2.size)
        val map = mutableMapOf<Point, MarkedStep>()
        for(i in 0..maxPointsSize) {
            if(points1.size > i) {
                if(!map.contains(points1[i])) {
                    map[points1[i]] = MarkedStep(i+1, 1)
                } else if(map[points1[i]]!!.which == 2) {
                    res = min(res, i+1+map[points1[i]]!!.distance)
                }
            }
            if(points2.size > i) {
                if(!map.contains(points2[i])) {
                    map[points2[i]] = MarkedStep(i+1, 2)
                } else if(map[points2[i]]!!.which == 1) {
                    res = min(res, i+1+map[points2[i]]!!.distance)
                }
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

        return points
    }
}

data class MarkedStep(
    val distance: Int,
    val which: Int
)
