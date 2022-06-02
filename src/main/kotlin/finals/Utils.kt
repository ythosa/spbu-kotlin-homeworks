package finals

private val isValidWikiPageRegex = Regex("^https?://en.wikipedia.org/wiki/.+\$")
fun isValidWikiPage(url: String) = isValidWikiPageRegex.matches(url)
