package homeworks.homework1.task3.ui.handlers

abstract class CLIHandler {
    abstract fun handle(arguments: List<String>)

    fun extractArguments(arguments: List<String>, expected: Array<Cast>): List<Any> {
        if (arguments.size != expected.size) {
            throw InvalidArgumentsException(expected = expected.size, actual = arguments.size)
        }

        val castedArguments = mutableListOf<Any>()
        for ((i, argument) in arguments.withIndex()) {
            castedArguments.add(expected[i].cast(argument))
        }

        return castedArguments
    }
}
