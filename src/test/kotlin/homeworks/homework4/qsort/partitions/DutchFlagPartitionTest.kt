package homeworks.homework4.qsort.partitions

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class DutchFlagPartitionTest {
    @ParameterizedTest
    @MethodSource("getApplyTestData")
    fun <T : Comparable<T>> apply(
        input: ApplyInput<T>,
        expected: ApplyResult<T>
    ) {
        val partitionResult = DutchFlagPartition<T>().apply(input.list, input.lowIndex, input.highIndex)
        assertEquals(input.list, expected.list)
        assertEquals(partitionResult, expected.partitionResult)
    }

    companion object {
        @JvmStatic
        fun getApplyTestData() = listOf(
            Arguments.of(
                ApplyInput(mutableListOf(1, 2, 4, 3), 0, 3),
                ApplyResult(listOf(1, 2, 3, 4), PartitionResult(1, 3))
            ),
            Arguments.of(
                ApplyInput(mutableListOf(12, 0, 3, 9, 2, 21, 18, 27, 1, 5, 8, -1, 8), 0, 12),
                ApplyResult(listOf(0, 3, -1, 2, 5, 1, 8, 8, 27, 18, 21, 9, 12), PartitionResult(5, 8))
            )
        )
    }
}
