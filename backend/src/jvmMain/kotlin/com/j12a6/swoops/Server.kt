package com.j12a6.swoops

import com.j12a6.swoops.data.DefaultDataModule
import com.j12a6.swoops.data.provideGameDataStore
import com.j12a6.swoops.domain.DomainModule
import com.j12a6.swoops.domain.games.usecases.FilterGamesOnDateUseCase
import com.j12a6.swoops.presentation.PlayerStatsUi
import com.j12a6.swoops.presentation.PlayerStatsUiMapper
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.Instant
import kotlinx.html.HTML
import kotlinx.html.body
import kotlinx.html.div
import kotlinx.html.head
import kotlinx.html.id
import kotlinx.html.script
import kotlinx.html.title
import java.io.File


val dataModule = DefaultDataModule()
val domainModule = DomainModule(dataModule)

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
    runBlocking {
//        fetchGames()
//        findWrongFiles()
//        checkMissingGames()
//        removeGames()
//        stuff()
        showPlayerStats()
    }
    //embeddedServer(Netty, port = 8080, host = "127.0.0.1", module = Application::app).start(wait = true)
}

suspend fun fetchGames() {
    val fetchGamesUseCase = domainModule.provideFetchGamesUseCase()
    fetchGamesUseCase.invoke()
//    val idsToFetch = listOf(1)
//    val fetchGamesByIdsUseCase = provideFetchGamesByIdsUseCase()
//    fetchGamesByIdsUseCase.invoke(idsToFetch)
}

suspend fun findWrongFiles() {
    val gameDataStore = provideGameDataStore()
    val gameIdsToExclude = gameDataStore.getGamesIdsToExclude()
    val getGamesUseCase = domainModule.provideGetGamesUseCase()
    val games = getGamesUseCase.invoke()
    println("games: ${games.count()}")
    println("gameIdsToExclude: ${gameIdsToExclude.count()}")
    println("total: ${games.count() + gameIdsToExclude.count()}")
}

fun removeGames() {
    val toDelete = listOf(1)
    val gameDataStore = provideGameDataStore()
    toDelete.forEach { gameId ->
        val filePath = gameDataStore.getGameFilePath(gameId)
        File(filePath).delete()
    }
}

fun checkMissingGames() {
    val gameDataStore = provideGameDataStore()
    val gameIdsToExclude = gameDataStore.getGamesIdsToExclude()
    val storedGamesIds = gameDataStore.getGameIds().toSet()
    val gameIdsWithError = storedGamesIds.mapNotNull { gameId ->
        val filePath = gameDataStore.getGameFilePath(gameId)
        val content = File(filePath).readText()
        if (!content.contains(""""status":"COMPLETE"""")) gameId else null
    }
    val gamesThatShouldNotBeStored = gameIdsWithError.intersect(gameIdsToExclude)
    println("storedGamesIds: ${storedGamesIds.count()}")
    println("gameIdsToExclude: ${gameIdsToExclude.count()}")
    println("total: ${storedGamesIds.count() + gameIdsToExclude.count()}")
    println("gameIdsWithError: ${gameIdsWithError.count()}")
    println("gamesThatShouldNotBeStored: $gamesThatShouldNotBeStored")
    val unstoredGamesThatShouldBeStored = (1..144964).minus(storedGamesIds)
    println(
        "unstoredGamesThatShouldBeStore: ${
            unstoredGamesThatShouldBeStored.minus(
                gameIdsToExclude
            )
        }"
    )
    println(
        "total: ${
            storedGamesIds.count() + gameIdsToExclude.count() + unstoredGamesThatShouldBeStored.minus(
                gameIdsToExclude
            ).count()
        }"
    )
}

suspend fun showPlayerStats() {
//    val viewModel = providePlayerStatsViewModel()
//    val playerStats = viewModel.start(listOf(622, 1467, 1066, 496, 710, 937, 457, 496, ))
//    val playerStats = viewModel.start(listOf(155, 66, 582))
//    playerStats.forEach {
//        println(it)
//    }
}

