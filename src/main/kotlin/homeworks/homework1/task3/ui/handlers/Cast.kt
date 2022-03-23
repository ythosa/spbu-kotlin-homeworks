package homeworks.homework1.task3.ui.handlers

fun interface Cast {
    fun cast(argument: String): Any
}

val intCast = Cast { it.toIntOrNull() ?: throw InvalidArgumentsException(expected = "integer") }
