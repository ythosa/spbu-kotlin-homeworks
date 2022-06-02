package finals.wikirace

import io.github.fastily.jwiki.core.NS
import io.github.fastily.jwiki.core.Wiki

fun Wiki.getRandomPageInMain(): String = this.getRandomPages(1, NS.MAIN).first()

fun Wiki.getLinksOnPageInMain(title: String): ArrayList<String> = this.getLinksOnPage(title, NS.MAIN)
