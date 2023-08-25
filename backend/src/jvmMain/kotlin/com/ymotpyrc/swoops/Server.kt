package com.ymotpyrc.swoops

import com.ymotpyrc.swoops.data.DefaultDataModule
import com.ymotpyrc.swoops.domain.DomainModule
import kotlinx.html.HTML
import kotlinx.html.body
import kotlinx.html.div
import kotlinx.html.head
import kotlinx.html.id
import kotlinx.html.script
import kotlinx.html.title

val dataModule = DefaultDataModule()
val domain = DomainModule(dataModule)

fun HTML.index() {
    head {
        title("Hello from Ktor!")
    }
    body {
        div {
            +"Hello from Ktor"
        }
        div {
            id = "root"
        }
        script(src = "/static/Swoops.js") {}
    }
}

fun main() {
    //embeddedServer(Netty, port = 8080, host = "127.0.0.1", module = Application::app).start(wait = true)
}

suspend fun stuff() {
//        val playerIds = listOf(584, 585, 66, 582, 844, 999) // My players
    val playerIds = setOf(
        861,
        1151,
        834, // North Side 416ers
        1066,
        1029,
        867,
        73,
        1130, // Metacourt Miners
        736,
        200,
        1450,
        543,
        927,
        1426,
        760,
        411,
        792,
        1091,
        1383,
        467, // that are opened in my navigator
        12,
        929,
        762,
        94,
        1464,
        952,
        1494,
        688,
        817,
        1323,
        726,
        950, // that are opened in my navigator
        554,
        829,
        441,
        348,
        62,
        937,
        110,
        1231, // that are opened in my navigator
        219,
        474,
        669,
        725,
        1342,
        322,
        399,
        92,
        156,
        1389,
        724,
        139,
        61,
        523, // other at 1 point in time
        1319,
        565,
        1405,
        1386,
        510,
        1393,
        528,
        354,
        225,
        306,
        644,
        670,
        1108, // other at 1 point in time
        921,
        1198,
        524,
        296,
        1163,
        1161, // other at 1 point in time
        622, // just saw listed
        // chef kitchens and North sidde players are available to sell, search ids
    ).toList()
}


//fun Application.app() {
//    install(ContentNegotiation) {
//        json()
//    }
//    install(CORS) {
//        allowMethod(HttpMethod.Get)
//        allowMethod(HttpMethod.Post)
//        allowMethod(HttpMethod.Delete)
//        anyHost()
//    }
//    install(Compression) {
//        gzip()
//    }
//    routing {
//        get("/") {
//            call.respondHtml(HttpStatusCode.OK, HTML::index)
//        }
//        static("/static") {
//            resources()
//        }
//        route(Player.path) {
//            get {
//                val getPlayersUseCase = provideGetPlayersUseCase()
//                val players = getPlayersUseCase.invoke()
//                val playerNumbers = listOf("585")
//                val filterOnPlayerNumbersUseCase = provideFilterOnPlayerNumbersUseCase()
//                val filteredPlayers = filterOnPlayerNumbersUseCase.invoke(players, playerNumbers)
//                call.respond(filteredPlayers)
//            }
//            post {
////                collection.insertMany(call.receive<List<Player>>())
//                call.respond(HttpStatusCode.OK)
//            }
//        }
//        route("/exportPlayersToDb") {
//            get {
//                val exportPlayersToDbUseCase = provideExportPlayersToDbUseCase()
//                exportPlayersToDbUseCase.invoke()
//                call.respond(HttpStatusCode.OK)
//            }
//        }
//        route("/playerStats") {
//            get {
//                val viewModel = providePlayerStatsViewModel()
//                val playerStats = viewModel.start(listOf(12))
//                call.respond(HttpStatusCode.OK)
//            }
//        }
//    }
//}
