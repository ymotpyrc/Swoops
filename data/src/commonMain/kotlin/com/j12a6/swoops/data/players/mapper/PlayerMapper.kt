package com.j12a6.swoops.data.players.mapper

import com.j12a6.swoops.data.players.model.PlayerDto
import com.j12a6.swoops.data.players.model.TraitDto
import com.j12a6.swoops.domain.players.models.Player
import com.j12a6.swoops.domain.players.models.SkillDetail
import com.j12a6.swoops.domain.players.models.SkillName
import com.j12a6.swoops.domain.players.models.SkillRank
import com.j12a6.swoops.domain.players.models.Trait

const val MIN_SCORE = 30f
const val MAX_SCORE = 100f

class PlayerMapper {

    private fun getTopSkills(traits: List<TraitDto>): Map<SkillRank, SkillName> =
        listOf(
            Pair(SkillRank.TOP_1, Trait.TOP_1_SKILL),
            Pair(SkillRank.TOP_2, Trait.TOP_2_SKILL),
            Pair(SkillRank.TOP_3, Trait.TOP_3_SKILL),
        ).associate { (skillRank, traitRank) ->
            val traitValue = (traits.first { it.traitType == traitRank.value } as TraitDto.OtherTraitDto).value
            skillRank to SkillName.from(fixWrongNamingOfTopAttributes(traitValue))!!
        }

    fun map(playerDto: PlayerDto): Player {
        return Player(
            number = playerDto.number,
            prospect = (playerDto.traits.first { it.traitType == Trait.PROSPECT.value } as TraitDto.NumberTraitDto).value,
            season = (playerDto.traits.first { it.traitType == Trait.SEASON.value } as TraitDto.NumberTraitDto).value,
            position = getPosition(playerDto.traits),
            skills = getSkills(playerDto.traits),
            topSkills = getTopSkills(playerDto.traits),
        )
    }

    private fun getPosition(traits: List<TraitDto>): String =
        listOfNotNull(
            (traits.first { it.traitType == Trait.POSITION_1.value } as TraitDto.OtherTraitDto).value,
            (traits.firstOrNull { it.traitType == Trait.POSITION_2.value } as? TraitDto.OtherTraitDto)?.value,
        ).joinToString("")

    private fun fixWrongNamingOfTopAttributes(topSkillName: String): String =
        when (topSkillName) {
            "Free Throw Shooting" -> "Free Throw"
            "Passing" -> "Assist"
            else -> topSkillName
        }

    private fun getSkills(traits: List<TraitDto>): Map<SkillName, SkillDetail> {
        val revealedTraits = traits.filterIsInstance<TraitDto.SkillTraitDto>()
        return SkillName.values().associateWith { skillName ->
            val traitValue = revealedTraits.firstOrNull { it.traitType == skillName.value }?.value
            SkillDetail(
                score = traitValue ?: 0f,
                minScore = traitValue ?: MIN_SCORE,
                maxScore = traitValue ?: MAX_SCORE,
                revealed = traitValue != null,
            )
        }
    }
}
