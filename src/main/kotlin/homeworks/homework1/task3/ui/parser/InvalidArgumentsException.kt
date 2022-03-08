package homeworks.homework1.task3.ui.parser

class InvalidArgumentsException(expected: Any, actual: Any? = null) :
    Exception("error: invalid arguments, expected: $expected ${if (actual == null) "" else "actual: $actual"}")
