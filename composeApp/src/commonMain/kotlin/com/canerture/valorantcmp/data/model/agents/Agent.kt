package com.canerture.valorantcmp.data.model.agents

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Agent(
    val abilities: List<Ability>?,
    val description: String?,
    val displayName: String?,
    val fullPortrait: String?,
    val fullPortraitV2: String?,
    val role: Role?,
    @SerialName("uuid") val id: String?,
    val background: String?,
    val backgroundGradientColors: List<String>?,
)
