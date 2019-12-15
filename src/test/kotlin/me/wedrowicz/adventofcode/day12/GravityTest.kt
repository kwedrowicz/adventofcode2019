package me.wedrowicz.adventofcode.day12

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class GravityTest {
    @Test
    fun `test 1`() {
        // given
        val initialPositions = listOf(
            Point(-1,0,2),
            Point(2, -10, -7),
            Point(4,-8,8),
            Point(3,5,-1)
        )

        // when
        val res = Gravity(initialPositions).run(10)

        // then
        assertEquals(179, res)
    }

    @Test
    fun `test 2`() {
        // given
        val initialPositions = listOf(
            Point(-8,-10,0),
            Point(5,  5, 10),
            Point(2,-7,3),
            Point(9,-8,-3)
        )

        // when
        val res = Gravity(initialPositions).run(100)

        // then
        assertEquals(1940, res)
    }

    @Test
    fun `task 2 - 1`() {
        // given
        val initialPositions = listOf(
            Point(-1,0,2),
            Point(2, -10, -7),
            Point(4,-8,8),
            Point(3,5,-1)
        )

        // when
        val res = Gravity(initialPositions).cycleSize()

        // then
        assertEquals(2772, res)
    }

    @Test
    fun `task 2 - 2`() {
        // given
        val initialPositions = listOf(
            Point(-8,-10,0),
            Point(5, 5, 10),
            Point(2,-7,3),
            Point(9,-8,-3)
        )

        // when
        val res = Gravity(initialPositions).cycleSize()

        // then
        assertEquals(4686774924, res)
    }
}
