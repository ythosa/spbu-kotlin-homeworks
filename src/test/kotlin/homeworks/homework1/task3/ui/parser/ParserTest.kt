package homeworks.homework1.task3.ui.parser

import homeworks.homework1.task3.commandStorage.DuplicatedStrategyNamesException
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class ParserTest {
    @Nested
    inner class InitTest {
        @Test
        fun `duplicated strategy names`() {
            assertThrows<DuplicatedStrategyNamesException> { Parser(listOf(PushStrategy(), PushStrategy())) }
        }
    }

    @Nested
    inner class ParseTest {
        private val parser = Parser(listOf(PushStrategy()))

        @Test
        fun `empty command`() {
            assertThrows<InvalidCommandException> { parser.parse("") }
        }

        @Test
        fun `unknown command`() {
            assertThrows<InvalidCommandException> { parser.parse("kek") }
        }
    }
}
