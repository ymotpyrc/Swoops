package com.ymotpyrc.swoops.data

import com.ymotpyrc.swoops.data.api.OpenSeaApi
import com.ymotpyrc.swoops.data.api.SwoopsApi
import com.ymotpyrc.swoops.data.games.GameDataStore
import com.ymotpyrc.swoops.data.games.GameRepositoryImpl
import com.ymotpyrc.swoops.data.games.mappers.GameMapper
import com.ymotpyrc.swoops.data.players.HtmlDataStore
import com.ymotpyrc.swoops.data.players.PlayerRepositoryImpl
import com.ymotpyrc.swoops.data.players.mappers.PlayerMapper
import com.ymotpyrc.swoops.data.stats.PlayersStatsDataStore
import com.ymotpyrc.swoops.data.stats.PlayersStatsRepositoryImpl
import com.ymotpyrc.swoops.domain.games.GameRepository
import com.ymotpyrc.swoops.domain.players.PlayerRepository
import com.ymotpyrc.swoops.domain.stats.PlayersStatsRepository

private val homePath = "${getEnvVariable("HOME")}/"
private val gamesDir = "${homePath}Projects/SwoopsDatabase/swoops/swoops_api/games/"

fun providePathProvider(): PathProvider = PathProvider(
    apiPath = "$homePath/Projects/SwoopsDatabase/swoops/players/",
    gameApiPath = "${gamesDir}content/",
    lastFetchedGameIdFilePath = "${gamesDir}last_fetched_game_id.txt",
    unrecoverableErrorsFilePath = "${gamesDir}unrecoverable_errors.txt",
    recoverableErrorsFilePath = "${gamesDir}recoverable_errors.txt",
    playerStatsFilePath = "$homePath/Projects/SwoopsDatabase/swoops/playerStats.json",
    playerStatsPercentilesFilePath = "$homePath/Projects/SwoopsDatabase/swoops/playerStatsPercentiles.json",
    localDbPath = "$homePath/Projects/Swoops/frontend/instance/front.sqlite",
)

val swoopsApi: SwoopsApi by lazy { SwoopsApi() }
fun provideSwoopsApi(): SwoopsApi = swoopsApi

val openSeaApi: OpenSeaApi by lazy { OpenSeaApi() }
fun provideOpenSeaApi(): OpenSeaApi = openSeaApi

//fun providePlayerDatastore(): PlayerDataStore =
//    PlayerDataStore(provideOpenSeaApi(), providePathProvider())

fun provideApiDatastore(): ApiDataStore = ApiDataStore(providePathProvider())

fun provideHtmlDataStore(): HtmlDataStore = HtmlDataStore(providePathProvider())

fun provideLocalDbDataStore(): LocalDbDataStore = LocalDbDataStore(providePathProvider())

fun provideGameDataStore(): GameDataStore = GameDataStore(provideSwoopsApi(), providePathProvider())

fun providePlayersStatsGameDataStore(): PlayersStatsDataStore = PlayersStatsDataStore(providePathProvider())

val playerRepository: PlayerRepository by lazy {
    PlayerRepositoryImpl(
//        providePlayerDatastore(),
        provideApiDatastore(),
        provideHtmlDataStore(),
        provideLocalDbDataStore(),
        PlayerMapper(),
    )
}

fun providingPlayerRepository(): PlayerRepository = playerRepository

val gameRepository: GameRepository by lazy {
    GameRepositoryImpl(provideGameDataStore(), provideGameMapper())
}

fun providingGameRepository(): GameRepository = gameRepository

fun provideGameMapper(): GameMapper = GameMapper()

val playersStatsRepository: PlayersStatsRepository by lazy {
    PlayersStatsRepositoryImpl(providePlayersStatsGameDataStore())
}

fun providingPlayersStatsRepository() : PlayersStatsRepository = playersStatsRepository