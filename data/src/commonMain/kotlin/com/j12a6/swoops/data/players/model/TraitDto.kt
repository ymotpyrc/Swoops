package com.j12a6.swoops.data.players.model

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonClassDiscriminator("display_type")
sealed class TraitDto {

    @SerialName("trait_type")
    abstract val traitType: String

    @SerialName("boost_percentage")
    data class SkillTraitDto(
        override val traitType: String,
        val value: Float,
    ) : TraitDto()

    @SerialName("number")
    data class NumberTraitDto(
        override val traitType: String,
        val value: Int,
    ) : TraitDto()

    data class OtherTraitDto(
        override val traitType: String,
        val value: String,
    ) : TraitDto()
}
