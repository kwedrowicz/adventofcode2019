package me.wedrowicz.adventofcode.day04

fun main() {
    var counter = 0
    for(i in 256310..732737) {
        if(PasswordValidation.isValid(i)) {
            counter++
        }
    }
    println(counter)
}
