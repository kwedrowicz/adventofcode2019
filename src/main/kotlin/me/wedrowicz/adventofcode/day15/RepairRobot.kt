package me.wedrowicz.adventofcode.day15

import me.wedrowicz.adventofcode.common.IntCodeVM
import java.math.BigInteger
import kotlin.math.max

class RepairRobot(private val p: List<BigInteger>) {
    private val oxygenTankMap = mutableMapOf<Point, Int>()
    private var oxygenSystemDistance: Int? = null
    private var oxygenSystemLocation: Point? = null

    private val oxygenSpreadMap = mutableSetOf<Point>()
    private var longestDistance = 0

    fun findOxygenSystemLocation(): Int {
        oxygenTankMap[Point(0,0)] = 1

        dfs(Point(0, 0), 0, p)

        return oxygenSystemDistance!!
    }

    fun getLongestDistanceFromOxygenSystem(): Int {
        dfsOnMap(oxygenSystemLocation!!, 0)

        return longestDistance
    }

    private fun dfs(currentLocation: Point, distance: Int, program: List<BigInteger>) {
//        println(currentLocation)
//        if(oxygenSystemDistance != null) {
//            return
//        }

        for(i in 1..4) {
            val newLocation = when(i) {
                1 -> Point(currentLocation.x, currentLocation.y - 1)
                2 -> Point(currentLocation.x, currentLocation.y + 1)
                3 -> Point(currentLocation.x + 1, currentLocation.y)
                4 -> Point(currentLocation.x - 1, currentLocation.y)
                else -> throw IllegalStateException()
            }

            if(!oxygenTankMap.contains(newLocation)) {
                val vm = IntCodeVM(program)
                val output = vm.execute(listOf(BigInteger.valueOf(i.toLong())))
                when(output[0].toInt()) {
                    0 -> { oxygenTankMap[newLocation] = 0 }
                    1 -> { oxygenTankMap[newLocation] = 1; dfs(newLocation, distance+1, vm.getState().toList()) }
                    2 -> { oxygenTankMap[newLocation] = 2; oxygenSystemDistance = distance + 1; oxygenSystemLocation = newLocation }
                }
            }
        }
    }

    private fun dfsOnMap(currentLocation: Point, distance: Int) {
        oxygenSpreadMap.add(currentLocation)
        longestDistance = max(longestDistance, distance)
        for(i in 1..4) {
            val newLocation = when(i) {
                1 -> Point(currentLocation.x, currentLocation.y - 1)
                2 -> Point(currentLocation.x, currentLocation.y + 1)
                3 -> Point(currentLocation.x + 1, currentLocation.y)
                4 -> Point(currentLocation.x - 1, currentLocation.y)
                else -> throw IllegalStateException()
            }

            if(oxygenTankMap.contains(newLocation) && oxygenTankMap[newLocation] == 1 && !oxygenSpreadMap.contains(newLocation)) {
                dfsOnMap(newLocation, distance + 1)
            }
        }
    }
}

data class Point(val x: Int, val y: Int)
