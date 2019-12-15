package me.wedrowicz.adventofcode.day13

import me.wedrowicz.adventofcode.common.IntCodeVM
import java.math.BigInteger
import java.math.BigInteger.ONE
import java.math.BigInteger.ZERO
import kotlin.math.max

fun main() {
    val fileContent = {}.javaClass.classLoader.getResource("13.in")!!.readText(Charsets.UTF_8)
    val program = fileContent.trim().split(',').map { el -> el.toBigInteger() }.toMutableList().also { it[0] = BigInteger.TWO }
    val vm = IntCodeVM(program)
    var res = vm.execute()
    var ballX = ZERO
    var paddleX = ZERO
    var score = ZERO

    while(true) {
        for(i in res.indices step 3) {
            if(res[i+2] == BigInteger.valueOf(4)) {
                ballX = res[i]
            }
            if(res[i+2] == BigInteger.valueOf(3)) {
                paddleX = res[i]
            }
            if(res[i] == BigInteger.valueOf(-1) && res[i+1] == ZERO) {
                println("SCORE: ${res[i+2]}")
                score = res[i+2]
                // return
            }
        }

        if(score == BigInteger.valueOf(12856)) {
            println(res)

            println("DUPA")
        }
        // println("$ballX, $paddleX")

        val direction = when {
            paddleX < ballX -> {
                ONE
            }
            paddleX == ballX -> {
                ZERO
            }
            else -> {
                BigInteger.valueOf(-1)
            }
        }

        res = vm.execute(listOf(direction))
    }


}

data class Point(val x: BigInteger, val y: BigInteger)
