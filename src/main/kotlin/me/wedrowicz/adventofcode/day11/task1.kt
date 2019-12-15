package me.wedrowicz.adventofcode.day11

import java.math.BigInteger

fun main() {
    val fileContent = {}.javaClass.classLoader.getResource("11.in")!!.readText(Charsets.UTF_8)
    val program = fileContent.trim().split(',').map { el -> el.toBigInteger() }.toMutableList()
    val robot = Robot(program)
    println(robot.run(BigInteger.ZERO).size)
}
