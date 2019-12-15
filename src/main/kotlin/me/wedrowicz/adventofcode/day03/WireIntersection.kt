package me.wedrowicz.adventofcode.day03

import kotlin.math.max
import kotlin.math.min

class WireIntersection {
    private val paths1: MutableList<Line> = mutableListOf()
    private val paths2: MutableList<Line> = mutableListOf()

    public constructor(wire1: List<String>, wire2: List<String>) {
        var startingPoint = Point(0,0 )
        for(path in wire1) {
            paths1.add(convertStringPathToLine(path, startingPoint))
            startingPoint = paths1.last().end
        }

        startingPoint = Point(0,0)
        for(path in wire2) {
            paths2.add(convertStringPathToLine(path, startingPoint))
            startingPoint = paths2.last().end
        }

        println(paths1)
        println(paths2)
    }

    private fun convertStringPathToLine(path: String, startingPoint: Point): Line {
        val direction = path[0]
        val distance = path.substring(1).toInt()

        val endingPoint = when(direction) {
            'R' -> Point(startingPoint.x + distance, startingPoint.y)
            'L' -> Point(startingPoint.x - distance, startingPoint.y)
            'U' -> Point(startingPoint.x, startingPoint.y + distance)
            'D' -> Point(startingPoint.x, startingPoint.y - distance)
            else -> throw IllegalArgumentException()
        }

        return Line(startingPoint, endingPoint)
    }

    private fun vectorProduct(a: Point, b: Point, c: Point): Int {
        val x1 = c.x - a.x
        val y1 = c.y - a.y
        val x2 = b.x - a.x
        val y2 = b.y - a.y

        return x1*y2 - x2*y1
    }

    private fun check(x: Point, y: Point, z: Point): Boolean {
        return min(x.x, y.x) <= z.x && z.x <= max(x.x, y.x) && min(x.y, y.y) <= z.y && z.y <= max(x.y, y.y)
    }

    private fun isIntersected(line1: Line, line2: Line): Boolean {
        val v1 = vectorProduct(line2.start, line2.end, line1.start)
        val v2 = vectorProduct(line2.start, line2.end, line1.end)
        val v3 = vectorProduct(line1.start, line1.end, line2.start)
        val v4 = vectorProduct(line1.start, line1.end, line2.end)

        if(v1*v2 < 0 && v3*v4 < 0) return true
        if((v1>0&&v2<0||v1<0&&v2>0)&&(v3>0&&v4<0||v3<0&&v4>0)) return true
        if(v1 == 0 && check(line2.start, line2.end, line1.start)) return true
        if(v2 == 0 && check(line2.start, line2.end, line2.end)) return true
        if(v3 == 0 && check(line1.start, line1.end, line2.start)) return true
        if(v4 == 0 && check(line1.start, line1.end, line2.end)) return true

        return false
    }

//    public fun intersectingDistance(): Int {
//        for(path1 in paths1) {
//            for(path2 in paths2) {
//                if(isIntersected(path1, path2)) {
//
//                }
//            }
//        }
//    }
}

data class Point(val x: Int, val y: Int)

data class Line(val start: Point, val end: Point)
