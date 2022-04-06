package homeworks.homework3.avl

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class AVLTreeTest {
    @ParameterizedTest
    @MethodSource("getSizeGetTestData")
    fun <K : Comparable<K>, V> getSize(tree: AVLTree<K, V>, expected: Int) {
        assertEquals(expected, tree.size)
    }

    @ParameterizedTest
    @MethodSource("containsKeyGetTestData")
    fun <K : Comparable<K>, V> containsKey(tree: AVLTree<K, V>, key: K, expected: Boolean) {
        assertEquals(tree.containsKey(key), expected)
    }

    @ParameterizedTest
    @MethodSource("containsValueGetTestData")
    fun <K : Comparable<K>, V> containsValue(tree: AVLTree<K, V>, value: V, expected: Boolean) {
        assertEquals(tree.containsValue(value), expected)
    }

    @ParameterizedTest
    @MethodSource("getGetTestData")
    fun <K : Comparable<K>, V> get(tree: AVLTree<K, V>, key: K, expected: Any?) {
        assertEquals(tree[key], expected)
    }

    @ParameterizedTest
    @MethodSource("isEmptyGetTestData")
    fun <K : Comparable<K>, V> isEmpty(tree: AVLTree<K, V>, expected: Boolean) {
        assertEquals(tree.isEmpty(), expected)
    }

    @ParameterizedTest
    @MethodSource("getEntriesGetTestData")
    fun <K : Comparable<K>, V> getEntries(tree: AVLTree<K, V>, expected: Set<Entry<K, V>>) {
        assertEquals(tree.entries, expected)
    }

    @ParameterizedTest
    @MethodSource("getKeysGetTestData")
    fun <K : Comparable<K>, V> getKeys(tree: AVLTree<K, V>, expected: Set<K>) {
        assertEquals(tree.keys, expected)
    }

    @ParameterizedTest
    @MethodSource("getValuesGetTestData")
    fun <K : Comparable<K>, V> getValues(tree: AVLTree<K, V>, expected: Collection<V>) {
        assertEquals(tree.values, expected)
    }

    @ParameterizedTest
    @MethodSource("clearGetTestData")
    fun <K : Comparable<K>, V> clear(tree: AVLTree<K, V>) {
        assertEquals(tree.apply { clear() }.size, 0)
    }

    @ParameterizedTest
    @MethodSource("putGetTestData")
    fun <K : Comparable<K>, V> put(tree: AVLTree<K, V>, entry: Entry<K, V>, expected: AVLTree<K, V>) {
        assertEquals(tree.apply { put(entry.key, entry.value) }.entries, expected.entries)
    }

    @ParameterizedTest
    @MethodSource("putAllGetTestData")
    fun <K : Comparable<K>, V> putAll(tree: AVLTree<K, V>, from: Map<K, V>, expected: AVLTree<K, V>) {
        assertEquals(tree.apply { putAll(from) }.entries, expected.entries)
    }

    @ParameterizedTest
    @MethodSource("removeGetTestData")
    fun <K : Comparable<K>, V> remove(tree: AVLTree<K, V>, key: K, expected: Pair<AVLTree<K, V>, V>) {
        val removeResult = tree.remove(key)

        assertEquals(tree.entries, expected.first.entries)
        assertEquals(removeResult, expected.second)
    }

    companion object {
        @JvmStatic
        fun getSizeGetTestData() = listOf(
            Arguments.of(avlTreeOf<Int, Any>(), 0),
            Arguments.of(avlTreeOf(Pair(1, 2)), 1),
            Arguments.of(avlTreeOf(Pair(2, 1), Pair(3, 2)), 2),
        )

        @JvmStatic
        fun containsKeyGetTestData() = listOf(
            Arguments.of(avlTreeOf(Pair(1, 2)), 1, true),
            Arguments.of(avlTreeOf(Pair("kek", 2)), "lol", false),
            Arguments.of(avlTreeOf<Int, Any>(), 0, false),
            Arguments.of(avlTreeOf(Pair(1, 2), Pair(2, 3)), 3, false),
            Arguments.of(avlTreeOf(Pair(1, 2), Pair(2, 3)), 2, true),
        )

        @JvmStatic
        fun containsValueGetTestData() = listOf(
            Arguments.of(avlTreeOf(Pair(1, 2)), 1, false),
            Arguments.of(avlTreeOf(Pair(1, 2)), 2, true),
            Arguments.of(avlTreeOf(Pair("kek", "lol")), "lol", true),
            Arguments.of(avlTreeOf(Pair("kek", "lol")), "kukech", false),
            Arguments.of(avlTreeOf<Int, Any>(), 0, false),
            Arguments.of(avlTreeOf(Pair(1, 2), Pair(2, 3)), 3, true),
            Arguments.of(avlTreeOf(Pair(1, 2), Pair(2, 3)), 0, false),
        )

        @JvmStatic
        fun getGetTestData() = listOf(
            Arguments.of(avlTreeOf(Pair(1, 2)), 2, null),
            Arguments.of(avlTreeOf(Pair(1, 2)), 1, 2),
            Arguments.of(avlTreeOf<Int, Any>(), 0, null),
            Arguments.of(avlTreeOf(Pair(1, 2), Pair(2, 3)), 3, null),
            Arguments.of(avlTreeOf(Pair(1, 2), Pair(2, 3)), 1, 2),
            Arguments.of(avlTreeOf(Pair(1, 2), Pair(2, 3)), 2, 3),
        )

        @JvmStatic
        fun isEmptyGetTestData() = listOf(
            Arguments.of(avlTreeOf<Int, Any>(), true),
            Arguments.of(avlTreeOf(Pair(1, 2)), false),
            Arguments.of(avlTreeOf(Pair(1, 2), Pair(2, 3)), false),
        )

        @JvmStatic
        fun getEntriesGetTestData() = listOf(
            Arguments.of(avlTreeOf<Int, Any>(), setOf<Entry<Int, Any>>()),
            Arguments.of(avlTreeOf(Pair(1, 2)), setOf(Entry(1, 2))),
            Arguments.of(avlTreeOf(Pair(1, 2), Pair(2, 3)), setOf(Entry(1, 2), Entry(2, 3))),
        )

        @JvmStatic
        fun getKeysGetTestData() = listOf(
            Arguments.of(avlTreeOf<Int, Any>(), setOf<Entry<Int, Any>>()),
            Arguments.of(avlTreeOf(Pair(1, 2)), setOf(1)),
            Arguments.of(avlTreeOf(Pair(1, 2), Pair(2, 3)), setOf(1, 2)),
        )

        @JvmStatic
        fun getValuesGetTestData() = listOf(
            Arguments.of(avlTreeOf<Int, Any>(), emptyList<Any>()),
            Arguments.of(avlTreeOf(Pair(1, 2)), listOf(2)),
            Arguments.of(avlTreeOf(Pair(1, 2), Pair(2, 3)), listOf(2, 3)),
        )

        @JvmStatic
        fun clearGetTestData() = listOf(
            Arguments.of(avlTreeOf<Int, Any>()),
            Arguments.of(avlTreeOf(Pair(1, 2))),
            Arguments.of(avlTreeOf(Pair("lol", 2), Pair("kek", 3))),
        )

        @JvmStatic
        fun putGetTestData() = listOf(
            Arguments.of(avlTreeOf<Int, Any>(), Entry(1, 0), avlTreeOf(Pair(1, 0))),
            Arguments.of(avlTreeOf(Pair(1, 2)), Entry(2, 3), avlTreeOf(Pair(1, 2), Pair(2, 3))),
            Arguments.of(
                avlTreeOf(Pair("lol", 2), Pair("kek", 3)),
                Entry("chmuk", 4),
                avlTreeOf(Pair("lol", 2), Pair("kek", 3), Pair("chmuk", 4)),
            )
        )

        @JvmStatic
        fun putAllGetTestData() = listOf(
            Arguments.of(avlTreeOf<Int, Any>(), mapOf(Pair(1, 0)), avlTreeOf(Pair(1, 0))),
            Arguments.of(avlTreeOf<Int, Any>(), mapOf(Pair(1, 0), Pair(2, 1)), avlTreeOf(Pair(1, 0), Pair(2, 1))),
            Arguments.of(
                avlTreeOf(Pair(1, 2)),
                mapOf(Pair(2, 0), Pair(3, 4)),
                avlTreeOf(Pair(1, 2), Pair(2, 0), Pair(3, 4))
            ),
            Arguments.of(
                avlTreeOf(Pair("lol", 2), Pair("kek", 3)),
                mapOf(Pair("chmuk", 4), Pair("kukeck", 12)),
                avlTreeOf(Pair("lol", 2), Pair("kek", 3), Pair("chmuk", 4), Pair("kukeck", 12)),
            )
        )

        @JvmStatic
        fun removeGetTestData() = listOf(
            Arguments.of(
                avlTreeOf(Pair("lol", 2), Pair("kek", 3), Pair("chmuk", 4), Pair("kukeck", 12)),
                "lol",
                Pair(avlTreeOf(Pair("kek", 3), Pair("chmuk", 4), Pair("kukeck", 12)), 2)
            ),
            Arguments.of(avlTreeOf<Int, Int>(), 1, Pair(avlTreeOf<Int, Int>(), null)),
            Arguments.of(avlTreeOf(Pair(1, 2)), 1, Pair(avlTreeOf<Int, Int>(), 2))
        )
    }
}
