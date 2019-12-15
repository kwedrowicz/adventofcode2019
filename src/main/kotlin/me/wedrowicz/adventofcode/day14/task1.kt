package me.wedrowicz.adventofcode.day14

fun main() {
    val reactions = parse("14.in")
    println(NanoFactory(reactions).calculateFuelUnitCost())
    println(NanoFactory(reactions).calculateFuelForOneUnits(1000000000000))
}
