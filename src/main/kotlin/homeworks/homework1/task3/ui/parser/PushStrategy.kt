package homeworks.homework1.task3.ui.parser

import homeworks.homework1.task3.actions.Action
import homeworks.homework1.task3.actions.PushAction

class PushStrategy : ParsingStrategy {
    override fun parse(arguments: List<String>): Action {
        if (arguments.size != argumentsCount) {
            throw InvalidArgumentsException(expected = argumentsCount, actual = arguments.size)
        }

        val argument: Int = arguments.first().toIntOrNull() ?: throw InvalidArgumentsException(expected = "integer")

        return PushAction(argument)
    }

    override fun getName(): String = "push"

    companion object {
        private const val argumentsCount = 1
    }
}
