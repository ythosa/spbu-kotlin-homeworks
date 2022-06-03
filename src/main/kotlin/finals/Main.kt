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

        val resultSearchPath = wikiRace.play()

        print("\n✔️ Result: ")
        if (resultSearchPath.isEmpty()) {
            println("Target page not found with such depth \uD83E\uDD7A")
        } else {
            println("$resultSearchPath \uD83E\uDD73")
        }
    } catch (exception: InvalidConfigParameter) {
        println("❌ ${exception.message}")
    }
}
