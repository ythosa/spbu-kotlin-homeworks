package homeworks.homework4.qsort

interface QSort<T : Comparable<T>> {
    fun sorted(list: MutableList<T>)
}
