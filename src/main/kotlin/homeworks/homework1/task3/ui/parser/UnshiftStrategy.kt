package homeworks.homework1.task3.ui.parser

import homeworks.homework1.task3.actions.Action
import homeworks.homework1.task3.actions.UnshiftAction

class UnshiftStrategy : ParsingStrategy {
    override fun parse(arguments: List<String>): Action {
        if (arguments.size != argumentsCount) {
            throw InvalidArgumentsException(expected = argumentsCount, actual = arguments.size)
        }

        val argument: Int = arguments.first().toIntOrNull() ?: throw InvalidArgumentsException(expected = "integer")

        return UnshiftAction(argument)
    }

    override fun getName(): String = "unshift"

    companion object {
        private const val argumentsCount = 1
    }
}
