package com.ymotpyrc.swoops.data.api

import com.ymotpyrc.swoops.domain.players.usecases.GameId
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.retry
import io.ktor.client.request.header
import io.ktor.client.request.request
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpMethod

class SwoopsApi {

    private val client = HttpClient(CIO) {
        install(HttpTimeout) {
            requestTimeoutMillis = 60000
        }
    }

    suspend fun getGame(gameId: GameId): String {
        return client.request("https://api.playswoops.com/api/game/${gameId.id}/") {
            method = HttpMethod.Get
            header("accept", "application/json")
            retry {
                constantDelay(3000)
                maxRetries = 3
            }
        }.bodyAsText()
    }

    suspend fun fetchOpenedGames(): String {
        return client.request("https://api.playswoops.com/api/game/") {
            method = HttpMethod.Get
            header("accept", "application/json")
            retry {
                constantDelay(3000)
                maxRetries = 3
            }
        }.bodyAsText()
    }

    // https://api.playswoops.com/api/baller/585
}
