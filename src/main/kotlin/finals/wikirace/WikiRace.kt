package finals.wikirace

import finals.wikirace.config.Config
import io.github.fastily.jwiki.core.Wiki
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Semaphore
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentLinkedQueue

class WikiRace(private val wikiClient: Wiki, private val config: Config) {
    private val semaphore = Semaphore(config.processCount)

    fun play(): String = runBlocking {
        bfs(config.startPage, config.searchTarget).toPath()
    }

    private suspend fun bfs(start: String, target: String): List<String> = coroutineScope {
        val parents = ConcurrentHashMap<String, String>()
        val currentLevel = ConcurrentLinkedQueue<String>()
        val nextLevel = ConcurrentLinkedQueue<String>()
        var level = 1

        currentLevel.add(start)

        while (!currentLevel.isEmpty() && level <= config.searchDepth) {
            val pageProcessors = currentLevel.map {
                async {
                    semaphore.acquire()
                    val result = processPage(it, start, target, nextLevel, parents)
                    semaphore.release()

                    return@async result
                }
            }

            val pageProcessingResults = awaitAll(deferreds = pageProcessors.toTypedArray())
            val resultPath = pageProcessingResults.filterNotNull().firstOrNull()
            if (resultPath != null) {
                return@coroutineScope resultPath
            }

            currentLevel.clear()
            currentLevel.addAll(nextLevel.toList())
            nextLevel.clear()
            level++
        }

        return@coroutineScope emptyList()
    }

    private fun processPage(
        page: String,
        start: String,
        target: String,
        nextLevel: ConcurrentLinkedQueue<String>,
        parents: ConcurrentHashMap<String, String>
    ): List<String>? {
        println("...Processing ${backtrace(parents, start, page).toPath()}")

        if (page == target) {
            return backtrace(parents, start, target)
        }

        for (link in wikiClient.getLinksOnPageInMain(page)) {
            if (!parents.contains(link)) {
                parents[link] = page
                nextLevel.add(link)
            }
        }

        return null
    }

    private fun backtrace(parents: ConcurrentHashMap<String, String>, start: String, target: String): List<String> {
        val path = mutableListOf(target)

        while (path.last() != start) {
            path.add(parents[path.last()] ?: throw InternalError("error while building backtrace"))
        }

        return path.reversed()
    }

    private fun <T> List<T>.toPath(): String = this.joinToString(" / ") { "\"$it\"" }
}
