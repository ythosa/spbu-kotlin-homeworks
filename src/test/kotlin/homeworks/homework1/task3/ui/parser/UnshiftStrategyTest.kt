package homeworks.homework1.task3.ui.parser

import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class UnshiftStrategyTest {
    private val unshiftStrategy = UnshiftStrategy()

    @ParameterizedTest
    @MethodSource("getSuccessParsingTestData")
    fun `success parsing`(arguments: List<String>) {
        assertDoesNotThrow { unshiftStrategy.parse(arguments) }
    }

    @ParameterizedTest
    @MethodSource("getInvalidArgumentsCountTestData")
    fun `invalid arguments count`(arguments: List<String>) {
        assertThrows<InvalidArgumentsException> { unshiftStrategy.parse(arguments) }
    }

    @ParameterizedTest
    @MethodSource("getInvalidArgumentsTypesTestData")
    fun `invalid arguments types`(arguments: List<String>) {
        assertThrows<InvalidArgumentsException> { unshiftStrategy.parse(arguments) }
    }

    companion object {
        @JvmStatic
        fun getSuccessParsingTestData() = listOf(
            Arguments.of(listOf("1")),
            Arguments.of(listOf("-1")),
            Arguments.of(listOf("0")),
        )

        @JvmStatic
        fun getInvalidArgumentsCountTestData() = listOf(
            Arguments.of(emptyList<String>()),
            Arguments.of(listOf("1", "2")),
        )

        @JvmStatic
        fun getInvalidArgumentsTypesTestData() = listOf(
            Arguments.of(listOf("kek")),
            Arguments.of(listOf("1_")),
            Arguments.of(listOf("1.0")),
        )
    }
}
