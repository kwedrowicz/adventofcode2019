package me.wedrowicz.adventofcode.day15

fun main() {
    val fileContent = {}.javaClass.classLoader.getResource("15.in")!!.readText(Charsets.UTF_8)
    val program = fileContent.trim().split(',').map { el -> el.toBigInteger() }.toMutableList()
    val robot = RepairRobot(program)
    println(robot.findOxygenSystemLocation())
    println(robot.getLongestDistanceFromOxygenSystem())
}