suspend fun stuff() {

    val getGamesUseCase = domainModule.provideGetGamesUseCase()
    val getGamesByIdsUseCase = domainModule.provideGetGamesByIdsUseCase()
    val filterGamesOnPlayersUseCase = domainModule.provideFilterGamesOnPlayersUseCase()
    val filterGamesOnTeamMatchUpsUseCase = domainModule.provideFilterGamesOnTeamMatchUpsUseCase()
    val filterGamesOnFreeAgentsUseCase = domainModule.provideFilterGamesOnFreeAgentsUseCase()
    val filterGamesOnDateUseCase = domainModule.provideFilterGamesOnDateUseCase()

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
//        val playerIds = listOf(999)
//        val playerName = "SWOOPSTER-847"

    val isWithoutFreeAgents = true
    // "2023-03-03T23:30:00.000000Z", this date indicates after configuration 1 with 999
    val date = "2023-03-03T23:30:00.000000Z"

    val games = getGamesUseCase.invoke()
//    val games = getGamesUseCaseByIds.invoke()
        .let { if (isWithoutFreeAgents) filterGamesOnFreeAgentsUseCase.execute(it) else it }
        .let { filterGamesOnPlayersUseCase.invoke(it, playerIds) }
        .let {
            if (date != null) {
                filterGamesOnDateUseCase.invoke(
                    it,
                    Instant.parse(date),
                    FilterGamesOnDateUseCase.Moment.After
                )
            } else {
                it
            }
        }
//        .let { game -> game.filter { it.results.playersBoxScore[582]?.position == 4 } }
//        .let { filterGamesOnTeamMatchUpsUseCase.invoke(it, setOf(95, 22)) }
        .toList()

//        val comparePlayersStatsViewModel = provideComparePlayersStatsViewModel()
//        val playersToCompare = comparePlayersStatsViewModel.start(585, listOf(584, 66))

//        val teamOppositionsViewModel = provideTeamOppositionsViewModel()

//        println("games count: ${games.count()}")
//        println("games Legends wins: ${games.count { it.results.scores[95]!! > it.results.scores[22]!! }}")
//        println("games Legends losses: ${games.count { it.results.scores[95]!! < it.results.scores[22]!! }}")
//        println("games Legends ties: ${games.count { it.results.scores[95]!! == it.results.scores[22]!! }}")
//        println("games ids: ${games.map { it.id }.sortedDescending()}")


    val getPlayerStatsUseCase = domainModule.provideGetPlayerStatsUseCase()
    val playerStats = getPlayerStatsUseCase.invoke(games)

    val playerStatsUi = playerStats.mapValues { PlayerStatsUiMapper().map(it.value) }
        .let {
//                filterOnSpecificStatsUseCase.invoke(it)
            it
        }

    val lambdas = listOf<(PlayerStatsUi) -> Float>(
        { statsUi -> statsUi.fieldGoalsPerGame },
        { statsUi -> statsUi.fieldGoalAttemptsPerGame },
        { statsUi -> statsUi.fieldGoalPercentagePerGame },
        { statsUi -> statsUi.threePointsPerGame },
        { statsUi -> statsUi.threePointAttemptsPerGame },
        { statsUi -> statsUi.threePointPercentagePerGame },
        { statsUi -> statsUi.twoPointsPerGame },
        { statsUi -> statsUi.twoPointAttemptsPerGame },
        { statsUi -> statsUi.twoPointPercentagePerGame },
        { statsUi -> statsUi.freeThrowsPerGame },
        { statsUi -> statsUi.freeThrowAttemptsPerGame },
        { statsUi -> statsUi.freeThrowPercentagePerGame },
        { statsUi -> statsUi.defensiveReboundsPerGame },
        { statsUi -> statsUi.offensiveReboundsPerGame },
        { statsUi -> statsUi.reboundsPerGame },
        { statsUi -> statsUi.assistsPerGame },
        { statsUi -> statsUi.stealsPerGame },
        { statsUi -> statsUi.blocksPerGame },
        { statsUi -> statsUi.foulsPerGame },
        { statsUi -> statsUi.pointsPerGame },
        { statsUi -> statsUi.turnoversPerGame },
        { statsUi -> statsUi.trueShootingPercentage },
    )
//        lambdas.forEachIndexed { index, lambda ->
//            println(index)
//            playerStatsUi
//                .values.sortedByDescending(lambda).subList(0, 10)
//                .forEach {
//                    println(it)
//                }
//            println()
//            println()
//        }

    playerStatsUi
        .filter { playerIds.contains(it.key.id) }
        .forEach {
            println(it)
        }

//        games.forEach { game ->
//            game.lineups.values.forEach { println(it.teamId) }
//            println()
//        }
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
