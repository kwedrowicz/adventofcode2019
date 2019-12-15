package me.wedrowicz.adventofcode.common

import java.math.BigInteger
import java.util.*

enum class OpCode(val code: Int) {
    EXIT(99),
    ADD(1),
    MULTIPLY(2),
    INPUT(3),
    OUTPUT(4),
    JUMP_IF_TRUE(5),
    JUMP_IF_FALSE(6),
    LESS_THAN(7),
    EQUALS(8),
    OFFSET(9);

    companion object {
        private val map = values().associateBy(OpCode::code)
        fun fromCode(code: Int) = map[code]
    }
}

class IntCodeVM(program: List<BigInteger>, inputParams: List<BigInteger> = emptyList()) {
    private val state = program.toMutableList()
    private val input: Queue<BigInteger>
    private var cursor = 0
    private var offset = 0
    private var halted = true
    private val initialSize = program.size

    init {
        state.addAll(MutableList<BigInteger>(1000000) { BigInteger.ZERO })
        input = LinkedList<BigInteger>()
        input.addAll(inputParams)
    }

    fun execute(newInputs: List<BigInteger> = emptyList()): List<BigInteger> {
        halted = false
        input.addAll(newInputs)
        val output = mutableListOf<BigInteger>()
        while(true) {
//            println(cursor)
//            state[cursor].divide(BigInteger.valueOf(100)).mod(BigInteger.valueOf(10)).toInt()
//            val firstMode = (state[cursor] / 100 % 10).toInt()
//            val secondMode = (state[cursor] / 1000 % 10).toInt()
//            val thirdMode = (state[cursor] / 10000 % 10).toInt()

            val firstMode = state[cursor].divide(BigInteger.valueOf(100)).mod(BigInteger.valueOf(10)).toInt()
            val secondMode = state[cursor].divide(BigInteger.valueOf(1000)).mod(BigInteger.valueOf(10)).toInt()
            val thirdMode = state[cursor].divide(BigInteger.valueOf(10000)).mod(BigInteger.valueOf(10)).toInt()

//            when(OpCode.fromCode((state[cursor] % 100).toInt())) {
//                OpCode.EXIT -> return output
//                OpCode.ADD -> add(firstMode, secondMode, thirdMode)
//                OpCode.MULTIPLY -> multiply(firstMode, secondMode, thirdMode)
//                OpCode.INPUT -> { input(firstMode); if(halted) return output }
//                OpCode.OUTPUT -> output(firstMode, output)
//                OpCode.JUMP_IF_TRUE -> jumpIfTrue(firstMode, secondMode)
//                OpCode.JUMP_IF_FALSE -> jumpIfFalse(firstMode, secondMode)
//                OpCode.LESS_THAN -> lessThan(firstMode, secondMode, thirdMode)
//                OpCode.EQUALS -> equalsOp(firstMode, secondMode, thirdMode)
//                OpCode.OFFSET -> offsetOp(firstMode)
//                else -> throw IllegalStateException()
//            }

            when(OpCode.fromCode((state[cursor].mod(BigInteger.valueOf(100))).toInt())) {
                OpCode.EXIT -> return output
                OpCode.ADD -> add(firstMode, secondMode, thirdMode)
                OpCode.MULTIPLY -> multiply(firstMode, secondMode, thirdMode)
                OpCode.INPUT -> { input(firstMode); if(halted) return output }
                OpCode.OUTPUT -> output(firstMode, output)
                OpCode.JUMP_IF_TRUE -> jumpIfTrue(firstMode, secondMode)
                OpCode.JUMP_IF_FALSE -> jumpIfFalse(firstMode, secondMode)
                OpCode.LESS_THAN -> lessThan(firstMode, secondMode, thirdMode)
                OpCode.EQUALS -> equalsOp(firstMode, secondMode, thirdMode)
                OpCode.OFFSET -> offsetOp(firstMode)
                else -> throw IllegalStateException()
            }
        }
    }

    fun getState(): List<BigInteger> {
        return state.toList().subList(0, initialSize)
    }

    private fun add(firstMode: Int, secondMode: Int, thirdMode: Int) {
        state[getPosition(cursor+3, thirdMode)] = getValue(cursor+1, firstMode) + getValue(cursor+2, secondMode)
        // state[state[cursor+3].toInt()] = getValue(cursor+1, firstMode) + getValue(cursor+2, secondMode)
        cursor += 4
    }

    private fun multiply(firstMode: Int, secondMode: Int, thirdMode: Int) {
        state[getPosition(cursor+3, thirdMode)] = getValue(cursor+1, firstMode) * getValue(cursor+2, secondMode)
        //state[state[cursor+3].toInt()] = getValue(cursor+1, firstMode) * getValue(cursor+2, secondMode)
        cursor +=4
    }

    private fun input(mode: Int) {
        if(input.isNotEmpty()) {
            //state[state[cursor+1].toInt()] = input.poll()
            state[getPosition(cursor+1, mode)] = input.poll()
            cursor += 2
        } else {
            halted = true
        }

    }

    private fun output(mode: Int, output: MutableList<BigInteger>) {
        output.add(getValue(cursor+1, mode))
        cursor += 2
    }

    private fun jumpIfTrue(firstMode: Int, secondMode: Int) {
        //if(getValue(cursor+1, firstMode) != 0L) cursor = state[cursor+2].toInt() else cursor += 3
        if(getValue(cursor+1, firstMode) != BigInteger.ZERO) cursor = state[getPosition(cursor+2, secondMode)].toInt() else cursor += 3

    }

    private fun jumpIfFalse(firstMode: Int, secondMode: Int) {
        //if(getValue(cursor+1, firstMode) == 0L) cursor = state[cursor+2].toInt() else cursor += 3
        if(getValue(cursor+1, firstMode) == BigInteger.ZERO) cursor = state[getPosition(cursor+2, secondMode)].toInt() else cursor += 3

    }

    private fun lessThan(firstMode: Int, secondMode: Int, thirdMode: Int) {
        state[getPosition(cursor+3, thirdMode)] = if(getValue(cursor+1, firstMode) < getValue(cursor + 2, secondMode)) {
            //state[state[cursor+3].toInt()] = 1
            BigInteger.ONE
        } else {
            BigInteger.ZERO
        }
        cursor += 4
    }

    private fun equalsOp(firstMode: Int, secondMode: Int, thirdMode: Int) {
        state[getPosition(cursor+3, thirdMode)] = if(getValue(cursor+1, firstMode) == getValue(cursor + 2, secondMode)) {
            //state[state[cursor+3].toInt()] = 1
            BigInteger.ONE
        }
        else {
            BigInteger.ZERO
        }
        cursor += 4
    }

    private fun offsetOp(firstMode: Int) {
        offset += getValue(cursor+1, firstMode).toInt()
        cursor += 2
    }

    private fun getValue(cursor: Int, mode: Int): BigInteger {
        return state[getPosition(cursor, mode)]
//        return when(mode) {
//            0 -> state[state[cursor].toInt()]
//            1 -> state[cursor]
//            2 -> state[state[cursor].toInt()+offset]
//            else -> throw IllegalStateException()
//        }
    }

    private fun getPosition(cursor: Int, mode: Int): Int {
        return when(mode) {
            0 -> state[cursor].toInt()
            1 -> cursor
            2 -> state[cursor].toInt()+offset
            else -> throw IllegalStateException()
        }
    }

    fun isHalted(): Boolean {
        return halted
    }
}
