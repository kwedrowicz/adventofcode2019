package me.wedrowicz.adventofcode.day04

class PasswordValidation {
    companion object {
        fun isValid(password: Int): Boolean {
            val pass = password.toString()
            if(pass.length != 6) {
                return false
            }
            var double = false
            for(i in 1 until pass.length) {
                if(pass[i] < pass[i-1]) {
                    return false
                }
                if(pass[i] == pass[i-1]) {
                    double = true
                }

            }

            return double
        }

        fun isValidNotMatching(password: Int): Boolean {
            val pass = password.toString()
            if(pass.length != 6) {
                return false
            }
            val digitMap = mutableMapOf<Char, Int>()
            for(i in 1 until pass.length) {
                if(pass[i] < pass[i-1]) {
                    return false
                }
                if(pass[i] == pass[i-1]) {
                    val current = digitMap.getOrDefault(pass[i], 1)
                    digitMap[pass[i]] = current + 1
                }
            }

            for(value in digitMap.values) {
                if(value == 2) {
                    return true
                }
            }

            return false
        }
    }
}
