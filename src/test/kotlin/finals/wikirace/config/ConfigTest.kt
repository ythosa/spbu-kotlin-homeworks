package finals.wikirace.config

import io.github.fastily.jwiki.core.Wiki
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

internal class ConfigTest {
    private val wikiClient = Wiki.Builder().withDefaultLogger(false).build()
    private val builder = Config.Builder(wikiClient)

    @Test
    fun `search target building error`() {
        assertThrows<InvalidConfigParameter> { builder.searchTarget("abrakadabra kekw") }
    }

    @Test
    fun `search target building success`() {
        assertDoesNotThrow { builder.searchTarget("Geometry") }
    }

    @Test
    fun `search depth building error`() {
        assertThrows<InvalidConfigParameter> { builder.searchDepth(-1) }
    }

    @Test
    fun `search depth building success`() {
        assertDoesNotThrow { builder.searchDepth(1) }
    }

    @Test
    fun `process count building error`() {
        assertThrows<InvalidConfigParameter> { builder.processCount(0) }
    }

    @Test
    fun `process count building success`() {
        assertDoesNotThrow { builder.processCount(1) }
    }

    @Test
    fun `start page building error`() {
        assertThrows<InvalidConfigParameter> { builder.startPage("abrakadabra kekw") }
    }

    @Test
    fun `start page building success`() {
        assertDoesNotThrow { builder.startPage("Geometry") }
    }

    @Test
    fun `default config is valid`() {
        assertDoesNotThrow { builder.buildDefault() }
    }
}
