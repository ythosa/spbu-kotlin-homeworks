package homeworks.homework4.qsort

import homeworks.homework4.gen.RandomListOfIntsGenerator
import homeworks.homework4.qsort.partitions.LomutoPartition
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.util.concurrent.ForkJoinPool
import java.util.stream.Stream

internal class QSortTest {
    @Nested
    inner class QSortSequentialTest {
        @ParameterizedTest
        @ArgumentsSource(SortedTestDataProvider::class)
        fun <T : Comparable<T>> sorted(list: MutableList<T>) {
            val qSortSequential = QSortSequential<T>(LomutoPartition())
            val expected = list.toMutableList().sorted()

            assertEquals(expected, list.apply { qSortSequential.sorted(list) })
        }
    }

    @Nested
    inner class QSortThreadPool {
        @ParameterizedTest
        @ArgumentsSource(SortedTestDataProvider::class)
        fun <T : Comparable<T>> sorted(list: MutableList<T>) {
            val qSortThreadPool = QSortThreadPool<T>(LomutoPartition(), ForkJoinPool())
            val expected = list.toMutableList().sorted()

            assertEquals(expected, list.apply { qSortThreadPool.sorted(list) })
        }
    }

    @Nested
    inner class QSortCoroutines {
        @ParameterizedTest
        @ArgumentsSource(SortedTestDataProvider::class)
        fun <T : Comparable<T>> sorted(list: MutableList<T>) {
            val qSortCoroutines = QSortCoroutines<T>(LomutoPartition())
            val expected = list.toMutableList().sorted()

            assertEquals(expected, list.apply { qSortCoroutines.sorted(list) })
        }
    }

    companion object SortedTestDataProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<Arguments> = Stream.of(
            Arguments.of(mutableListOf<Int>()),
            Arguments.of(mutableListOf(5, 2, 1, 3, 4)),
            Arguments.of(mutableListOf(1, 1, 1, 3, 4, 2)),
            Arguments.of(mutableListOf(1, 1, 1, 1, 1, 1)),
            Arguments.of(
                RandomListOfIntsGenerator.build {
                    minValue = 0
                    maxValue = 1000
                    elementsCount = 1000
                }.generate().toMutableList()
            ),
            Arguments.of(
                RandomListOfIntsGenerator.build {
                    minValue = 0
                    maxValue = 1000
                    elementsCount = 1000
                }.generate().toMutableList().sorted()
            )
        )
    }
}
