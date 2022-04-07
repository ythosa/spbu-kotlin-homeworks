package homeworks.homework1.task3.actions

import homeworks.homework1.task3.commandStorage.actions.PushFirstAction
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class PushFirstActionTest {
    @ParameterizedTest
    @MethodSource("getParametersApplyTest")
    fun apply(elements: MutableList<Int>, expected: MutableList<Int>) {
        pushFirstAction.apply(elements)
        Assertions.assertEquals(elements, expected)
    }

    @ParameterizedTest
    @MethodSource("getParametersCancelTest")
    fun cancel(elements: MutableList<Int>, expected: MutableList<Int>) {
        pushFirstAction.cancel(elements)
        Assertions.assertEquals(elements, expected)
    }

    companion object {
        private const val pushFirstValue = 1
        private val pushFirstAction = PushFirstAction(pushFirstValue)

        @JvmStatic
        fun getParametersApplyTest() = listOf(
            Arguments.of(mutableListOf<Int>(), mutableListOf(pushFirstValue)),
            Arguments.of(mutableListOf(1, 2, 3), mutableListOf(pushFirstValue, 1, 2, 3))
        )

        @JvmStatic
        fun getParametersCancelTest() = listOf(
            Arguments.of(mutableListOf(1), mutableListOf<Int>()),
            Arguments.of(mutableListOf(1, 2, 3, 4), mutableListOf(2, 3, 4))
        )
    }
}
