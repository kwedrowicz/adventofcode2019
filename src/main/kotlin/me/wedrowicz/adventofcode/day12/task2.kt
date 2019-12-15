package me.wedrowicz.adventofcode.day12

fun main() {
    // given
    val initialPositions = listOf(
        Point(7,10,17),
        Point(-2,7,0),
        Point(12,5,12),
        Point(5,-8,6)
    )

    // when
    println(Gravity(initialPositions).cycleSize())
}
