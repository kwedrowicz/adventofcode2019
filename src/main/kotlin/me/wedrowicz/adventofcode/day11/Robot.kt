package me.wedrowicz.adventofcode.day11

import me.wedrowicz.adventofcode.common.IntCodeVM
import java.math.BigInteger
import kotlin.math.max
import kotlin.math.min

class Robot(program: List<BigInteger>) {
    private val vm: IntCodeVM = IntCodeVM(program)

    fun run(start: BigInteger): Map<Point, Int> {
        val map = mutableMapOf<Point, Int>()

        var currentCellColor: Int? = start.toInt()
        var currentPoint = Point(0,0)
        var direction = Direction(-1, 0)
        while (currentCellColor != null) {
            val output = vm.execute(listOf(currentCellColor.toBigInteger()))
            if(output.isNotEmpty()) {
                val (color, turn) = output
                map.put(currentPoint, color.toInt())

                direction = when {
                    direction == Direction(-1, 0) &&  turn.toInt() == 0 -> Direction(0, -1)
                    direction == Direction(0, -1) && turn.toInt() == 0 -> Direction(1,0)
                    direction == Direction(1,0) && turn.toInt() == 0 -> Direction(0,1)
                    direction == Direction(0,1) && turn.toInt() == 0 -> Direction(-1, 0)

                    direction == Direction(-1, 0) &&  turn.toInt() == 1 -> Direction(0, 1)
                    direction == Direction(0, -1) && turn.toInt() == 1 -> Direction(-1,0)
                    direction == Direction(1,0) && turn.toInt() == 1 -> Direction(0,-1)
                    direction == Direction(0,1) && turn.toInt() == 1 -> Direction(1, 0)
                    else -> throw IllegalStateException()
                }

                currentPoint = Point(currentPoint.x+direction.x, currentPoint.y+direction.y)
                val colored = map.get(currentPoint)
                currentCellColor = colored ?: 0

            } else {
                currentCellColor = null
            }
        }

        return map
    }

    fun printRegistrationIdentifier() {
        val map = run(BigInteger.ONE)

        var minX = Int.MAX_VALUE
        var minY = Int.MAX_VALUE
        var maxX = Int.MIN_VALUE
        var maxY = Int.MIN_VALUE

        for(panel in map.keys) {
            minX = min(minX, panel.x)
            minY = min(minY, panel.y)
            maxX = max(maxX, panel.x)
            maxY = max(maxY, panel.y)
        }

        for(y in minY..(maxY+1)) {
            for(x in minX..(maxX+1)) {
                val color = map.getOrDefault(Point(x,y), 0)
                if(color == 0) {
                    print(' ')
                } else {
                    print('X')
                }
            }
            println()
        }
    }
}

data class Point(val x: Int, val y: Int)

data class Direction(val x: Int, val y: Int)
