package homeworks.homework1.task3.ui.handlers

import homeworks.homework1.task3.ui.Commands

class HelpHandler : CLIHandler() {
    override fun handle(arguments: List<String>) {
        println(
            """Commands:
            * ${Commands.HELP.commandName} - returns help message
            * ${Commands.EXIT.commandName} - exit from command
            * ${Commands.CANCEL.commandName} - cancel last command
            * ${Commands.PUSH_BACK.commandName} <Int> - add integer element to the end
            * ${Commands.PUSH_FIRST.commandName} <Int> - add integer element to the start
            * ${Commands.MOVE.commandName} from<Int> to<Int> - move elements from `from` index to `to`
            * ${Commands.STATE.commandName} - returns current state
            """.trimIndent()
        )
    }
}
