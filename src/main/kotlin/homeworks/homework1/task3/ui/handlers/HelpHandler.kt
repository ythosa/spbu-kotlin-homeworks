package homeworks.homework1.task3.ui.handlers

import homeworks.homework1.task3.ui.Commands

class HelpHandler : CLIHandler() {
    override fun handle(arguments: List<String>) {
        println(
            """Commands:
            * ${Commands.HELP.commandName} - returns help message
            * ${Commands.EXIT.commandName} - exit from command
            * ${Commands.CANCEL.commandName} - cancel last command
            * ${Commands.PUSH.commandName} <Int> - add integer element to the end
            * ${Commands.UNSHIFT.commandName} <Int> - add integer element to the start
            * ${Commands.MOVE.commandName} first<Int> second<Int> - move elements from `first` index to `second`
            * ${Commands.STATE.commandName} - returns current state
            """.trimIndent()
        )
    }
}
