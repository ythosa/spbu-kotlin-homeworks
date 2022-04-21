package homeworks.homework4.qsort

import homeworks.homework4.qsort.partitions.Partition
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class QSortCoroutines : QSort {
    override fun <T : Comparable<T>> sorted(list: MutableList<T>, partition: Partition<T>): Unit = runBlocking {
        launch {
            sort(list, partition)
        }
    }

    private suspend fun <T : Comparable<T>> sort(
        list: MutableList<T>,
        partition: Partition<T>,
        lowIndex: Int = 0,
        highIndex: Int = list.size - 1
    ): Unit = coroutineScope {
        if (lowIndex < highIndex) {
            val pivotIndex = partition.apply(list, lowIndex, highIndex)

            launch {
                sort(list, partition, lowIndex, pivotIndex - 1)
            }

            launch {
                sort(list, partition, pivotIndex + 1, highIndex)
            }
        }
    }
}
