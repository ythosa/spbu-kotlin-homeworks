package homeworks.homework3

import homeworks.homework3.avl.avlTreeOf

infix fun String.example(function: () -> Unit) {
    println("- Example of $this")
    function()
    println()
}

@Suppress("SpreadOperator", "MagicNumber")
fun main() {
    "tree balancing" example {
        val tree = avlTreeOf(*Array(10) { Pair(it, 0) })
        println(tree)
    }

    "tree put" example {
        val tree = avlTreeOf(*Array(10) { Pair(it, 0) })
        println(tree)
        tree[11] = 11
        println(tree)
    }

    "tree remove" example {
        val tree = avlTreeOf(*Array(10) { Pair(it, 0) })
        println(tree)
        tree.remove(3)
        println(tree)
    }
}
