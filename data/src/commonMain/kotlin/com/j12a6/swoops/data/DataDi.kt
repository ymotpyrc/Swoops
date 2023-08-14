package com.j12a6.swoops.data

import com.j12a6.swoops.data.games.GameDataStore
import com.j12a6.swoops.data.players.HtmlDataStore
import com.j12a6.swoops.domain.games.GameRepository
import com.j12a6.swoops.domain.players.PlayerRepository
import com.j12a6.swoops.data.players.mapper.PlayerMapper
import com.j12a6.swoops.data.games.mapper.GameMapper
import com.j12a6.swoops.data.games.GameRepositoryImpl

private val homePath = "${getEnvVariable("HOME")}/"
private val gamesDir = "${homePath}Projects/SwoopsDatabase/swoops/swoops_api/games/"

fun providePathProvider(): PathProvider = PathProvider(
    apiPath = "$homePath/Projects/SwoopsDatabase/swoops/players/",
    gameApiPath = "${gamesDir}content/",
    lastFetchedGameIdFilePath = "${gamesDir}last_fetched_game_id.txt",
    unrecoverableErrorsFilePath = "${gamesDir}unrecoverable_errors.txt",
    recoverableErrorsFilePath = "${gamesDir}recoverable_errors.txt",
    localDbPath = "$homePath/Projects/Swoops/frontend/instance/front.sqlite",
)

val swoopsApi: SwoopsApi by lazy { SwoopsApi() }
fun provideSwoopsApi(): SwoopsApi = swoopsApi

fun provideApiDatastore(): ApiDataStore = ApiDataStore(providePathProvider())

fun provideHtmlDataStore(): HtmlDataStore = HtmlDataStore(providePathProvider())

fun provideLocalDbDataStore(): LocalDbDataStore = LocalDbDataStore(providePathProvider())

fun provideGameDataStore(): GameDataStore = GameDataStore(provideSwoopsApi(), providePathProvider())

val playerRepository: PlayerRepository by lazy {
    PlayerRepositoryImpl(
        provideApiDatastore(),
        provideHtmlDataStore(),
        provideLocalDbDataStore(),
        PlayerMapper(),
    )
}

fun providingPlayerRepository(): PlayerRepository = playerRepository

val gameRepository: GameRepository by lazy { GameRepositoryImpl(provideGameDataStore(), provideGameMapper()) }
fun providingGameRepository(): GameRepository = gameRepository

fun provideGameMapper(): GameMapper = GameMapper()
