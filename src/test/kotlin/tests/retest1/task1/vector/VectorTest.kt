package tests.retest1.task1.vector

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import tests.retest1.task1.arithmetic.ArithmeticAvailable
import tests.retest1.task1.arithmetic.IntArithmetic
import tests.retest1.task1.arithmetic.toArithmetic

internal class VectorTest {
    @Test
    fun `invalid vector dimension`() {
        assertThrows<InvalidVectorSizeException> { Vector<IntArithmetic>() }
    }

    @ParameterizedTest
    @MethodSource("getPlusTestData")
    fun <T : ArithmeticAvailable<T>> plus(first: Vector<T>, second: Vector<T>, expected: Vector<T>) {
        assertTrue(first + second == expected)
    }

    @ParameterizedTest
    @MethodSource("getPlusInvalidVectorSizesTestData")
    fun <T : ArithmeticAvailable<T>> `invalid vector sizes for plus`(first: Vector<T>, second: Vector<T>) {
        assertThrows<InvalidVectorSizesException> { first + second }
    }

    @ParameterizedTest
    @MethodSource("getMinusTestData")
    fun <T : ArithmeticAvailable<T>> minus(first: Vector<T>, second: Vector<T>, expected: Vector<T>) {
        assertTrue(first - second == expected)
    }

    @ParameterizedTest
    @MethodSource("getMinusInvalidVectorSizesTestData")
    fun <T : ArithmeticAvailable<T>> `invalid vector sizes for minus`(first: Vector<T>, second: Vector<T>) {
        assertThrows<InvalidVectorSizesException> { first + second }
    }

    @ParameterizedTest
    @MethodSource("getTimesTestData")
    fun <T : ArithmeticAvailable<T>> times(first: Vector<T>, second: Vector<T>, expected: T) {
        assertTrue(first * second == expected)
    }

    @ParameterizedTest
    @MethodSource("getTimesInvalidVectorSizesTestData")
    fun <T : ArithmeticAvailable<T>> `invalid vector sizes for times`(first: Vector<T>, second: Vector<T>) {
        assertThrows<InvalidVectorSizesException> { first + second }
    }

    @ParameterizedTest
    @MethodSource("getIsNullTestData")
    fun <T : ArithmeticAvailable<T>> isNull(vector: Vector<T>, expected: Boolean) {
        assertEquals(vector.isNull(), expected)
    }

    companion object {
        @JvmStatic
        fun getPlusTestData() = listOf(
            Arguments.of(
                Vector((1).toArithmetic()),
                Vector((2).toArithmetic()),
                Vector((3).toArithmetic())
            ),
            Arguments.of(
                Vector(List(3) { (it).toArithmetic() }),
                Vector(List(3) { (it).toArithmetic() }),
                Vector(List(3) { (it * 2).toArithmetic() })
            ),
        )

        @JvmStatic
        fun getPlusInvalidVectorSizesTestData() = listOf(
            Arguments.of(
                Vector(List(1) { (it).toArithmetic() }),
                Vector(List(2) { (it).toArithmetic() }),
            ),
            Arguments.of(
                Vector(List(2) { (it).toArithmetic() }),
                Vector(List(1) { (it).toArithmetic() }),
            ),
        )

        @JvmStatic
        fun getMinusTestData() = listOf(
            Arguments.of(
                Vector((1).toArithmetic()),
                Vector((2).toArithmetic()),
                Vector((-1).toArithmetic())
            ),
            Arguments.of(
                Vector(List(3) { (it).toArithmetic() }),
                Vector(List(3) { (it).toArithmetic() }),
                Vector(List(3) { (0).toArithmetic() })
            ),
        )

        @JvmStatic
        fun getMinusInvalidVectorSizesTestData() = listOf(
            Arguments.of(
                Vector(List(1) { (it).toArithmetic() }),
                Vector(List(2) { (it).toArithmetic() }),
            ),
            Arguments.of(
                Vector(List(2) { (it).toArithmetic() }),
                Vector(List(1) { (it).toArithmetic() }),
            ),
        )

        @JvmStatic
        fun getTimesTestData() = listOf(
            Arguments.of(
                Vector((1).toArithmetic()),
                Vector((2).toArithmetic()),
                (2).toArithmetic()
            ),
            Arguments.of(
                Vector(List(3) { (it).toArithmetic() }),
                Vector(List(3) { (-it).toArithmetic() }),
                (-5).toArithmetic()
            ),
        )

        @JvmStatic
        fun getTimesInvalidVectorSizesTestData() = listOf(
            Arguments.of(
                Vector(List(1) { (it).toArithmetic() }),
                Vector(List(2) { (it).toArithmetic() }),
            ),
            Arguments.of(
                Vector(List(2) { (it).toArithmetic() }),
                Vector(List(1) { (it).toArithmetic() }),
            ),
        )

        @JvmStatic
        fun getIsNullTestData() = listOf(
            Arguments.of(Vector(List(10) { (0).toArithmetic() }), true),
            Arguments.of(Vector(List(10) { (it).toArithmetic() }), false),
        )
    }
}
