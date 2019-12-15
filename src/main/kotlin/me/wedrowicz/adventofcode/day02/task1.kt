package me.wedrowicz.adventofcode.day02

fun main() {
    val fileContent = {}.javaClass.classLoader.getResource("2.in")!!.readText(Charsets.UTF_8)
    val program = fileContent.trim().split(',').map { el -> el.toInt() }.toMutableList()
    program[1] = 12
    program[2] = 2
    println(IntCodeProgram(program).run()[0])
}
