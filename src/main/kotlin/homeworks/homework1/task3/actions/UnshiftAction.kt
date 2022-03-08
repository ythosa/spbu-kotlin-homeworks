package homeworks.homework1.task3.actions

import java.util.LinkedList

class UnshiftAction(private val element: Int) : Action {
    override fun apply(elements: LinkedList<Int>) {
        elements.addFirst(element)
    }

    override fun cancel(elements: LinkedList<Int>) {
        elements.removeFirst()
    }
}
