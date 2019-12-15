package me.wedrowicz.adventofcode.day12

import kotlin.math.abs

class Gravity(private val initialPositions: List<Point>) {
    fun run(iterations: Int): Int {
        val planets = initialPositions.map { Planet(it, Point(0,0,0)) }

        for(i in 0 until iterations) {
            println(planets)
            step(planets)
//            for(planet in planets) {
//                for(otherPlanet in planets) {
//                    planet.velocity = planet.velocity.add(planet.position.diff(otherPlanet.position))
//                }
//            }
//
//            for(planet in planets) {
//                planet.position = planet.position.add(planet.velocity)
//            }
        }

        var res = 0
        for(planet in planets) {
            res += (planet.position.propSum() * planet.velocity.propSum())
        }

        return res
    }

    fun cycleSize(): Long {
        val planets = initialPositions.map { Planet(it, Point(0,0,0)) }

        var xCycle = -1
        var yCycle = -1
        var zCycle = -1
        var iteration = 0

        while(xCycle == -1 || yCycle == -1 || zCycle == -1) {
            step(planets)
            iteration++

            if(xCycle == -1 && planets.filterIndexed {i, p -> (p.position.x == initialPositions[i].x && p.velocity.x == 0)}.size == 4) {
                xCycle = iteration
            }
            if(yCycle == -1 && planets.filterIndexed {i, p -> (p.position.y == initialPositions[i].y && p.velocity.y == 0)}.size == 4) {
                yCycle = iteration
            }
            if(zCycle == -1 && planets.filterIndexed {i, p -> (p.position.z == initialPositions[i].z && p.velocity.z == 0)}.size == 4) {
                zCycle = iteration
            }
        }

        println("$xCycle, $yCycle, $zCycle")

        return lcm(xCycle.toLong(), lcm(yCycle.toLong(), zCycle.toLong()))
    }

    private fun step(planets: List<Planet>) {
        for(planet in planets) {
            for(otherPlanet in planets) {
                planet.velocity = planet.velocity.add(planet.position.diff(otherPlanet.position))
            }
        }

        for(planet in planets) {
            planet.position = planet.position.add(planet.velocity)
        }
    }
}

data class Planet(var position: Point, var velocity: Point)

data class Point(val x: Int, val y: Int, val z: Int)

fun Point.add(other: Point) = Point(this.x+other.x, this.y+other.y,this.z+other.z)

fun Point.diff(other: Point): Point {
    return Point(
        sign(this.x-other.x),
        sign(this.y-other.y),
        sign(this.z-other.z)
    )
}

fun Point.propSum(): Int {
    return abs(this.x)+abs(this.y)+abs(this.z)
}

fun sign(value: Int): Int {
    if (value > 0) {
        return -1
    } else if (value == 0) {
        return 0
    } else {
        return 1
    }
}

fun gcd(a: Long, b: Long): Long = if (b == 0L) a else gcd(b, a % b)
fun lcm(a: Long, b: Long): Long = a / gcd(a, b) * b
