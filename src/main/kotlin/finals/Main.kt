package finals

import io.github.fastily.jwiki.core.NS
import io.github.fastily.jwiki.core.Wiki

fun main() {
    val wiki = Wiki.Builder().build()

    println(wiki.getRandomPages(5, NS.MAIN))
    println(wiki.getLinksOnPage(wiki.getRandomPages(5, NS.MAIN)[1]))
//    try {
//        val config = ConfigReader(defaultConfig).read()
//    } catch (exception: InvalidConfigParameter) {
//        println("❌ ${exception.message}")
//    }
}
