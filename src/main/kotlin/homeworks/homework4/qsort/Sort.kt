package homeworks.homework4.qsort

interface Sort<T : Comparable<T>> {
    fun getSorted(): MutableList<T>
}
