package me.wedrowicz.adventofcode.day07

import java.util.*

class IntCodeVM(program: List<Int>, private val initialInput: List<Int>) {
    var isHalted = true
    private val tape: MutableList<Int> = program.toMutableList()
    private var i = 0
    private val inputs: Queue<Int>;

    init {
        inputs = LinkedList<Int>()
        inputs.addAll(initialInput)
    }

    fun run(newInput: List<Int>): Pair<List<Int>, Boolean> {
        inputs.addAll(newInput)
        val output = mutableListOf<Int>()

        while(i < tape.size) {
            val opcode = tape[i] % 100
            val one = tape[i] / 100 % 10
            val two = tape[i] / 1000 % 10

            when(opcode) {
                99 -> {isHalted = false; return Pair(output, false) }
                1 -> { tape[tape[i+3]] = (if (one == 1) tape[i+1] else tape[tape[i+1]]) + (if (two == 1) tape[i+2] else tape[tape[i+2]]); i += 4 }
                2 -> { tape[tape[i+3]] = (if (one == 1) tape[i+1] else tape[tape[i+1]]) * (if (two == 1) tape[i+2] else tape[tape[i+2]]); i += 4 }
                3 -> { if (inputs.size == 0) return Pair(output, true) else { tape[tape[i+1]] = inputs.poll(); i += 2 } }
                4 -> { output.add(tape[tape[i+1]]); i += 2}
                // 3 -> { tape[tape[i+1]] = if(inputFirst) { inputFirst = false; input[0] } else input[1]; i+= 2 }
                // 4 -> { /*println(tape[tape[i+1]]);*/ lastToPrint = tape[tape[i+1]]; i += 2 }
                5 -> { if((if(one == 1) tape[i+1] else tape[tape[i+1]]) != 0) i = (if(two == 1) tape[i+2] else tape[tape[i+2]]) else i += 3}
                6 -> { if((if(one == 1) tape[i+1] else tape[tape[i+1]]) == 0) i = (if(two == 1) tape[i+2] else tape[tape[i+2]]) else i += 3}
                7 -> { tape[tape[i+3]] = if ((if (one == 1) tape[i+1] else tape[tape[i+1]]) < (if (two == 1) tape[i+2] else tape[tape[i+2]])) 1 else 0; i += 4 }
                8 -> { tape[tape[i+3]] = if ((if (one == 1) tape[i+1] else tape[tape[i+1]]) == (if (two == 1) tape[i+2] else tape[tape[i+2]])) 1 else 0; i += 4 }
                else -> { throw IllegalStateException() }
            }
        }

        throw IllegalStateException()
    }
}
