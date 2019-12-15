package me.wedrowicz.adventofcode.day06

import kotlin.math.min

class OrbitCounter {
    private val root: String
    private val points: MutableMap<String, Point> = HashMap()

    constructor(relations: List<String>) {
        val possibleRoots = HashSet<String>()

        for(relation in relations) {
            val names = relation.split(')')
            if(!points.contains(names[1])) {
                points[names[1]] = Point()
            }
            if(possibleRoots.contains(names[1])) {
                possibleRoots.remove(names[1])
            }
            if(!points.contains(names[0])) {
                points[names[0]] = Point()
                possibleRoots.add(names[0])
            }
            points[names[0]]!!.orbitedBy.add(names[1])
        }

        root = possibleRoots.first()
    }

    fun count(): Int {
        val res = 0
        return points[root]!!.orbitedBy.fold(res) { acc, e -> acc + recCount(e, 1) }
    }

    fun paths(): List<List<String>> {
        val current = listOf(root)

        return points[root]!!.orbitedBy.flatMap {
            val newPath = current.toMutableList()
            newPath.add(it)
            recPath(newPath)
        }
    }

    fun calculateDistance(): Int {
        val paths = paths()
        val santaPath = paths.filter { it.contains("SAN") }.first()
        val myPath = paths.filter { it.contains("YOU") }.first()

        println(santaPath)
        println(myPath)

        var i = 0
        while(santaPath[i] == myPath[i]) {
            i++
        }

        return santaPath.size + myPath.size - 2 * i - 2
    }

    private fun recPath(current: List<String>): List<List<String>> {
        if(points[current.last()]!!.orbitedBy.size == 0) {
            return listOf(current)
        }

        return points[current.last()]!!.orbitedBy.flatMap {
            val newPath = current.toMutableList()
            newPath.add(it)
            recPath(newPath)
        }
    }

    private fun recCount(point: String, distance: Int): Int {
        if(points[point]!!.orbitedBy.size == 0) {
            return distance
        }

        return points[point]!!.orbitedBy.fold(distance) {acc, e -> acc + recCount(e, distance+1)}
    }
}

data class Point(val orbitedBy: MutableList<String> = mutableListOf())
