package tests.test1.task1.priorityQueue

import java.util.PriorityQueue

fun <E, K : Comparable<K>>
        priorityQueueOf(vararg elements: Element<E, K>): PriorityQueueImpl<E, K> =
    PriorityQueueImpl<E, K>().apply { elements.forEach { enqueue(it.element, it.priority) } }

class PriorityQueueImpl<E, K : Comparable<K>> : tests.test1.task1.priorityQueue.PriorityQueue<E, K> {
    private val queue = PriorityQueue<Element<E, K>>()

    override fun enqueue(element: E, priority: K) {
        queue.add(Element(element, priority))
    }

    override fun peek(): E {
        if (queue.isEmpty()) {
            throw EmptyQueueException()
        }

        return queue.peek().element
    }

    override fun remove() {
        if (queue.isEmpty()) {
            throw EmptyQueueException()
        }

        queue.remove()
    }

    override fun roll(): E {
        if (queue.isEmpty()) {
            throw EmptyQueueException()
        }

        return queue.poll().element
    }

    override fun toList() = queue.toList().map { it.element }
}
