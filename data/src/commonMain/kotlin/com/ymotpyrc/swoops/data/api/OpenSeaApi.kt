package com.ymotpyrc.swoops.data.api

import io.ktor.client.HttpClient
import io.ktor.client.request.headers
import io.ktor.client.request.request
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpMethod

class OpenSeaApi {

    suspend fun fetchPlayers(cursor: String): String {
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
            }
        }
        return response.bodyAsText()
    }

    suspend fun fetchPlayerOffers() {
        val httpClient = HttpClient()
        val response = httpClient.request("https://api.opensea.io/v2/offers/collection/swoops") {
            method = HttpMethod.Get
            headers {
                append("accept", "application/json")
            }
        }
    }
}
