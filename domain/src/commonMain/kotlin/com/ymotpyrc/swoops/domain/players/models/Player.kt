package com.ymotpyrc.swoops.domain.players.models

import kotlinx.serialization.Serializable

@Serializable
data class Player(
    val number: String,
    val prospect: Int,
    val season: Int,
    val position: String,
    val skills: Map<SkillName, SkillDetail>,
    val topSkills: Map<SkillRank, SkillName>,
) {

    companion object {
        const val path = "/players"
    }
}
