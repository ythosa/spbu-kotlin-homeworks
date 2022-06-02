package finals.wikirace.config

import io.github.fastily.jwiki.core.Wiki

class Config private constructor(builder: Builder) {
    val searchTarget: String = builder.searchTarget
    val searchDepth: Int = builder.searchDepth
    val processCount: Int = builder.processCount
    val startPage: String = builder.startPage

    class Builder(private val wikiClient: Wiki) {
        var searchTarget = ""
            private set
        var searchDepth = 1
            private set
        var processCount = 0
            private set
        var startPage = ""
            private set

        fun searchTarget(target: String) = this.apply {
            searchTarget = target.also { validateSearchTargetUrl(it) }
        }

        fun searchDepth(depth: Int) = this.apply {
            searchDepth = depth.also { validateSearchDepth(it) }
        }

        fun processCount(count: Int) = this.apply {
            processCount = count.also { validateProcessCount(it) }
        }

        fun startPage(url: String) = this.apply {
            startPage = url.also { validateStartPageUrl(it) }
        }

        fun build() = Config(this)

        fun buildDefault() = Config(
            this.searchTarget("Adolf Hitler")
                .searchDepth(0)
                .processCount(1)
                .startPage("Adolf Hitler")
        )

        private fun validateSearchTargetUrl(target: String) {
            if (!wikiClient.exists(target)) {
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
            if (!wikiClient.exists(page)) {
                throw InvalidConfigParameter("valid wiki url", page)
            }
        }
    }
}
