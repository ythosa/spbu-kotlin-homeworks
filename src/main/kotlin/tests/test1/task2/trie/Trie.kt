package tests.test1.task2.trie

internal class Node(val symbol: Char, val isTerminal: Boolean) {
    private val children = mutableListOf<Node>()

    fun addChild(symbol: Char, isTerminal: Boolean): Node {
        val child = Node(symbol, isTerminal)

        children.add(child)

        return child
    }

    fun hasChild(symbol: Char) = children.map { it.symbol }.contains(symbol)

    fun getChild(symbol: Char) = children.find { it.symbol == symbol }
}

class Trie {
    private var head: Node? = null
    private var size = 0

    fun add(element: String): Boolean {
        if (element.isEmpty())
            return true

        if (head == null) {
            if (element.length == 1) {
                head = Node(element.first(), true)
                return true
            } else {
                head = Node(element.first(), false)
            }
        }

        var currentNode = head

        return true
    }
}
