import app.webApp.getPlayers
import csstype.px
import csstype.rgb
import emotion.react.css
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import model.Player
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.p
import react.dom.html.ReactHTML.ul
import react.useEffectOnce
import react.useState

private val scope = MainScope()


val App = FC<Props> {

    var players by useState(emptyList<Player>())

    useEffectOnce {
        scope.launch {
            exportPlayersToDb()
            players = getPlayers()
        }
    }

    div {
        css {
            padding = 5.px
            backgroundColor = rgb(8, 97, 22)
            color = rgb(56, 246, 137)
        }
        ul {
            players.forEach { player ->
                li { +"$player" }
            }
        }
    }
//    inputComponent {
//        onSubmit = { input ->
//            val player = Player(
//                number = "",
//                prospect = 3,
//                season = 4,
//                position = "C",
//                skills = mapOf(),
//                topSkills = mapOf()
//            )
//            scope.launch {
//                addPlayer(player)
//                players = getPlayers()
//            }
//        }
//    }
}
