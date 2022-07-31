import androidx.compose.runtime.*
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.renderComposable

fun main() {
    var count: Int by mutableStateOf(0)

    renderComposable(rootElementId = "root") {
        Style(AppStylesheet)
        Div(
            attrs = {
                classes(AppStylesheet.container)
            }
        ) {
            Button(attrs = {
                onClick { count -= 1 }
            }) {
                Text("--")
            }

            Span({ style { padding(15.px) } }) {
                Text("$count")
            }

            Button(attrs = {
                onClick { count += 1 }
            }) {
                Text("++")
            }

            InputTest()

            EventsTest()
        }
    }
}

@Composable
fun EventsTest() {
    Button(
        attrs = {
            onClick { wrappedMouseEvent ->
                // wrappedMouseEvent is of `WrappedMouseEvent` type
                println("button clicked at ${wrappedMouseEvent.movementX}, ${wrappedMouseEvent.movementY}")

                val nativeEvent = wrappedMouseEvent.nativeEvent
            }
        }
    ) {
        Text("Button")
    }

    val text = remember { mutableStateOf("") }

    TextArea(
        value = text.value,
        attrs = {
            onInput {
                text.value = it.value
            }
        }
    )

    Span {
        Text("Typed text = ${text.value}")
    }
}

object AppStylesheet : StyleSheet() {
    val container by style { // container is a class
        display(DisplayStyle.Flex)
        padding(20.px)
        backgroundColor(Color.blue)

        // custom property (or not supported out of a box)
        property("font-family", "Arial, Helvetica, sans-serif")
    }
}

