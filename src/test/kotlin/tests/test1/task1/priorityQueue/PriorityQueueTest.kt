package tests.test1.task1.priorityQueue

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.math.exp

internal class PriorityQueueTest {
    @ParameterizedTest
    @MethodSource("getEnqueueTestData")
    fun <E, K : Comparable<K>> enqueue(
        queue: PriorityQueue<E, K>,
        element: Element<E, K>,
        expected: List<E>
    ) {
        assertEquals(expected, queue.apply { enqueue(element.element, element.priority) }.toList())
    }

    @ParameterizedTest
    @MethodSource("getPeekTestData")
    fun <E, K : Comparable<K>> peek(queue: PriorityQueue<E, K>, expected: E) {
        assertEquals(expected, queue.peek())
    }

    @ParameterizedTest
    @MethodSource("getRemoveTestData")
    fun <E, K : Comparable<K>> remove(queue: PriorityQueue<E, K>, expected: List<E>) {
        assertEquals(expected, queue.apply { remove() }.toList())
    }

    @ParameterizedTest
    @MethodSource("getRollTestData")
    fun <E, K : Comparable<K>> roll(queue: PriorityQueue<E, K>, expected: E) {
        assertEquals(expected, queue.roll())
    }

    companion object {
        @JvmStatic
        fun getEnqueueTestData() = listOf(
            Arguments.of(
                priorityQueueOf<String, Int>(),
                Element(4, 4),
                listOf(4)
            ),
            Arguments.of(
                priorityQueueOf(Element(2, 2), Element(1, 1), Element(3, 3)),
                Element(4, 4),
                listOf(1, 2, 3, 4)
            )
        )

        @JvmStatic
        fun getPeekTestData() = listOf(
            Arguments.of(priorityQueueOf(Element(2, 2)), 2),
            Arguments.of(priorityQueueOf(Element(2, 2), Element(1, 1)), 1),
            Arguments.of(priorityQueueOf(Element(2, 2), Element(1, 1), Element(3, 3)), 1)
        )

        @JvmStatic
        fun getRemoveTestData() = listOf(
            Arguments.of(
                priorityQueueOf(Element(4, 4)),
                emptyList<Int>()
            ),
            Arguments.of(
                priorityQueueOf(Element(2, 2), Element(1, 1), Element(3, 3)),
                listOf(2, 3),
            )
        )

        @JvmStatic
        fun getRollTestData() = listOf(
            Arguments.of(priorityQueueOf(Element(4, 4)), 4),
            Arguments.of(priorityQueueOf(Element(2, 2), Element(1, 1), Element(3, 3)), 1)
        )
    }
}