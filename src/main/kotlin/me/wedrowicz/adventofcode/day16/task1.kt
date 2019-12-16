package me.wedrowicz.adventofcode.day16

fun main() {
    val fileContent = {}.javaClass.classLoader.getResource("16.in")!!.readText(Charsets.UTF_8)
    val input = fileContent.trim()
    println(FFT(input).next(100).substring(0,8))
    println(FFT(input).nextWithOffset(100, input.substring(0, 7).toInt()))
}
