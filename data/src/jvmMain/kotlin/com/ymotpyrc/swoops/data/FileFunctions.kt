package com.ymotpyrc.swoops.data

import com.ymotpyrc.swoops.data.players.models.TraitDto
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.File

actual fun readTextFromFile(filePath: String): String = File(filePath).readText(Charsets.UTF_8)

actual fun readLinesFromFile(filePath: String): List<String> =
    File(filePath).readLines(Charsets.UTF_8)

actual fun writeTextToFile(filePath: String, text: String) = File(filePath).writeText(text)

actual fun appendTextToFile(filePath: String, text: String, onNewLine: Boolean) =
    File(filePath).appendText(if (onNewLine) "\n$text" else text)

actual fun listFilesInFolder(folderPath: String): Sequence<String> =
    File(folderPath).walkTopDown().maxDepth(1).mapNotNull { it.name }

actual inline fun <reified T> loadJsonFromApiFile(filePath: String): T {
    val content = File(filePath).readText(Charsets.UTF_8)
    return Json.decodeFromString(content)
}

actual fun getTraitsFromFilePath(filePath: String): List<TraitDto> {
    val htmlString = readTextFromFile(filePath)
    val dom = Jsoup.parse(htmlString)
    return getSkillTraits(dom) + getNumberTraits(dom) + getOtherTraits(dom)
}

private fun getSkillTraits(dom: Document): List<TraitDto> {
    val boostTypes =
        dom.selectXpath("""//h6[contains(@class,"Boost--label-trait-type")]""").map { it.text() }
    val boostValues =
        dom.selectXpath("""//div[@data-progress]""").map { it.attr("data-progress").toFloat() }
    return boostTypes.zip(boostValues)
        .map { TraitDto.SkillTraitDto(traitType = it.first, value = it.second) }
}

private fun getNumberTraits(dom: Document): List<TraitDto> {
    val numericTypes = dom.selectXpath("""//div[@class="NumericTrait--type"]""").map { it.text() }
    val numericValues = dom.selectXpath("""//div[@class="NumericTrait--value"]""")
        .map { it.text().split(" ").first().toInt() }
        .chunked(2).first()
    return numericTypes.zip(numericValues)
        .map { TraitDto.NumberTraitDto(traitType = it.first, value = it.second) }
}

private fun getOtherTraits(dom: Document): List<TraitDto> {
    val propertyTypes = dom.selectXpath("""//div[@class="Property--type"]""").map { it.text() }
    val propertyValues = dom.selectXpath("""//div[@class="Property--value"]""").map { it.text() }
    return propertyTypes.zip(propertyValues)
        .map { TraitDto.OtherTraitDto(traitType = it.first, value = it.second) }
}