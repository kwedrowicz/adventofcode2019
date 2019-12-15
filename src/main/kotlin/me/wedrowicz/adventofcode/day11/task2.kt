package me.wedrowicz.adventofcode.day11

fun main() {
    val fileContent = {}.javaClass.classLoader.getResource("11.in")!!.readText(Charsets.UTF_8)
    val program = fileContent.trim().split(',').map { el -> el.toBigInteger() }.toMutableList()
    val robot = Robot(program)
    robot.printRegistrationIdentifier()
}
