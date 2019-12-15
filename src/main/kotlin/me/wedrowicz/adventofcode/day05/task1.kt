package me.wedrowicz.adventofcode.day05

fun main() {
    val fileContent = {}.javaClass.classLoader.getResource("5.in")!!.readText(Charsets.UTF_8)
    val program = fileContent.trim().split(',').map { el -> el.toInt() }.toMutableList()
    println(IntCodeProgramExtended(program).run())
}
