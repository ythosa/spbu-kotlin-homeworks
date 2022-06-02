package finals

import finals.wikirace.WikiRace
import finals.wikirace.config.Config
import finals.wikirace.config.ConfigReader
import finals.wikirace.config.InvalidConfigParameter
import io.github.fastily.jwiki.core.Wiki

fun main() {
    val wikiClient = Wiki.Builder().withDefaultLogger(false).build()
    val configBuilder = Config.Builder(wikiClient)
    val defaultConfig = configBuilder.buildDefault()

    try {
        val config = ConfigReader(wikiClient, defaultConfig).read()
        val wikiRace = WikiRace(wikiClient, config)

        val resultSearchPath = wikiRace.start()



    } catch (exception: InvalidConfigParameter) {
        println("❌ ${exception.message}")
    }
}
