package finals.wikirace

import finals.wikirace.config.Config
import io.github.fastily.jwiki.core.Wiki
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Semaphore
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.logging.Logger

class WikiRace(private val wikiClient: Wiki, private val config: Config) {
    private val logger = Logger.getLogger(WikiRace::javaClass.name)
    private val semaphore = Semaphore(config.processCount)

    fun start(): String = runBlocking {
        bfs(config.startPage, config.searchTarget).toPath()
    }

    private fun bfs(start: String, target: String): List<String> {
        val parents = ConcurrentHashMap<String, String>()
        val currentLevel = ConcurrentLinkedQueue<String>()
        val nextLevel = ConcurrentLinkedQueue<String>()
        var level = 1

        currentLevel.add(start)

        while (!currentLevel.isEmpty() && level < config.searchDepth) {
            for (page in currentLevel) {
                println("Processing ${backtrace(parents, start, page).toPath()}")

                if (page == target) {
                    return backtrace(parents, start, target)
                }

                for (pageNeighbour in wikiClient.getLinksOnPageInMain(page)) {
                    if (!parents.contains(pageNeighbour)) {
                        parents[pageNeighbour] = page
                        nextLevel.add(pageNeighbour)
                    }
                }
            }

            currentLevel.clear()
            currentLevel.addAll(nextLevel.toList())
            nextLevel.clear()
            level += 1
        }

        return emptyList()
    }

    private fun backtrace(parents: ConcurrentHashMap<String, String>, start: String, target: String): List<String> {
        val path = mutableListOf(target)

        while (path.last() != start) {
            path.add(parents[path.last()]!!)
        }

        return path.reversed()
    }

    private fun <T> List<T>.toPath(): String = this.joinToString(" / ") { "\"$it\"" }
}
