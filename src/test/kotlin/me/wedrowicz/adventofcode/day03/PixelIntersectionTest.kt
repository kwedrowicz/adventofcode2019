package me.wedrowicz.adventofcode.day03

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class PixelIntersectionTest {
    @Test
    fun `should return right distance`() {
        // given
        val input = "R75,D30,R83,U83,L12,D49,R71,U7,L72\n" +
                "U62,R66,U55,R34,D71,R55,D58,R83"
        val paths = input.trim().split("\n").map { path -> path.split(",") }
        val pixelIntersection = PixelIntersection(paths[0], paths[1])
        // when
        val res = pixelIntersection.getClosestIntersection()
        // then
        val expected = 159
        Assertions.assertEquals(expected, res)
    }
}
