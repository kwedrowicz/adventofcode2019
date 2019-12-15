package me.wedrowicz.adventofcode.day10

import java.util.*
import kotlin.math.max
import kotlin.math.pow
import kotlin.math.sqrt

class MonitoringStation(private val asteroids: List<Point>) {
    fun findBestLocation(): Int {
        var globalBest = 0
        for(asteroid in asteroids) {
            val coefficients = mutableSetOf<VisibleAsteroid>()
            for(otherAsteroid in asteroids) {
                if(asteroid != otherAsteroid) {

                    val isNegativeX = otherAsteroid.x-asteroid.x < 0
                    val coefficient = if(otherAsteroid.x-asteroid.x == 0) {
                        if(otherAsteroid.y > asteroid.y) Double.POSITIVE_INFINITY else Double.NEGATIVE_INFINITY
                    } else if(otherAsteroid.y-asteroid.y == 0) {
                        if(otherAsteroid.x > asteroid.x) Double.MAX_VALUE else Double.MIN_VALUE
                    } else {
                        (otherAsteroid.y-asteroid.y)/(otherAsteroid.x-asteroid.x).toDouble()
                    }

                    coefficients.add(VisibleAsteroid(coefficient, isNegativeX))
                }
            }

            if(coefficients.size == 256) {
                println(asteroid)
            }

            globalBest = max(globalBest, coefficients.size)
        }

        return globalBest
    }

    fun getPositionOfNthAsteroidToDestroy(laserPosition: Point, n: Int): Point {
        println(laserPosition)
        val leftHalf = mutableMapOf<Double, PriorityQueue<PointWithDistance>>()
        val rightHalf = mutableMapOf<Double, PriorityQueue<PointWithDistance>>()

        for(asteroid in asteroids) {
            if(asteroid != laserPosition) {
                val isNegativeX = asteroid.x-laserPosition.x < 0
                val coefficient = if(asteroid.x-laserPosition.x == 0) {
                    if(asteroid.y > laserPosition.y) Double.POSITIVE_INFINITY else Double.NEGATIVE_INFINITY
//                } else if(asteroid.y-laserPosition.y == 0) {
//                    if(asteroid.x > laserPosition.x) Double.MAX_VALUE else Double.MIN_VALUE
                } else {
                    (asteroid.y-laserPosition.y)/(asteroid.x-laserPosition.x).toDouble()
                }

                if(isNegativeX) {
                    leftHalf
                        .getOrPut(coefficient, { PriorityQueue<PointWithDistance> {a, b -> (a.distance - b.distance).toInt()} })
                        .add(PointWithDistance(asteroid.x, asteroid.y, asteroid.distance(laserPosition)))
                } else {
                    rightHalf
                        .getOrPut(coefficient, { PriorityQueue<PointWithDistance> {a, b -> (a.distance - b.distance).toInt()} })
                        .add(PointWithDistance(asteroid.x, asteroid.y, asteroid.distance(laserPosition)))
                }
            }
        }

        val sortedRightHalf = rightHalf.toSortedMap()
        val sortedLeftHalf = leftHalf.toSortedMap()

        println(sortedRightHalf)
        println(sortedLeftHalf)

        var asteroidDestroyed = 0
        var lastDestroy: Point
        while(asteroidDestroyed < n) {
            for(rightQueue in sortedRightHalf.values) {
                if(rightQueue.isNotEmpty()) {
                    lastDestroy = rightQueue.remove().toPoint()
                    println("Destroyed $lastDestroy")
                    asteroidDestroyed++
                    if(asteroidDestroyed == n) {
                        return lastDestroy
                    }
                }
            }

            for(leftQueue in sortedLeftHalf.values) {
                if(leftQueue.isNotEmpty()) {
                    lastDestroy = leftQueue.remove().toPoint()
                    println("Destroyed $lastDestroy")
                    asteroidDestroyed++
                    if(asteroidDestroyed == n) {
                        return lastDestroy
                    }
                }
            }
        }


        return Point(0,0)
    }
}

data class Point(val x: Int, val y: Int)

fun Point.distance(other: Point): Double {
    return sqrt((this.x-other.x).toDouble().pow(2) + (this.y-other.y).toDouble().pow(2))
}

data class PointWithDistance(val x: Int, val y: Int, val distance: Double)

fun PointWithDistance.toPoint(): Point {
    return Point(this.x, this.y)
}


data class VisibleAsteroid(val coefficient: Double, val isNegativeX: Boolean)
