package homeworks.homework4.qsort

import homeworks.homework4.qsort.partitions.Partition
import java.util.concurrent.ForkJoinPool
import java.util.concurrent.RecursiveAction

class QSortThreadPool(
    private val executorService: ForkJoinPool
) : QSort {
    override fun <T : Comparable<T>> sorted(list: MutableList<T>, partition: Partition<T>) {
        executorService.invoke(Sort(list, partition))
    }

    private class Sort<T : Comparable<T>>(
        private val list: MutableList<T>,
        private val partition: Partition<T>,
        private val lowIndex: Int = 0,
        private val highIndex: Int = list.size - 1
    ) : RecursiveAction() {
        override fun compute() {
            if (lowIndex < highIndex) {
                val pivotIndex = partition.apply(list, lowIndex, highIndex)
                invokeAll(
                    Sort(list, partition, lowIndex, pivotIndex),
                    Sort(list, partition, pivotIndex + 1, highIndex),
                )
            }
        }
    }
}
