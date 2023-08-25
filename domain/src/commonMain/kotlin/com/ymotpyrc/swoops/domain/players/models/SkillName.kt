package com.ymotpyrc.swoops.domain.players.models

enum class SkillName(val value: String) {
    THREE_POINT_SHOOTING("Three Point Shooting"),
    INTERIOR_TWO_POINT_SHOOTING("Interior Two Point Shooting"),
    MIDRANGE_TWO_POINT_SHOOTING("Midrange Two Point Shooting"),
    FREE_THROW("Free Throw"),
    DEFENSIVE_REBOUND("Defensive Rebound"),
    OFFENSIVE_REBOUND("Offensive Rebound"),
    ASSIST("Assist"),
    INTERIOR_DEFENSE("Interior Defense"),
    PERIMETER_DEFENSE("Perimeter Defense"),
    PHYSICALITY("Physicality"),
    LONGEVITY("Longevity"),
    HUSTLE("Hustle"),
    BASKETBALL_IQ("Basketball IQ"),
    LEADERSHIP("Leadership"),
    COACHABILITY("Coachability");

    companion object {
        infix fun from(value: String): SkillName? = SkillName.values().firstOrNull { it.value == value }
    }
}
