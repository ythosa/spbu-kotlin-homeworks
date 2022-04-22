package homeworks.homework4.qsort.partitions

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class LomutoPartitionTest {
    @ParameterizedTest
    @MethodSource("getApplyTestData")
    fun <T : Comparable<T>> apply(
        input: ApplyInput<T>,
        expected: ApplyResult<T>
    ) {
        val partitionResult = LomutoPartition<T>().apply(input.list, input.lowIndex, input.highIndex)
        assertEquals(input.list, expected.list)
        assertEquals(partitionResult, expected.partitionResult)
    }

    companion object {
        @JvmStatic
        fun getApplyTestData() = listOf(
            Arguments.of(
                ApplyInput(mutableListOf(3, 1, 4, 2), 0, 3),
                ApplyResult(listOf(1, 2, 4, 3), PartitionResult(0, 2))
            ),
            Arguments.of(
                ApplyInput(mutableListOf(12, 0, 3, 9, 2, 21, 18, 27, 1, 5, 8, -1, 8), 0, 12),
                ApplyResult(listOf(0, 3, 2, 1, 5, 8, -1, 8, 9, 12, 21, 18, 27), PartitionResult(6, 8))
            )
        )
    }
}
