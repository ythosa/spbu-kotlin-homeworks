package homeworks.homework1.task3.actions

import homeworks.homework1.task3.commandStorage.actions.UnshiftAction
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.LinkedList

internal class UnshiftActionTest {
    @ParameterizedTest
    @MethodSource("getParametersApplyTest")
    fun apply(elements: LinkedList<Int>, expected: LinkedList<Int>) {
        unshiftAction.apply(elements)
        Assertions.assertEquals(elements, expected)
    }

    @ParameterizedTest
    @MethodSource("getParametersCancelTest")
    fun cancel(elements: LinkedList<Int>, expected: LinkedList<Int>) {
        unshiftAction.cancel(elements)
        Assertions.assertEquals(elements, expected)
    }

    companion object {
        private const val unshiftValue = 1
        private val unshiftAction = UnshiftAction(unshiftValue)

        @JvmStatic
        fun getParametersApplyTest() = listOf(
            Arguments.of(LinkedList<Int>(), LinkedList(listOf(unshiftValue))),
            Arguments.of(LinkedList(listOf(1, 2, 3)), LinkedList(listOf(unshiftValue, 1, 2, 3)))
        )

        @JvmStatic
        fun getParametersCancelTest() = listOf(
            Arguments.of(LinkedList(listOf(1)), LinkedList<Int>()),
            Arguments.of(LinkedList(listOf(1, 2, 3, 4)), LinkedList(listOf(2, 3, 4)))
        )
    }
}
