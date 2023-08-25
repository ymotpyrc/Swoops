package com.ymotpyrc.swoops.domain.players.models

import kotlinx.serialization.Serializable

@Serializable
data class SkillDetail(
    val score: Float,
    val minScore: Float,
    val maxScore: Float,
    val revealed: Boolean,
)
