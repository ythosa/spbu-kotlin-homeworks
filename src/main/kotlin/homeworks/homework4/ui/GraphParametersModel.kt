package homeworks.homework4.ui

import homeworks.homework4.qsort.QSort
import homeworks.homework4.qsort.QSortCoroutines
import homeworks.homework4.qsort.QSortSequential
import homeworks.homework4.qsort.QSortThreadPool
import homeworks.homework4.qsort.partitions.DutchFlagPartition
import homeworks.homework4.qsort.partitions.HoarePartition
import homeworks.homework4.qsort.partitions.LomutoPartition
import homeworks.homework4.qsort.partitions.Partition
import kotlinx.coroutines.asCoroutineDispatcher
import java.util.concurrent.ForkJoinPool
import kotlin.reflect.KClass

enum class Partitions {
    LOMUTO,
    HOARE,
    DUTCH_FLAG
}

enum class QSorts {
    SEQUENTIAL,
    THREAD_POOL,
    COROUTINES
}

infix fun <T, P> List<T>.times(other: List<P>): List<Pair<T, P>> {
    val result = mutableListOf<Pair<T, P>>()

    for (first in this) {
        for (second in other) {
            result.add(first to second)
        }
    }

    return result
}

class GraphParametersModel<T : Comparable<T>> {
    private val partitions = mutableSetOf<Partitions>()
    private val qSorts = mutableSetOf<Pair<QSorts, List<Int>>>()

    fun addPartition(partition: Partitions) {
        partitions.add(partition)
    }

    fun removePartition(partition: Partitions) {
        partitions.remove(partition)
    }

    private fun getPartitions(): List<Partition<T>> = partitions.map {
        when (it) {
            Partitions.LOMUTO -> LomutoPartition()
            Partitions.HOARE -> HoarePartition()
            Partitions.DUTCH_FLAG -> DutchFlagPartition()
        }
    }

    fun addQSort(qSort: QSorts, threadsCount: List<Int>) {
        qSorts.add(Pair(qSort, threadsCount))
    }

    fun removeQSort(qSort: QSorts) {
        qSorts.removeIf { it.first == qSort }
    }

    fun getQSorts(): Map<String, QSort<T>> = qSorts.map {
        when (it.first) {
            QSorts.SEQUENTIAL -> getPartitions().map { partition ->
                qSortName(QSortSequential::class, partition::class) to QSortSequential(partition)
            }
            QSorts.THREAD_POOL -> (getPartitions() times it.second).map { (partition, threadsCount) ->
                qSortName(QSortThreadPool::class, partition::class, threadsCount) to QSortThreadPool(
                    partition,
                    ForkJoinPool(threadsCount)
                )
            }
            QSorts.COROUTINES -> (getPartitions() times it.second).map { (partition, threadsCount) ->
                qSortName(QSortCoroutines::class, partition::class, threadsCount) to QSortCoroutines(
                    partition,
                    ForkJoinPool(threadsCount).asCoroutineDispatcher()
                )
            }
        }
    }.flatten().toMap()

    private fun qSortName(qSort: KClass<*>, partition: KClass<*>, threadsCount: Int? = null): String {
        return "${qSort.simpleName}:${partition.simpleName}${if (threadsCount != null) ":$threadsCount" else ""}"
    }
}
