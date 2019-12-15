package me.wedrowicz.adventofcode.day02

class IntCodeProgram(private val program: List<Int>) {
    fun run(): List<Int> {
        val result = program.toMutableList()
        for(i in program.indices step 4) {
            when(result[i]) {
                99 -> return result
                1 -> result[result[i+3]] = result[result[i+1]] + result[result[i+2]]
                2 -> result[result[i+3]] = result[result[i+1]] * result[result[i+2]]
            }
        }

        return result
    }
}
