package me.wedrowicz.adventofcode.day10

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class MonitoringStationTest {
    @ParameterizedTest
    @MethodSource("mapProvider")
    fun `test 1`(fileName: String, expectedResult: Int) {
        // given
        val fileContent = {}.javaClass.classLoader.getResource(fileName)!!.readText(Charsets.UTF_8).trim()
        val map = fileContent.split("\n").map { it.trim().split("") }
        val asteroids = mutableListOf<Point>()
        for((y, line) in map.withIndex()) {
            for((x, cell) in line.withIndex()) {
                if(cell == "#") {
                    asteroids.add(Point(x-1,y))
                }
            }
        }
        //when
        val res = MonitoringStation(asteroids).findBestLocation()
        // then
        assertEquals(expectedResult, res)
    }

    @Test
    fun `test 2`() {
        // given
        val fileContent = {}.javaClass.classLoader.getResource("10_2_1.in")!!.readText(Charsets.UTF_8).trim()
        val map = fileContent.split("\n").map { it.trim().split("") }
        val asteroids = mutableListOf<Point>()
        for((y, line) in map.withIndex()) {
            for((x, cell) in line.withIndex()) {
                if(cell == "#") {
                    asteroids.add(Point(x-1,y))
                }
            }
        }
        //when
        val res1 = MonitoringStation(asteroids).getPositionOfNthAsteroidToDestroy(Point(8,3), 9)
        // then
        assertEquals(Point(15,1), res1)

        //when
        val res2 = MonitoringStation(asteroids).getPositionOfNthAsteroidToDestroy(Point(8,3), 18)
        // then
        assertEquals(Point(4,4), res2)

    }

    companion object {
        @JvmStatic
        fun mapProvider() = listOf(
            Arguments.of("10_1.in", 8),
            Arguments.of("10_2.in", 33),
            Arguments.of("10_3.in", 35),
            Arguments.of("10_4.in", 41),
            Arguments.of("10_5.in", 210)
        )
    }
}
