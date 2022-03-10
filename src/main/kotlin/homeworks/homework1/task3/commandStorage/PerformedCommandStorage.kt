package homeworks.homework1.task3.commandStorage

import homeworks.homework1.task3.commandStorage.actions.Action
import java.util.LinkedList
import java.util.Stack

class PerformedCommandStorage : CommandStorage {
    private val commands = Stack<Action>()
    private val elements = LinkedList<Int>()

    override fun apply(action: Action) {
        action.apply(elements)
        commands.push(action)
    }

    override fun cancel() {
        if (commands.isEmpty()) {
            throw ImpossibleCancelCommandException()
        }

        commands.pop().cancel(elements)
    }

    override fun getElements(): LinkedList<Int> {
        return elements
    }
}
