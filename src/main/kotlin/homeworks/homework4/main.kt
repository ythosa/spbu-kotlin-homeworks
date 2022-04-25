package homeworks.homework4

import homeworks.homework4.bench.QSortBenchmark
import homeworks.homework4.gen.RandomListOfIntsGenerator
import homeworks.homework4.qsort.QSort
import homeworks.homework4.qsort.QSortCoroutines
import homeworks.homework4.qsort.QSortSequential
import homeworks.homework4.qsort.QSortThreadPool
import homeworks.homework4.qsort.partitions.DutchFlagPartition
import homeworks.homework4.qsort.partitions.HoarePartition
import homeworks.homework4.qsort.partitions.LomutoPartition
import homeworks.homework4.qsort.partitions.Partition
import homeworks.homework4.ui.GraphParametersModel
import homeworks.homework4.ui.QSorts
import javafx.application.Platform
import jetbrains.datalore.base.registration.Disposable
import jetbrains.datalore.plot.MonolithicCommon
import jetbrains.datalore.vis.swing.jfx.DefaultPlotPanelJfx
import jetbrains.letsPlot.export.ggsave
import jetbrains.letsPlot.geom.geomLine
import jetbrains.letsPlot.geom.geomPoint
import jetbrains.letsPlot.geom.geomSmooth
import jetbrains.letsPlot.ggplot
import jetbrains.letsPlot.ggsize
import jetbrains.letsPlot.intern.Plot
import jetbrains.letsPlot.intern.toSpec
import kotlinx.coroutines.asCoroutineDispatcher
import java.awt.Checkbox
import java.awt.Component
import java.awt.Dimension
import java.awt.GridLayout
import java.awt.event.ItemEvent
import java.util.concurrent.ForkJoinPool
import javax.swing.*
import javax.swing.WindowConstants.EXIT_ON_CLOSE
import kotlin.time.Duration
import kotlin.time.DurationUnit

//enum class Partitions(val partition: Partition<Int>) {
//    LOMUTO(LomutoPartition()),
//    HOARE(HoarePartition()),
//    DUTCH_FLAG(DutchFlagPartition())
//}
//
//data class GState(
//    val partitions: MutableSet<Partitions> = mutableSetOf()
//)
//
//val gstate = GState()
//
//infix fun Box.of(components: List<Component>): Box {
//    return this.apply { components.forEach { add(it) } }
//}
//
//class GraphVisualizer : JFrame() {
//    init {
//        createUI()
//    }
//
//    private fun createUI() {
//        title = "Graphs"
//        defaultCloseOperation = EXIT_ON_CLOSE
//        setSize(300, 200)
//        setLocationRelativeTo(null)
//
//        add(createPartitionsPanel())
//    }
//
//
//    private fun createPartitionsPanel() = Box.createHorizontalBox() of (Partitions.values().map {
//        Checkbox(it.name).apply {
//            addItemListener { event ->
//                when (event.stateChange) {
//                    ItemEvent.SELECTED -> gstate.partitions.add(Partitions.valueOf(event.item.toString()))
//                    else -> gstate.partitions.remove(Partitions.valueOf(event.item.toString()))
//                }
//            }
//        }
//    })
//}
//
//fun main4() {
//    val gv = GraphVisualizer()
//    gv.isVisible = true
//}

fun main() {
    val gpm = GraphParametersModel<Int>()
    gpm.addPartition(homeworks.homework4.ui.Partitions.LOMUTO)
    gpm.addQSort(QSorts.THREAD_POOL, (4..16 step 100).toList())

    val data = getDataFrame(gpm.getQSorts())
    val plots = mapOf(
        "Default" to ggplot(data) { y = "durations"; x = "elementsCount"; color = "names" } +
                ggsize(2000, 1500) +
                geomPoint() +
                geomLine(),
        "With smooth" to ggplot(data) { y = "durations"; x = "elementsCount"; color = "names" } +
                ggsize(2000, 1500) +
                geomPoint() +
                geomLine() + geomSmooth(),
    )

    val selectedPlotKey = plots.keys.first()
    val controller = Controller(
        plots,
        selectedPlotKey,
        false
    )

    val window = JFrame("Ерина мать трахал")
    window.defaultCloseOperation = EXIT_ON_CLOSE
    window.contentPane.layout = BoxLayout(window.contentPane, BoxLayout.Y_AXIS)

    // Add controls
    val controlsPanel = Box.createHorizontalBox().apply {
        // Plot selector
        val plotButtonGroup = ButtonGroup()
        for (key in plots.keys) {
            plotButtonGroup.add(
                JRadioButton(key, key == selectedPlotKey).apply {
                    addActionListener {
                        controller.plotKey = this.text
                    }
                }
            )
        }

        this.add(Box.createHorizontalBox().apply {
            border = BorderFactory.createTitledBorder("Plot")
            for (elem in plotButtonGroup.elements) {
                add(elem)
            }
        })

        // Preserve aspect ratio selector
        val aspectRadioButtonGroup = ButtonGroup()
        aspectRadioButtonGroup.add(JRadioButton("Original", false).apply {
            addActionListener {
                controller.preserveAspectRadio = true
            }
        })
        aspectRadioButtonGroup.add(JRadioButton("Fit container", true).apply {
            addActionListener {
                controller.preserveAspectRadio = false
            }
        })

        this.add(Box.createHorizontalBox().apply {
            border = BorderFactory.createTitledBorder("Aspect ratio")
            for (elem in aspectRadioButtonGroup.elements) {
                add(elem)
            }
        })
    }
    window.contentPane.add(controlsPanel)

    // Add plot panel
    val plotContainerPanel = JPanel(GridLayout())
    window.contentPane.add(plotContainerPanel)
    controller.plotContainerPanel = plotContainerPanel
    controller.rebuildPlotComponent()

    SwingUtilities.invokeLater {
        window.pack()
        window.size = Dimension(850, 400)
        window.setLocationRelativeTo(null)
        window.isVisible = true
    }
}

