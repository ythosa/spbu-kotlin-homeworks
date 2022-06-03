package finals.wikirace.config

class InvalidConfigParameter(
    expected: String,
    actual: Any,
) : Exception("Invalid parameter, expected: $expected, actual: $actual")
