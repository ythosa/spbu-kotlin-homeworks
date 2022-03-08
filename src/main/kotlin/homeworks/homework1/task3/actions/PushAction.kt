package homeworks.homework1.task3.actions

import java.util.LinkedList

class PushAction(private val element: Int) : Action {
    override fun apply(elements: LinkedList<Int>) {
        elements.addLast(element)
    }

    override fun cancel(elements: LinkedList<Int>) {
        elements.removeLast()
    }
}
