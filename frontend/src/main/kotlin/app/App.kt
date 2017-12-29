package app

import com.iwings.fly.TwitterPostDto
import com.iwings.fly.Wall
import logo.logo
import react.*
import react.dom.div
import react.dom.h1
import react.dom.h2
import react.dom.p
import ticker.ticker
import utils.getAndParseResult
import utils.launch
import kotlin.js.JSON
import kotlinx.serialization.json.JSON as KJSON

class App() : RComponent<RProps, MainState>() {

    override fun componentDidMount() {
        launch {
            getAndParseResult("/all", null) {

                val newWall = (it as Array<dynamic>)
                        .map { JSON.stringify(it) }
                        .foldRight(ArrayList<Wall>()) { y: String, x: ArrayList<Wall> ->
                            x.add(KJSON.parse<Wall>(y))
                            x
                        }

                setState {
                    wall = newWall.toTypedArray()
                }
            }
        }
    }

    override fun RBuilder.render() {
        div("App-header") {
            logo()
            h2 {
                +"Welcome to React with Kotlin"
            }
        }
        h1 {
            div {
                for (wall: Wall in state.wall) {
                    p {
                        +"${wall.author}: ${wall.tweets.foldRight("") { t: TwitterPostDto, s: String -> s + t.text + "; " }}"
                    }
                }
            }
        }
        p("App-ticker") {
            ticker()
        }
    }

    init {
        state.wall = emptyArray()
    }
}

fun RBuilder.app() = child(App::class) {}

external interface MainState : RState {
    var wall: Array<Wall>
}