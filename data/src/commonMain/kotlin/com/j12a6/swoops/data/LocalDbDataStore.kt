package com.j12a6.swoops.data

import com.j12a6.swoops.domain.players.models.Player

//val client = KMongo.createClient().coroutine
//val database = client.getDatabase("players")
//val collection = database.getCollection<Player>()

val playersInDb = mutableListOf<Player>()

class LocalDbDataStore(val pathProvider: PathProvider) {

//    private val collection: CoroutineCollection<Player>

    init {
//        val connectionString: ConnectionString? = System.getenv("MONGODB_URI")?.let {
//            ConnectionString("$it?retryWrites=false")
//        }
//        val client = if (connectionString != null) {
//            KMongo.createClient(connectionString).coroutine
//        } else {
//            KMongo.createClient().coroutine
//        }
//        val database = client.getDatabase(connectionString?.database ?: "players")
//        val client = KMongo.createClient().coroutine
//        val database = client.getDatabase("players")
//        collection = database.getCollection()
    }

    suspend fun getPlayers(): List<Player> {
//        return collection.find().toList()
//        return playersInDb
        return emptyList()
    }

    suspend fun savePlayers(players: List<Player>) {
//        collection.insertMany(players.toList())
//        playersInDb.clear()
//        playersInDb.addAll(players)
    }
}
