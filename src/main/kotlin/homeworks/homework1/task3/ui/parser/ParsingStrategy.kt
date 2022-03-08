package homeworks.homework1.task3.ui.parser

import homeworks.homework1.task3.actions.Action

interface ParsingStrategy {
    fun parse(arguments: List<String>): Action
    fun getName(): String
}
