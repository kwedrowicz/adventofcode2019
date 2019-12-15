package me.wedrowicz.adventofcode.day09

import me.wedrowicz.adventofcode.common.IntCodeVM
import java.math.BigInteger

fun main() {
    val fileContent = {}.javaClass.classLoader.getResource("9.in")!!.readText(Charsets.UTF_8)
    val program = fileContent.trim().split(',').map { el -> el.toBigInteger() }.toMutableList()
    val vm = IntCodeVM(program, listOf(BigInteger.ONE))
    println(vm.execute())
}
