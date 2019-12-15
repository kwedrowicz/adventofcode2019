package me.wedrowicz.adventofcode.day02

fun main() {
    val fileContent = {}.javaClass.classLoader.getResource("2.in")!!.readText(Charsets.UTF_8)
    val program = fileContent.trim().split(',').map { el -> el.toInt() }

    for(x in 0..99) {
        for(y in 0..99) {
            val initial = program.toMutableList()
            initial[1] = x
            initial[2] = y
            val result = IntCodeProgram(initial).run()
            if(result[0] == 19690720) {
                println(100 * x + y)
                return
            }
        }
    }
}
