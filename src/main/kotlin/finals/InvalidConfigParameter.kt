package finals

class InvalidConfigParameter(
    expected: String,
    actual: Any,
) : Exception("Invalid parameter, expected: $expected, actual: $actual")
