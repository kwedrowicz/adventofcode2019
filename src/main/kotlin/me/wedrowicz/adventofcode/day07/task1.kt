package me.wedrowicz.adventofcode.day07

fun main() {
    var permutations = Permutations(5)
    println(permutations.state())
    permutations.inc()
    println(permutations.state())
    permutations.isZero()
}