private class Controller(
    private val plots: Map<String, Plot>,
    initialPlotKey: String,
    initialPreserveAspectRadio: Boolean
) {
    var plotContainerPanel: JPanel? = null

    var plotKey: String = initialPlotKey
        set(value) {
            field = value
            rebuildPlotComponent()
        }
    var preserveAspectRadio: Boolean = initialPreserveAspectRadio
        set(value) {
            field = value
            rebuildPlotComponent()
        }

    fun rebuildPlotComponent() {
        plotContainerPanel?.let {
            val container = plotContainerPanel!!
            // cleanup
            for (component in container.components) {
                if (component is Disposable) {
                    component.dispose()
                }
            }
            container.removeAll()

            // build
            container.add(createPlotPanel())
            container.parent?.revalidate()
        }
    }

    fun createPlotPanel(): JPanel {
        // Make sure JavaFX event thread won't get killed after JFXPanel is destroyed.
        Platform.setImplicitExit(false)

        val rawSpec = plots[plotKey]!!.toSpec()
        val processedSpec = MonolithicCommon.processRawSpecs(rawSpec, frontendOnly = false)

        return DefaultPlotPanelJfx(
            processedSpec = processedSpec,
            preserveAspectRatio = preserveAspectRadio,
            preferredSizeFromPlot = false,
            repaintDelay = 10,
        ) { messages ->
            for (message in messages) {
                println("[Example App] $message")
            }
        }
    }
}

//fun main3() {
//    val gpm = GraphParametersModel<Int>()
//    gpm.addPartition(homeworks.homework4.ui.Partitions.LOMUTO)
////    gpm.addPartition(homeworks.homework4.ui.Partitions.HOARE)
////    gpm.addQSort(QSorts.SEQUENTIAL, emptyList())
//    gpm.addQSort(QSorts.THREAD_POOL, (4..16 step 4).toList())
//
//    val p = ggplot(getDataFrame(gpm.getQSorts())) { y = "durations"; x = "elementsCount"; color = "names" } +
//            ggsize(2000, 1500) +
//            geomPoint() +
//            geomLine()
//    //            geomSmooth()
//
//    ggsave(p, "density.png")
//}

data class Bench(val name: String, val elementsCount: Int, val duration: Duration)

fun getDataFrame(qSorts: Map<String, QSort<Int>>): MutableMap<String, Any> {
    val data = mutableListOf<Bench>()

    val from = 1_000_000
    val to = 10_000_000
    val step = 1_000_000

    for (count in from..to step step) {
        val generator = RandomListOfIntsGenerator.build {
            minValue = 0
            maxValue = 10_000_000
            elementsCount = count
        }
        val benchmark = QSortBenchmark(3, 2, generator)

        data.addAll(benchmark.benchAll(qSorts).map { Bench(it.key, count, it.value) })
    }
    data.sortBy { it.duration }

    val result = mutableMapOf<String, Any>()

    result["names"] = data.map { it.name }
    result["durations"] = data.map { it.duration.toInt(DurationUnit.MILLISECONDS) }
    result["elementsCount"] = data.map { it.elementsCount }

    return result
}

//fun main11() {
//    val generator = RandomListOfIntsGenerator.build {
//        minValue = 0
//        maxValue = 10_000_000
//        elementsCount = 5_000_000
//    }
//    val benchmark = QSortBenchmark(3, 5, generator)
//    val qSorts = mapOf<String, QSort<Int>>(
//        "coroutines with Lomuto" to QSortCoroutines(LomutoPartition(), ForkJoinPool().asCoroutineDispatcher()),
//        "coroutines with Hoare" to QSortCoroutines(HoarePartition(), ForkJoinPool().asCoroutineDispatcher()),
//        "coroutines with DutchFlag" to QSortCoroutines(DutchFlagPartition(), ForkJoinPool().asCoroutineDispatcher()),
//        "thread pool with Lomuto" to QSortThreadPool(LomutoPartition(), ForkJoinPool()),
//        "thread pool with Hoare" to QSortThreadPool(HoarePartition(), ForkJoinPool()),
//        "thread pool with DutchFlag" to QSortThreadPool(DutchFlagPartition(), ForkJoinPool()),
//        "single thread with Lomuto" to QSortSequential(LomutoPartition()),
//        "single thread with Hoare" to QSortSequential(HoarePartition()),
//        "single thread with DutchFlag" to QSortSequential(DutchFlagPartition()),
//    )
//
//    val benchmarkResult = benchmark.benchAll(qSorts)
//
//    for ((name, duration) in benchmarkResult) {
//        println("$name: $duration")
//    }
//}
