package homeworks.homework1.task3.ui.parser

interface Parser {
    fun parse(line: String): Command
}
