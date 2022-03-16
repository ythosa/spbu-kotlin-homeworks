package homeworks.homework1.task3.actions

import homeworks.homework1.task3.commandStorage.actions.PushBackAction
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class PushBackActionTest {
    @ParameterizedTest
    @MethodSource("getParametersApplyTest")
    fun apply(elements: MutableList<Int>, expected: MutableList<Int>) {
        pushBackAction.apply(elements)
        assertEquals(elements, expected)
    }

    @ParameterizedTest
    @MethodSource("getParametersCancelTest")
    fun cancel(elements: MutableList<Int>, expected: MutableList<Int>) {
        pushBackAction.cancel(elements)
        assertEquals(elements, expected)
    }

    companion object {
        private const val pushValue = 1
        private val pushBackAction = PushBackAction(pushValue)

        @JvmStatic
        fun getParametersApplyTest() = listOf(
            Arguments.of(mutableListOf<Int>(), mutableListOf(pushValue)),
            Arguments.of(mutableListOf(1, 2, 3), mutableListOf(1, 2, 3, pushValue))
        )

        @JvmStatic
        fun getParametersCancelTest() = listOf(
            Arguments.of(mutableListOf(1), mutableListOf<Int>()),
            Arguments.of(mutableListOf(1, 2, 3, 4), mutableListOf(1, 2, 3))
        )
    }
}
