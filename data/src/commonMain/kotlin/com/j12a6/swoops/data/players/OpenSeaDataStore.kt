package com.j12a6.swoops.data.players

import com.j12a6.swoops.data.PathProvider
import io.ktor.client.HttpClient
import io.ktor.client.request.headers
import io.ktor.client.request.request
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpMethod

class OpenSeaDataStore(private val pathProvider: PathProvider) {

    suspend fun getPlayerOffers() {
        val httpClient = HttpClient()
        val response = httpClient.request("https://api.opensea.io/v2/offers/collection/swoops") {
            method = HttpMethod.Get
            headers {
                append("accept", "application/json")
                append("X-API-KEY", "bbcc5d4a98e7439eb541fa4a5dd51db1")
            }
        }
    }

    suspend fun getPlayers(cursor: String): String {
        val httpClient = HttpClient()
        val url = if (cursor.isNotEmpty()) {
            "https://api.opensea.io/api/v1/assets?collection_slug=swoops&order_direction=desc&limit=200&include_orders=false&cursor=$cursor"
        } else {
            "https://api.opensea.io/api/v1/assets?collection_slug=swoops&order_direction=desc&limit=200&include_orders=false"
        }
        val response = httpClient.request(url) {
            method = HttpMethod.Get
            headers {
                append("accept", "application/json")
                append("X-API-KEY", "bbcc5d4a98e7439eb541fa4a5dd51db1")
            }
        }
        return response.bodyAsText()
    }
}
