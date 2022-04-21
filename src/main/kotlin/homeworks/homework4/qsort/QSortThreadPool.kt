package homeworks.homework4.qsort

import homeworks.homework4.qsort.partitions.Partition
import java.util.concurrent.ForkJoinPool
import java.util.concurrent.RecursiveAction

class QSortThreadPool<T : Comparable<T>>(
    private val partition: Partition<T>,
    private val executorService: ForkJoinPool,
) : QSort<T> {
    override fun sorted(list: MutableList<T>) {
        executorService.invoke(Sort(list))
    }

    private inner class Sort(
        private val list: MutableList<T>,
        private val lowIndex: Int = 0,
        private val highIndex: Int = list.size - 1
    ) : RecursiveAction() {
        override fun compute() {
            if (lowIndex < highIndex) {
                val partitionIndices = partition.apply(list, lowIndex, highIndex)
                invokeAll(
                    Sort(list, lowIndex, partitionIndices.leftToIndex),
                    Sort(list, partitionIndices.rightFromIndex, highIndex),
                )
            }
        }
    }
}
