package me.wedrowicz.adventofcode.day06

fun main() {
    val fileContent = {}.javaClass.classLoader.getResource("6.in")!!.readText(Charsets.UTF_8)
    val relations = fileContent.trim().split("\n")
    println(OrbitCounter(relations).calculateDistance())
}
