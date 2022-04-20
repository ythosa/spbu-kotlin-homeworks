package homeworks.homework4.qsort.partitions

import homeworks.homework4.qsort.swapAt

class LomutoPartition<T : Comparable<T>> : Partition<T> {
    override fun applyPartition(list: MutableList<T>, lowIndex: Int, highIndex: Int): Int {
        val pivot = list[highIndex]

        var i = lowIndex
        for (j in lowIndex until highIndex) {
            if (list[j] <= pivot) {
                list.swapAt(i, j)
                i += 1
            }
        }

        list.swapAt(i, highIndex)

        return i
    }
}
