package finals.wikirace

import io.github.fastily.jwiki.core.NS
import io.github.fastily.jwiki.core.Wiki

fun Wiki.getRandomPageAtMain(): String = this.getRandomPages(1, NS.MAIN).first()
