package homeworks.homework4.qsort.partitions

import homeworks.homework4.qsort.swapAt

class HoarePartition<T : Comparable<T>> : Partition<T> {
    override fun applyPartition(list: MutableList<T>, lowIndex: Int, highIndex: Int): Int {
        val pivot = list[lowIndex]

        var i = lowIndex - 1
        var j = highIndex + 1
        while (true) {
            do {
                j -= 1
            } while (list[j] > pivot)

            do {
                i += 1
            } while (list[i] < pivot)

            if (i < j) {
                list.swapAt(i, j)
            } else {
                return j
            }
        }
    }
}
