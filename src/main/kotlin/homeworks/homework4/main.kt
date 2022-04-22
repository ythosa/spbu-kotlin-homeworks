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
import homeworks.homework4.qsort.partitions.LomutoPartition

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
    val l = mutableListOf(3, 1, 4, 2)
    println(l)
    println(LomutoPartition<Int>().apply(l, 0, l.size - 1))
    println(l)
}
