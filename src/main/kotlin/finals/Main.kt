package finals

fun main() {
    try {
        val config = ConfigReader(defaultConfig).read()
    } catch (exception: InvalidConfigParameter) {
        println("❌ ${exception.message}")
    }
}
