package tests.test1.task1

import tests.test1.task1.priorityQueue.Element
import tests.test1.task1.priorityQueue.priorityQueueOf

fun main() {
    val q =  priorityQueueOf(Element(1, 1), Element(2, 2), Element(3, 3))
    println(q.peek())
}
