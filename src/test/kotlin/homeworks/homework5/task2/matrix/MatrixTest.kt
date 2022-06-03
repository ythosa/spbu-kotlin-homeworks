package homeworks.homework5.task2.matrix

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Named
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class MatrixTest {
    @Test
    fun `incorrect matrix data`() {
        assertThrows<InvalidMatrixDataException> { Matrix(List(10) { rowIndex -> List(rowIndex) { it } }) }
    }

    @Test
    fun `valid matrix data`() {
        val rowsCount = 5
        val columnsCount = 10

        assertDoesNotThrow { Matrix(List(rowsCount) { List(columnsCount) { it } }) }
    }

    @ParameterizedTest(name = "Multiplication case: {0}")
    @MethodSource("getTimesTestData")
    fun times(first: Matrix, second: Matrix, expected: Matrix) {
        assertEquals(expected, first * second)
    }

    @ParameterizedTest
    @MethodSource("getInvalidMatrixSizesToTimesTestData")
    fun `invalid matrix sizes to times`(first: Matrix, second: Matrix) {
        assertThrows<InvalidMatrixSizesException> { first * second }
    }

    companion object {
        @JvmStatic
        fun getTimesTestData() = listOf(
            Arguments.of(
                Named.of(
                    "square matrix",
                    Matrix(
                        listOf(3, 8, 3), listOf(3, 9, 9), listOf(1, 5, 5),
                    )
                ),
                Matrix(
                    listOf(1, 3, 6), listOf(2, 6, 7), listOf(5, 1, 8),
                ),
                Matrix(
                    listOf(34, 60, 98), listOf(66, 72, 153), listOf(36, 38, 81)
                )
            ),
            Arguments.of(
                Named.of(
                    "tall matrix",
                    Matrix(
                        listOf(7, 4, 6),
                        listOf(1, 6, 5),
                        listOf(6, 5, 5),
                        listOf(8, 5, 8),
                        listOf(2, 4, 9),
                    )
                ),
                Matrix(
                    listOf(4), listOf(4), listOf(6),
                ),
                Matrix(
                    listOf(80), listOf(58), listOf(74), listOf(100), listOf(78)
                )
            ),
            Arguments.of(
                Named.of(
                    "wide matrix",
                    Matrix(
                        listOf(5, 1, 1, 6, 4, 8, 5),
                        listOf(9, 8, 8, 7, 1, 8, 4),
                        listOf(4, 1, 3, 9, 2, 7, 9),
                    )
                ),
                Matrix(
                    listOf(9, 3, 7, 7),
                    listOf(7, 4, 8, 9),
                    listOf(3, 8, 7, 8),
                    listOf(9, 3, 4, 5),
                    listOf(9, 7, 2, 6),
                    listOf(6, 9, 8, 4),
                    listOf(1, 9, 4, 5),
                ),
                Matrix(
                    listOf(198, 190, 166, 163), listOf(285, 259, 293, 292), listOf(202, 225, 189, 191)
                )
            )
        )

        @JvmStatic
        fun getInvalidMatrixSizesToTimesTestData() = listOf(
            Arguments.of(
                generateMatrixWithSizes(1, 1),
                generateMatrixWithSizes(2, 2)
            ),
            Arguments.of(
                generateMatrixWithSizes(3, 2),
                generateMatrixWithSizes(3, 2)
            ),
        )
    }
}
