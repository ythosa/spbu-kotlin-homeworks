package homeworks.homework4.qsort

import homeworks.homework4.qsort.partitions.Partition

class QSort<T : Comparable<T>>(
    private val list: MutableList<T>,
    private val partition: Partition<T>
) : Sort<T> {
    override fun getSorted(): MutableList<T> {
        sort(0, list.size - 1)

        return list
    }

    private fun sort(lowIndex: Int, highIndex: Int) {
        if (lowIndex < highIndex) {
            val pivotIndex = partition.applyPartition(list, lowIndex, highIndex)
            sort(lowIndex, pivotIndex)
            sort(pivotIndex + 1, highIndex)
        }
    }
}
