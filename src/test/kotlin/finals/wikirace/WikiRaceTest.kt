package finals.wikirace

import finals.wikirace.config.Config
import io.github.fastily.jwiki.core.Wiki
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class WikiRaceTest {
    @ParameterizedTest
    @MethodSource("getPlayTestData")
    fun play(config: Config, expected: String) {
        assertEquals(expected, WikiRace(wikiClient, config).play())
    }

    companion object {
        val wikiClient: Wiki = Wiki.Builder().withDefaultLogger(false).build()

        @JvmStatic
        fun getPlayTestData() = listOf(
            Arguments.of(
                Config.Builder(wikiClient).buildDefault(),
                ""
            ),
            Arguments.of(
                Config.Builder(wikiClient).searchTarget("Adolf Hitler")
                    .searchDepth(1)
                    .processCount(1)
                    .startPage("Adolf Hitler")
                    .build(),
                "\"Adolf Hitler\""
            ),
            Arguments.of(
                Config.Builder(wikiClient).searchTarget("Abrasion (mechanical)")
                    .searchDepth(2)
                    .processCount(1)
                    .startPage("Hardness")
                    .build(),
                "\"Hardness\" / \"Abrasion (mechanical)\""
            ),
        )
    }
}
