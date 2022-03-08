package homeworks.homework1.task2.eratosthenesSieve

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class EratosthenesSieveGetPrimesUpToTheBoundaryTest {
    @ParameterizedTest
    @MethodSource("getParameters")
    fun `get primes under 98 (97 must be included)`(sieve: EratosthenesSieve) {
        val expected =
            listOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97)
        assertEquals(expected, sieve.getPrimesUpToTheBoundary(98))
    }

    @ParameterizedTest
    @MethodSource("getParameters")
    fun `get primes under 97 (97 must be excluded)`(sieve: EratosthenesSieve) {
        val expected =
            listOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89)
        assertEquals(expected, sieve.getPrimesUpToTheBoundary(97))
    }

    @ParameterizedTest
    @MethodSource("getParameters")
    fun `get primes under 2 (must be empty list)`(sieve: EratosthenesSieve) {
        assertEquals(emptyList<Int>(), sieve.getPrimesUpToTheBoundary(2))
    }

    @ParameterizedTest
    @MethodSource("getParameters")
    fun `get primes with boundary lower then 1 `(sieve: EratosthenesSieve) {
        val bound = 0
        val exception = assertThrows<InvalidBoundException> { sieve.getPrimesUpToTheBoundary(bound) }
        assertEquals(InvalidBoundException(bound).message, exception.message)
    }

    companion object {
        @JvmStatic
        fun getParameters() = listOf(
            Arguments.of(EratosthenesSieveImperative()),
            Arguments.of(EratosthenesSieveFunctionally())
        )
    }
}
