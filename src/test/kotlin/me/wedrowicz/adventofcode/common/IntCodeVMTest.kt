//package me.wedrowicz.adventofcode.common
//
//import org.junit.jupiter.api.Assertions
//import org.junit.jupiter.api.Assertions.*
//import org.junit.jupiter.api.Test
//
//internal class IntCodeVMTest {
//
//    @Test
//    fun `should finish execution when 99 opcode found`() {
//        // given
//        val program = listOf(99).map { it.toLong() }
//        val vm = IntCodeVM(program)
//        // when, then
//        Assertions.assertDoesNotThrow { vm.execute() }
//    }
//
//    @Test
//    fun `should support add operation`() {
//        // given
//        val program = listOf(1,5,6,7,99,10,20,0).map { it.toLong() }
//        val vm = IntCodeVM(program)
//        // when
//        vm.execute()
//        // then
//        val expectedState = listOf(1,5,6,7,99,10,20,30).map { it.toLong() }
//        assertEquals(expectedState, vm.getState())
//    }
//
//    @Test
//    fun `should support multiply operation`() {
//        // given
//        val program = listOf(2,5,6,7,99,10,20,0).map { it.toLong() }
//        val vm = IntCodeVM(program)
//        // when
//        vm.execute()
//        // then
//        val expectedState = listOf(2,5,6,7,99,10,20,200).map { it.toLong() }
//        assertEquals(expectedState, vm.getState())
//    }
//
//    @Test
//    fun `should support input operation`() {
//        // given
//        val program = listOf(3,3,99,0).map { it.toLong() }
//        val vm = IntCodeVM(program, listOf(10))
//        // when
//        vm.execute()
//        // then
//        val expectedState = listOf(3,3,99,10).map { it.toLong() }
//        assertEquals(expectedState, vm.getState())
//    }
//
//    @Test
//    fun `should support output operation`() {
//        // given
//        val program = listOf(4,3,99,10).map { it.toLong() }
//        val vm = IntCodeVM(program)
//        // when
//        val output = vm.execute()
//        // then
//        val expectedOutput = listOf(10).map { it.toLong() }
//        assertEquals(expectedOutput, output)
//    }
//
//    @Test
//    fun `should support value mode`() {
//        // given
//        val program = listOf(1101,2,3,5,99,0).map { it.toLong() }
//        val vm = IntCodeVM(program)
//        // when
//        vm.execute()
//        // then
//        val expectedState = listOf(1101,2,3,5,99,5).map { it.toLong() }
//        assertEquals(expectedState, vm.getState())
//    }
//
//    @Test
//    fun `should support jump-if-true operation`() {
//        // given
//        val program = listOf(105,1,4,99,1101,2,3,9,99,0).map { it.toLong() }
//        val vm = IntCodeVM(program)
//        // when
//        vm.execute()
//        // then
//        val expectedState = listOf(105,1,4,99,1101,2,3,9,99,5).map { it.toLong() }
//        assertEquals(expectedState, vm.getState())
//    }
//
//    @Test
//    fun `should support jump-if-false operation`() {
//        // given
//        val program = listOf(106,0,4,99,1101,2,3,9,99,0).map { it.toLong() }
//        val vm = IntCodeVM(program)
//        // when
//        vm.execute()
//        // then
//        val expectedState = listOf(106,0,4,99,1101,2,3,9,99,5).map { it.toLong() }
//        assertEquals(expectedState, vm.getState())
//    }
//
//    @Test
//    fun `should support less than operation`() {
//        // given
//        val program = listOf(1107,1,2,5,99,0).map { it.toLong() }
//        val vm = IntCodeVM(program)
//        // when
//        vm.execute()
//        // then
//        val expectedState = listOf(1107,1,2,5,99,1).map { it.toLong() }
//        assertEquals(expectedState, vm.getState())
//    }
//
//    @Test
//    fun `should support equals operation`() {
//        // given
//        val program = listOf(1108,1,1,5,99,0).map { it.toLong() }
//        val vm = IntCodeVM(program)
//        // when
//        vm.execute()
//        // then
//        val expectedState = listOf(1108,1,1,5,99,1).map { it.toLong() }
//        assertEquals(expectedState, vm.getState())
//    }
//
//    @Test
//    fun `should be halted when lack of input params and resumed afterwards`() {
//        // given
//        val program = listOf(3,3,99,0).map { it.toLong() }
//        val vm = IntCodeVM(program)
//        assertTrue(vm.isHalted())
//        // when
//        vm.execute()
//        assertTrue(vm.isHalted())
//        vm.execute(listOf(10))
//        // then
//        assertFalse(vm.isHalted())
//        val expectedState = listOf(3,3,99,10).map { it.toLong() }
//        assertEquals(expectedState, vm.getState())
//    }
//
//    @Test
//    fun `should restart execution from the place where was halted`() {
//        // given
//        val program = listOf(104,5,3,1,99).map { it.toLong() }
//        val vm = IntCodeVM(program)
//        // when
//        vm.execute()
//        val output = vm.execute(listOf(10))
//        // then
//        val expectedOutput = emptyList<Int>()
//        assertEquals(expectedOutput, output)
//    }
//}
