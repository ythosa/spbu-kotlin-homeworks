package homeworks.homework4.qsort

import homeworks.homework4.qsort.partitions.Partition
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class QSortCoroutines<T : Comparable<T>>(private val partition: Partition<T>) : QSort<T> {
    override fun sorted(list: MutableList<T>): Unit = runBlocking {
        sort(list)
    }

    private suspend fun sort(
        list: MutableList<T>,
        lowIndex: Int = 0,
        highIndex: Int = list.size - 1
    ): Unit = coroutineScope {
        if (lowIndex < highIndex) {
            val partitionIndices = partition.apply(list, lowIndex, highIndex)

            launch {
                sort(list, lowIndex, partitionIndices.leftToIndex)
            }

            launch {
                sort(list, partitionIndices.rightFromIndex, highIndex)
            }
        }
    }
}
