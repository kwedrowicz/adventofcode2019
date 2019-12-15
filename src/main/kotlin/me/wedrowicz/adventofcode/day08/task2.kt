package me.wedrowicz.adventofcode.day08

fun main() {
    val fileContent = {}.javaClass.classLoader.getResource("8.in")!!.readText(Charsets.UTF_8).trim()
    val mergedLayer = SpaceImageFormat(fileContent, 25, 6).mergeLayers().map {
        if(it == 1) {
            'X'
        } else {
            ' '
        }
    }.joinToString("")
    for(i in 0..5) {
        println(mergedLayer.substring(i*25, i*25+25))
    }
}
