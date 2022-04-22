package homeworks.homework4.gen

import homeworks.homework1.task3.ui.handlers.InvalidArgumentsException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class RandomListGeneratorTest {
    @ParameterizedTest
    @MethodSource("getGenerateTestData")
    fun generate(generatorParameters: GeneratorParameters) {
        val generated = RandomListGenerator.build {
            minValue = generatorParameters.minValue
            maxValue = generatorParameters.maxValue
            elementsCount = generatorParameters.elementsCount
        }.generate()

        assertTrue(generated.all { it >= generatorParameters.minValue })
        assertTrue(generated.all { it < generatorParameters.maxValue })
        assertEquals(generatorParameters.elementsCount, generated.size)
    }

    @Test
    fun `invalid generator bounds`() {
        assertThrows<IllegalArgumentException> {
            RandomListGenerator.build { minValue = 0; maxValue = 0; elementsCount = 100 }.generate()
        }
    }

    @Test
    fun `invalid generator elements count`() {
        assertThrows<IllegalArgumentException> {
            RandomListGenerator.build { minValue = 1; maxValue = 0; elementsCount = -1 }.generate()
        }
    }

    companion object {
        @JvmStatic
        fun getGenerateTestData() = listOf(
            Arguments.of(GeneratorParameters(0, 1, 100)),
            Arguments.of(GeneratorParameters(0, 1000, 0)),
            Arguments.of(GeneratorParameters(0, 1000, 5_000_000)),
        )
    }

    internal data class GeneratorParameters(val minValue: Int, val maxValue: Int, val elementsCount: Int)
}
