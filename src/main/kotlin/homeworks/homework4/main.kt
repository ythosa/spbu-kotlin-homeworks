package homeworks.homework4

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import homeworks.homework4.bench.QSortBenchmark
import homeworks.homework4.gen.RandomListOfIntsGenerator
import homeworks.homework4.qsort.QSort
import homeworks.homework4.qsort.QSortCoroutines
import homeworks.homework4.qsort.QSortSequential
import homeworks.homework4.qsort.QSortThreadPool
import homeworks.homework4.qsort.partitions.DutchFlagPartition
import homeworks.homework4.qsort.partitions.HoarePartition
import homeworks.homework4.qsort.partitions.LomutoPartition
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.asCoroutineDispatcher
import java.util.concurrent.ForkJoinPool

@Composable
@Preview
fun App() {
    val count = remember { mutableStateOf(0) }
    MaterialTheme {
        Column(Modifier.fillMaxSize(), Arrangement.spacedBy(5.dp)) {
            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = {
                    count.value++
                }
            ) {
                Text(if (count.value == 0) "Hello World" else "Clicked ${count.value}!")
            }
            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = {
                    count.value = 0
                }
            ) {
                Text("Reset")
            }
        }
    }
}

fun main1() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Yay graphs",
        state = rememberWindowState(width = 1080.dp, height = 960.dp)
    ) {
        App()
    }
}

fun main() {
    val generator = RandomListOfIntsGenerator.build {
        minValue = 0
        maxValue = 10_000_000
        elementsCount = 5_000_000
    }
    val benchmark = QSortBenchmark(3, 5, generator)
    val qSorts = mapOf<String, QSort<Int>>(
        "coroutines with Lomuto" to QSortCoroutines(LomutoPartition(), ForkJoinPool().asCoroutineDispatcher()),
        "coroutines with Hoare" to QSortCoroutines(HoarePartition(), ForkJoinPool().asCoroutineDispatcher()),
        "coroutines with DutchFlag" to QSortCoroutines(DutchFlagPartition(), ForkJoinPool().asCoroutineDispatcher()),
        "thread pool with Lomuto" to QSortThreadPool(LomutoPartition(), ForkJoinPool()),
        "thread pool with Hoare" to QSortThreadPool(HoarePartition(), ForkJoinPool()),
        "thread pool with DutchFlag" to QSortThreadPool(DutchFlagPartition(), ForkJoinPool()),
        "single thread with Lomuto" to QSortSequential(LomutoPartition()),
        "single thread with Hoare" to QSortSequential(HoarePartition()),
        "single thread with DutchFlag" to QSortSequential(DutchFlagPartition()),
    )

    val benchmarkResult = benchmark.benchAll(qSorts)

    for ((name, duration) in benchmarkResult) {
        println("$name: $duration")
    }
}
