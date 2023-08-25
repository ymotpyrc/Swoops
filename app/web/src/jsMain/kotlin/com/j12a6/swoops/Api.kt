package app.web

import io.ktor.http.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.serialization.kotlinx.json.*


val jsonClient = HttpClient {
    install(ContentNegotiation) {
        json()
    }
}

suspend fun exportPlayersToDb() {
    return jsonClient.get("/exportPlayersToDb").body()
}

suspend fun getPlayers(): List<Player> {
    return jsonClient.get(Player.path).body()
}

suspend fun addPlayer(player: Player) {
    jsonClient.post(Player.path) {
        contentType(ContentType.Application.Json)
        setBody(player)
    }
}

suspend fun getPlayerStats(): Map<PlayerWithPosition, PlayerStats> {
    return jsonClient.get("/playerStats").body()
}

//suspend fun deletePlayer(player: Player) {
//    jsonClient.delete(Player.path + "/${player.id}")
//}