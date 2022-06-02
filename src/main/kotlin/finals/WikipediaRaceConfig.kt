package finals

val defaultConfig = WikipediaRaceConfig.Builder()
    .searchTargetUrl("https://en.wikipedia.org/wiki/Adolf_Hitler")
    .searchDepth(0)
    .processCount(1)
    .startPageUrl("https://en.wikipedia.org/wiki/Adolf_Hitler")
    .build()

class WikipediaRaceConfig private constructor(builder: Builder) {
    val searchTargetUrl: String = builder.searchTargetUrl
    val searchDepth: Int = builder.searchDepth
    val processCount: Int = builder.processCount
    val startPageUrl: String = builder.startPageUrl

    class Builder {
        var searchTargetUrl = ""
            private set
        var searchDepth = 1
            private set
        var processCount = 0
            private set
        var startPageUrl = ""
            private set

        fun searchTargetUrl(target: String) = this.apply {
            searchTargetUrl = target.also { validateSearchTargetUrl(it) }
        }

        fun searchDepth(depth: Int) = this.apply {
            searchDepth = depth.also { validateSearchDepth(it) }
        }

        fun processCount(count: Int) = this.apply {
            processCount = count.also { validateProcessCount(it) }
        }

        fun startPageUrl(url: String) = this.apply {
            startPageUrl = url.also { validateStartPageUrl(it) }
        }

        fun build() = WikipediaRaceConfig(this)

        private fun validateSearchTargetUrl(target: String) {
            if (!isValidWikiPage(target)) {
                throw InvalidConfigParameter("valid wiki url", target)
            }
        }

        private fun validateSearchDepth(depth: Int) {
            if (depth < 0) {
                throw InvalidConfigParameter("greater or equals then 0", depth)
            }
        }

        private fun validateProcessCount(count: Int) {
            if (count < 1) {
                throw InvalidConfigParameter("greater then 0", count)
            }
        }

        private fun validateStartPageUrl(page: String) {
            if (!isValidWikiPage(page)) {
                throw InvalidConfigParameter("valid wiki url", page)
            }
        }
    }
}
