package tests.test1.task1.priorityQueue

interface PriorityQueue<E, K : Comparable<K>> {
    fun enqueue(element: E, priority: K)
    fun peek(): E
    fun remove()
    fun roll(): E
    fun toList(): List<E>
}
