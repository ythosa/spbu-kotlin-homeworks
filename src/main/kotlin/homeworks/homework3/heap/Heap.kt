package homeworks.homework3.heap

interface Heap<T: Comparable<T>> {
    fun insert(element: T)
    fun remove(): T?
}
