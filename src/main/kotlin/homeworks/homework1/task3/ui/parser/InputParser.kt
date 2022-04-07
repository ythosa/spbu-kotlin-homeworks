package homeworks.homework1.task3.ui.parser

class InputParser : Parser {
    override fun parse(line: String): Command {
        val parts = line.trim().split(" ")

        return Command(name = parts.first(), arguments = parts.drop(1))
    }
}
