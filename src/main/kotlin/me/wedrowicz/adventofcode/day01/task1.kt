package me.wedrowicz.adventofcode.day01

fun main() {
    val calculator = FuelCalculator()
    val fileContent = calculator.javaClass.classLoader.getResource("1.in")!!.readText(Charsets.UTF_8)
    val moduleMasses = fileContent.split("\n").filter { el -> el != "" }.map { el -> el.toInt() }
    println(calculator.calculateForAllModules(moduleMasses))
}
