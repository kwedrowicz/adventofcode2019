package me.wedrowicz.adventofcode.day13

import me.wedrowicz.adventofcode.common.IntCodeVM

fun main() {
    val fileContent = {}.javaClass.classLoader.getResource("13.in")!!.readText(Charsets.UTF_8)
    val program = fileContent.trim().split(',').map { el -> el.toBigInteger() }.toMutableList()
    val res = IntCodeVM(program).execute()
    println(res)
    var blocks = 0
    for(i in 2 until res.size step 3) {
        if(res[i].toInt() == 2) {
            blocks++
        }
    }
    println(blocks)
}
