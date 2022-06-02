package finals

class ConfigReader(private val defaultConfig: WikipediaRaceConfig) {
    fun read() = WikipediaRaceConfig.Builder()
        .searchTargetUrl(readSearchTargetUrl())
        .searchDepth(readSearchDepth())
        .processCount(readProcessCount())
        .startPageUrl(readStartPageUrl())
        .build()

    private fun readSearchTargetUrl(): String {
        print("\uD83C\uDF49 Input search target url (default: ${defaultConfig.searchTargetUrl}): ")

        return readln().ifEmpty {
            defaultConfig.searchTargetUrl
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

    private fun readStartPageUrl(): String {
        print("\uD83C\uDF52 Input start page url (example: ${defaultConfig.startPageUrl}, default: random): ")

        return readln().ifEmpty {
            defaultConfig.startPageUrl
        }
    }
}
