package homeworks.homework1.task3

import homeworks.homework1.task3.commandStorage.PerformedCommandStorage
import homeworks.homework1.task3.ui.REPL
import homeworks.homework1.task3.ui.parser.Parser
import homeworks.homework1.task3.ui.parser.PushStrategy
import homeworks.homework1.task3.ui.parser.SwapStrategy
import homeworks.homework1.task3.ui.parser.UnshiftStrategy

fun main() {
    val parser = Parser(listOf(PushStrategy(), SwapStrategy(), UnshiftStrategy()))
    val commandStorage = PerformedCommandStorage()
    val repl = REPL(commandStorage, parser)

    repl.start()
}
