package homeworks.homework3.avl

@Suppress("TooManyFunctions")
class AVLTree<K : Comparable<K>, V> : MutableMap<K, V> {
    private var head: AVLNode<K, V>? = null

    override val size: Int
        get() {
            var size = 0
            head?.traversePreOrder { size += 1 }
            return size
        }

    override fun containsKey(key: K): Boolean {
        var currentNode = head

        while (currentNode != null) {
            currentNode = when {
                currentNode.key < key -> currentNode.leftChild
                currentNode.key > key -> currentNode.rightChild
                else -> return true
            }
        }

        return false
    }

    override fun containsValue(value: V): Boolean = value in values

    override fun get(key: K): V? {
        var currentNode = head

        while (currentNode != null) {
            currentNode = when {
                currentNode.key < key -> currentNode.leftChild
                currentNode.key > key -> currentNode.rightChild
                else -> return currentNode.value
            }
        }

        return null
    }

    override fun isEmpty(): Boolean = size == 0

    override val entries: MutableSet<MutableMap.MutableEntry<K, V>>
        get() = mutableSetOf<MutableMap.MutableEntry<K, V>>().apply {
            head?.traversePreOrder { entire -> this.add(entire) }
        }
    override val keys: MutableSet<K>
        get() = mutableSetOf<K>().apply {
            head?.traversePreOrder { entire -> this.add(entire.key) }
        }
    override val values: MutableCollection<V>
        get() = mutableListOf<V>().apply {
            head?.traversePreOrder { entire -> this.add(entire.value) }
        }

    override fun clear() {
        head = null
    }

    override fun put(key: K, value: V): V? {
        head = nodePut(head, key, value)

        return value
    }

    override fun putAll(from: Map<out K, V>) = from.entries.forEach { put(it.key, it.value) }

    override fun remove(key: K): V? = nodeRemove(head, key).let {
        head = it.first

        return it.second?.value
    }

    @Suppress("ReturnCount")
    private fun nodePut(node: AVLNode<K, V>?, key: K, value: V): AVLNode<K, V> {
        node ?: return AVLNode(key, value)

        when {
            key < node.key -> nodePut(node.leftChild, key, value)
            key > node.key -> nodePut(node.rightChild, key, value)
            else -> return node.apply { this.value = value }
        }

        return balanced(node).updateHeight()
    }

    @Suppress("ReturnCount")
    private fun nodeRemove(node: AVLNode<K, V>?, key: K): Pair<AVLNode<K, V>?, AVLNode<K, V>?> {
        node ?: return Pair(null, null)

        var removedNode: AVLNode<K, V>? = null

        when {
            key < node.key -> nodeRemove(node.leftChild, key)
            key > node.key -> nodeRemove(node.rightChild, key)
            else -> when {
                node.leftChild == null && node.rightChild == null -> return Pair(null, node)
                node.leftChild == null -> return Pair(node.rightChild, node)
                node.rightChild == null -> return Pair(node.leftChild, node)
                else -> {
                    node.rightChild?.minChild?.let {
                        node.key = it.key
                        node.value = it.value
                    }

                    with(nodeRemove(node.rightChild, node.key)) {
                        node.rightChild = first
                        removedNode = second
                    }
                }
            }
        }

        return Pair(balanced(node).updateHeight(), removedNode)
    }

    private fun balanced(node: AVLNode<K, V>): AVLNode<K, V> = when (node.balanceFactor) {
        LEFT_SUBTREE_OVERRUN -> if (node.leftChild?.balanceFactor == -1) leftRightRotate(node) else rightRotate(node)
        RIGHT_SUBTREE_OVERRUN -> if (node.rightChild?.balanceFactor == 1) rightLeftRotate(node) else leftRotate(node)
        else -> node
    }

    private fun leftRotate(node: AVLNode<K, V>): AVLNode<K, V> {
        val pivot = node.rightChild!!

        node.rightChild = pivot.leftChild
        pivot.leftChild = node

        node.updateHeight()
        pivot.updateHeight()

        return pivot
    }

    private fun rightRotate(node: AVLNode<K, V>): AVLNode<K, V> {
        val pivot = node.leftChild!!

        node.leftChild = pivot.rightChild
        pivot.rightChild = node

        node.updateHeight()
        pivot.updateHeight()

        return pivot
    }

    private fun rightLeftRotate(node: AVLNode<K, V>): AVLNode<K, V> {
        val rightChild = node.rightChild ?: return node

        node.rightChild = rightRotate(rightChild)

        return leftRotate(node)
    }

    private fun leftRightRotate(node: AVLNode<K, V>): AVLNode<K, V> {
        val leftChild = node.leftChild ?: return node

        node.leftChild = leftRotate(leftChild)

        return rightRotate(node)
    }

    companion object {
        private const val LEFT_SUBTREE_OVERRUN = 2
        private const val RIGHT_SUBTREE_OVERRUN = -2
    }
}
