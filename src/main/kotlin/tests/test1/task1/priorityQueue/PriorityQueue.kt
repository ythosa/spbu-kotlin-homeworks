package tests.test1.task1.priorityQueue

import java.util.PriorityQueue

fun <E, K : Comparable<K>>
priorityQueueOf(vararg elements: Element<E, K>): tests.test1.task1.priorityQueue.PriorityQueue<E, K> =
    PriorityQueue<E, K>().apply { elements.forEach { enqueue(it.element, it.priority) } }

class PriorityQueue<E, K : Comparable<K>> {
    private val queue = PriorityQueue<Element<E, K>>()

    fun enqueue(element: E, priority: K) {
        queue.add(Element(element, priority))
    }

    fun peek(): E {
        if (queue.isEmpty()) {
            throw EmptyQueueException()
        }

        return queue.peek().element
    }

    fun remove() {
        if (queue.isEmpty()) {
            throw EmptyQueueException()
        }

        queue.remove()
    }

    fun roll(): E {
        if (queue.isEmpty()) {
            throw EmptyQueueException()
        }

        return queue.poll().element
    }

    fun toList() = queue.toList().map { it.element }
}
