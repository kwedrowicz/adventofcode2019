package me.wedrowicz.adventofcode.day07

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.math.max

internal class IntCodeProgramExtended2Test {
    @Test
    fun `test 1`() {
        var program = listOf(3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0)
        val permutations = Permutations(5)
        var maxRes = Int.MIN_VALUE
        while(true) {
            var res = 0
            val state = permutations.state()
            for(i in 0..4) {
                res = IntCodeProgramExtended2(program).runWithIfAndJumps(listOf(state[i], res))
            }
            println(res)
            maxRes = max(res, maxRes)
            if(permutations.hasNext()) {
                permutations.inc()
            } else {
                break
            }
        }

        // then
        Assertions.assertEquals(43210, maxRes)
    }

    @Test
    fun `test 2`() {
        var program = listOf(3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5)
        val permutations = Permutations(5)
        var maxRes = Int.MIN_VALUE
        while(true) {
            val state = permutations.state()
            val aplifiers = (0..4).toList().map { IntCodeVM(program, listOf(state[it]+5)) }

            var finished = 0
            var output = listOf(0)
            while(finished < 5) {
                for(i in 0..4) {
                    if(!aplifiers[i].isHalted) {
                        continue
                    }
                    val pair = aplifiers[i].run(output)
                    //println(i)
                    //println(pair)
                    output = pair.first
                    if(!pair.second) {
                        finished++
                        //println(finished)
                    }
                }
            }

            // println(res)
            if(output.isNotEmpty()) {
                maxRes = max(output.max()!!, maxRes)
            }

            if(permutations.hasNext()) {
                permutations.inc()
            } else {
                break
            }
        }

        // then
        Assertions.assertEquals(139629729, maxRes)
    }

    @Test
    fun `prod`() {
        var program = listOf(3,8,1001,8,10,8,105,1,0,0,21,34,51,76,101,126,207,288,369,450,99999,3,9,102,4,9,9,1001,9,2,9,4,9,99,3,9,1001,9,2,9,1002,9,3,9,101,3,9,9,4,9,99,3,9,102,5,9,9,1001,9,2,9,102,2,9,9,101,3,9,9,1002,9,2,9,4,9,99,3,9,101,5,9,9,102,5,9,9,1001,9,2,9,102,3,9,9,1001,9,3,9,4,9,99,3,9,101,2,9,9,1002,9,5,9,1001,9,5,9,1002,9,4,9,101,5,9,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,1,9,4,9,99,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,99,3,9,101,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,1,9,4,9,99,3,9,1001,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,1,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,99,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,99)
        val permutations = Permutations(5)
        var maxRes = Int.MIN_VALUE
        while(true) {
            var res = 0
            val state = permutations.state()
            for(i in 0..4) {
                res = IntCodeProgramExtended2(program).runWithIfAndJumps(listOf(state[i], res))
            }
            // println(res)
            maxRes = max(res, maxRes)
            if(permutations.hasNext()) {
                permutations.inc()
            } else {
                break
            }
        }

        // then
        println(maxRes)
        // Assertions.assertEquals(43210, maxRes)
    }

    @Test
    fun `prod 2`() {
        var program = listOf(3,8,1001,8,10,8,105,1,0,0,21,34,51,76,101,126,207,288,369,450,99999,3,9,102,4,9,9,1001,9,2,9,4,9,99,3,9,1001,9,2,9,1002,9,3,9,101,3,9,9,4,9,99,3,9,102,5,9,9,1001,9,2,9,102,2,9,9,101,3,9,9,1002,9,2,9,4,9,99,3,9,101,5,9,9,102,5,9,9,1001,9,2,9,102,3,9,9,1001,9,3,9,4,9,99,3,9,101,2,9,9,1002,9,5,9,1001,9,5,9,1002,9,4,9,101,5,9,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,1,9,4,9,99,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,99,3,9,101,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,1,9,4,9,99,3,9,1001,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,1,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,99,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,99)
        val permutations = Permutations(5)
        var maxRes = Int.MIN_VALUE
        while(true) {
            val state = permutations.state()
            val aplifiers = (0..4).toList().map { IntCodeVM(program, listOf(state[it]+5)) }

            var finished = 0
            var output = listOf(0)
            while(finished < 5) {
                for(i in 0..4) {
                    if(!aplifiers[i].isHalted) {
                        continue
                    }
                    val pair = aplifiers[i].run(output)
                    //println(i)
                    //println(pair)
                    output = pair.first
                    if(!pair.second) {
                        finished++
                        //println(finished)
                    }
                }
            }

            // println(res)
            if(output.isNotEmpty()) {
                maxRes = max(output.max()!!, maxRes)
            }

            if(permutations.hasNext()) {
                permutations.inc()
            } else {
                break
            }
        }

        println(maxRes)
    }
}
