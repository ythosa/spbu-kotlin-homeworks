package finals.wikirace.config

import io.github.fastily.jwiki.core.Wiki

class ConfigReader(private val defaultConfig: WikipediaRaceConfig) {
    fun read(wikiClient: Wiki) = WikipediaRaceConfig.Builder(wikiClient)
        .searchTarget(readSearchTarget())
        .searchDepth(readSearchDepth())
        .processCount(readProcessCount())
        .startPage(readStartPage())
        .build()

    private fun readSearchTarget(): String {
        print("\uD83C\uDF49 Input search target (default: ${defaultConfig.searchTarget}): ")

        return readln().ifEmpty {
            defaultConfig.searchTarget
        }
    }

    private fun readSearchDepth(): Int {
        print("\uD83C\uDF4C Input search depth (default: ${defaultConfig.searchDepth}): ")

        val userInput = readln()
        if (userInput.isEmpty()) {
            return defaultConfig.searchDepth
        }

        try {
            return userInput.toInt()
        } catch (e: NumberFormatException) {
            throw InvalidConfigParameter("integer", this)
        }
    }

    private fun readProcessCount(): Int {
        print("\uD83C\uDF4E Input process count (default: ${defaultConfig.processCount}): ")

        val userInput = readln()
        if (userInput.isEmpty()) {
            return defaultConfig.processCount
        }

        try {
            return userInput.toInt()
        } catch (e: NumberFormatException) {
            throw InvalidConfigParameter("integer", this)
        }
    }

    private fun readStartPage(): String {
        print("\uD83C\uDF52 Input start page (example: ${defaultConfig.startPage}, default: random): ")

        return readln().ifEmpty {
            defaultConfig.startPage
        }
    }
}
