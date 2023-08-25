package com.ymotpyrc.swoops.data

import com.ymotpyrc.swoops.data.players.models.TraitDto

expect fun readTextFromFile(filePath: String): String
expect fun readLinesFromFile(filePath: String): List<String>
expect fun writeTextToFile(filePath: String, text: String)
expect fun appendTextToFile(filePath: String, text: String, onNewLine: Boolean)
expect fun listFilesInFolder(folderPath: String): Sequence<String>
expect inline fun <reified T> loadJsonFromApiFile(filePath: String): T
expect fun getTraitsFromFilePath(filePath: String): List<TraitDto>