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
import homeworks.homework4.gen.RandomListGenerator
import homeworks.homework4.qsort.QSortCoroutines
import homeworks.homework4.qsort.QSortSequential
import homeworks.homework4.qsort.QSortThreadPool
import homeworks.homework4.qsort.partitions.LomutoPartition
import java.util.concurrent.ForkJoinPool
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

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

@ExperimentalTime
infix fun String.time(function: () -> Unit) {
    println("> Measuring time of $this")
    val t = measureTime(function)
    println("< Result: $t")
}

@OptIn(ExperimentalTime::class)
fun main() {
    List<Int>(-1) {it}

    val generator = RandomListGenerator.build {
        minValue = 0
        maxValue = 1000
        elementsCount = 5_000_000
    }

    val qsc = QSortCoroutines<Int>(LomutoPartition())
    val qst = QSortThreadPool<Int>(LomutoPartition(), ForkJoinPool())
    val qss = QSortSequential<Int>(LomutoPartition())

    var l = generator.generate().toMutableList()
    val cpy = listOf(*l.toTypedArray())
    "ksort" time {
        l.sort()
    }

    l = mutableListOf(*cpy.toTypedArray())
    "qsc" time {
        qsc.sorted(l)
    }

    l = mutableListOf(*cpy.toTypedArray())
    "qst" time {
        qst.sorted(l)
    }

    l = mutableListOf(*cpy.toTypedArray())
    "qss" time {
        qss.sorted(l)
    }
}
