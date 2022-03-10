package homeworks.homework1.task3.actions

import homeworks.homework1.task3.commandStorage.actions.PushAction
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.LinkedList

internal class PushActionTest {
    @ParameterizedTest
    @MethodSource("getParametersApplyTest")
    fun apply(elements: LinkedList<Int>, expected: LinkedList<Int>) {
        pushAction.apply(elements)
        assertEquals(elements, expected)
    }

    @ParameterizedTest
    @MethodSource("getParametersCancelTest")
    fun cancel(elements: LinkedList<Int>, expected: LinkedList<Int>) {
        pushAction.cancel(elements)
        assertEquals(elements, expected)
    }

    companion object {
        private const val pushValue = 1
        private val pushAction = PushAction(pushValue)

        @JvmStatic
        fun getParametersApplyTest() = listOf(
            Arguments.of(LinkedList<Int>(), LinkedList(listOf(pushValue))),
            Arguments.of(LinkedList(listOf(1, 2, 3)), LinkedList(listOf(1, 2, 3, pushValue)))
        )

        @JvmStatic
        fun getParametersCancelTest() = listOf(
            Arguments.of(LinkedList(listOf(1)), LinkedList<Int>()),
            Arguments.of(LinkedList(listOf(1, 2, 3, 4)), LinkedList(listOf(1, 2, 3)))
        )
    }
}
