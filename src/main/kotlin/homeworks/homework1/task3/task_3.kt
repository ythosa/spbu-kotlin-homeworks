package homeworks.homework1.task3

import homeworks.homework1.task3.commandStorage.CommandStorage
import homeworks.homework1.task3.commandStorage.PerformedCommandStorage
import homeworks.homework1.task3.ui.Commands
import homeworks.homework1.task3.ui.REPL
import homeworks.homework1.task3.ui.handlers.CLIHandler
import homeworks.homework1.task3.ui.handlers.CancelHandler
import homeworks.homework1.task3.ui.handlers.ExitHandler
import homeworks.homework1.task3.ui.handlers.HelpHandler
import homeworks.homework1.task3.ui.handlers.MoveHandler
import homeworks.homework1.task3.ui.handlers.PushBackHandler
import homeworks.homework1.task3.ui.handlers.StateHandler
import homeworks.homework1.task3.ui.handlers.PushFirstHandler
import homeworks.homework1.task3.ui.parser.InputParser

fun getReplConfig(commandStorage: CommandStorage): Map<String, CLIHandler> = mapOf(
    Pair(Commands.PUSH_BACK.commandName, PushBackHandler(commandStorage)),
    Pair(Commands.PUSH_FIRST.commandName, PushFirstHandler(commandStorage)),
    Pair(Commands.MOVE.commandName, MoveHandler(commandStorage)),
    Pair(Commands.CANCEL.commandName, CancelHandler(commandStorage)),
    Pair(Commands.STATE.commandName, StateHandler(commandStorage)),
    Pair(Commands.HELP.commandName, HelpHandler()),
    Pair(Commands.EXIT.commandName, ExitHandler())
)

fun main() {
    val commandStorage = PerformedCommandStorage()
    val parser = InputParser()
    val repl = REPL(parser, getReplConfig(commandStorage))

    repl.start()
}
