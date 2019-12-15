package me.wedrowicz.adventofcode.day05

class IntCodeProgramExtended(private val program: List<Int>) {
    fun run(): List<Int> {
        val result = program.toMutableList()
        var i = 0
        while(i < program.size) {
            val opcode = result[i] % 100
            val one = result[i] / 100 % 10
            val two = result[i] / 1000 % 10
            // val three = result[i] / 10000 % 10

            when(opcode) {
                99 -> return result
                1 -> { result[result[i+3]] = (if (one == 1) result[i+1] else result[result[i+1]]) + (if (two == 1) result[i+2] else result[result[i+2]]); i += 4 }
                2 -> { result[result[i+3]] = (if (one == 1) result[i+1] else result[result[i+1]]) * (if (two == 1) result[i+2] else result[result[i+2]]); i += 4 }
                3 -> { result[result[i+1]] = 1; i+= 2 }
                4 -> { println(result[result[i+1]]); i += 2 }
                else -> { i += 4 }
            }
        }

//            when(result[i]) {
//                99 -> return result
//                1 -> { result[result[i+3]] = result[result[i+1]] + result[result[i+2]]; i+= 4 }
//                2 -> { result[result[i+3]] = result[result[i+1]] * result[result[i+2]]; i+= 4 }
//                3 -> { result[result[i+1]] = 1; i += 2 }
//                4 -> { println(result[result[i+1]]); i += 2 }
//            }
//        }
//        for(i in program.indices step 4) {
//            when(result[i]) {
//                99 -> return result
//                1 -> result[result[i+3]] = result[result[i+1]] + result[result[i+2]]
//                2 -> result[result[i+3]] = result[result[i+1]] * result[result[i+2]]
//                3 -> result[result[i+1]] = 1
//                4 -> println(result[result[i+1]])
//            }
//        }

        return result
    }

    fun runWithIfAndJumps(input: Int): Int {
        val result = program.toMutableList()
        var i = 0
        var lastToPrint = -1001
        while(i < program.size) {
            val opcode = result[i] % 100
            val one = result[i] / 100 % 10
            val two = result[i] / 1000 % 10
            // val three = result[i] / 10000 % 10

            when(opcode) {
                99 -> return lastToPrint
                1 -> { result[result[i+3]] = (if (one == 1) result[i+1] else result[result[i+1]]) + (if (two == 1) result[i+2] else result[result[i+2]]); i += 4 }
                2 -> { result[result[i+3]] = (if (one == 1) result[i+1] else result[result[i+1]]) * (if (two == 1) result[i+2] else result[result[i+2]]); i += 4 }
                3 -> { result[result[i+1]] = input; i+= 2 }
                4 -> { println(result[result[i+1]]); lastToPrint = result[result[i+1]]; i += 2 }
                5 -> { if((if(one == 1) result[i+1] else result[result[i+1]]) != 0) i = (if(two == 1) result[i+2] else result[result[i+2]]) else i += 3}
                6 -> { if((if(one == 1) result[i+1] else result[result[i+1]]) == 0) i = (if(two == 1) result[i+2] else result[result[i+2]]) else i += 3}
                7 -> { result[result[i+3]] = if ((if (one == 1) result[i+1] else result[result[i+1]]) < (if (two == 1) result[i+2] else result[result[i+2]])) 1 else 0; i += 4 }
                8 -> { result[result[i+3]] = if ((if (one == 1) result[i+1] else result[result[i+1]]) == (if (two == 1) result[i+2] else result[result[i+2]])) 1 else 0; i += 4 }
                else -> { println("DUPA$opcode"); i += 4 }
            }
        }

        return lastToPrint
    }
}
