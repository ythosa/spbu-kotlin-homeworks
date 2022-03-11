package homeworks.homework1.task3.actions

import homeworks.homework1.task3.commandStorage.actions.UnshiftAction
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class UnshiftActionTest {
    @ParameterizedTest
    @MethodSource("getParametersApplyTest")
    fun apply(elements: MutableList<Int>, expected: MutableList<Int>) {
        unshiftAction.apply(elements)
        Assertions.assertEquals(elements, expected)
    }

    @ParameterizedTest
    @MethodSource("getParametersCancelTest")
    fun cancel(elements: MutableList<Int>, expected: MutableList<Int>) {
        unshiftAction.cancel(elements)
        Assertions.assertEquals(elements, expected)
    }

    companion object {
        private const val unshiftValue = 1
        private val unshiftAction = UnshiftAction(unshiftValue)

        @JvmStatic
        fun getParametersApplyTest() = listOf(
            Arguments.of(mutableListOf<Int>(), mutableListOf(unshiftValue)),
            Arguments.of(mutableListOf(1, 2, 3), mutableListOf(unshiftValue, 1, 2, 3))
        )

        @JvmStatic
        fun getParametersCancelTest() = listOf(
            Arguments.of(mutableListOf(1), mutableListOf<Int>()),
            Arguments.of(mutableListOf(1, 2, 3, 4), mutableListOf(2, 3, 4))
        )
    }
}
