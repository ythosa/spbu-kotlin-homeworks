package homeworks.homework1.task3.commandStorage

import homeworks.homework1.task3.actions.Action
import java.util.LinkedList
import java.util.Stack

class PerformedCommandStorage {
    private val commands = Stack<Action>()
    private val elements = LinkedList<Int>()

    fun apply(action: Action) {
        action.apply(elements)
        commands.push(action)
    }

    fun cancel() {
        if (commands.isEmpty()) {
            throw ImpossibleCancelCommandException()
        }

        commands.pop().cancel(elements)
    }

    fun getElements(): LinkedList<Int> {
        return elements
    }
}
