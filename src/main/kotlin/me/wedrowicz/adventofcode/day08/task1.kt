package me.wedrowicz.adventofcode.day08

fun main() {
    val fileContent = {}.javaClass.classLoader.getResource("8.in")!!.readText(Charsets.UTF_8).trim()
    println(SpaceImageFormat(fileContent, 25, 6).findLayerWithFewestZeros())
}
