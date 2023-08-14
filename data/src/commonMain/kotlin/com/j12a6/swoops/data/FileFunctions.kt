package com.j12a6.swoops.data

import com.j12a6.swoops.data.players.model.TraitDto

expect fun readTextFromFile(filePath: String): String
expect fun readLinesFromFile(filePath: String): List<String>
expect fun writeTextToFile(filePath: String, text: String)
expect fun appendTextToFile(filePath: String, text: String, onNewLine: Boolean)
expect fun listFilesInFolder(folderPath: String): Sequence<String>
expect inline fun <reified T> loadJsonFromApiFile(filePath: String): T
expect fun getTraitsFromFilePath(filePath: String): List<TraitDto>