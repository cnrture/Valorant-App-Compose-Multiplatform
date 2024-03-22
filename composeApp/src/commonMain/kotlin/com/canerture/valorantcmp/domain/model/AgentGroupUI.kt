package com.canerture.valorantcmp.domain.model

import androidx.compose.ui.graphics.Color

data class AgentGroupUI(
    val role: String,
    val roleIcon: String,
    val agents: List<AgentUI>
)

data class AgentUI(
    val abilities: List<AbilityUI>,
    val description: String,
    val displayName: String,
    val fullPortrait: String,
    val role: RoleUI,
    val id: String,
    val background: String,
    val backgroundGradientColors: List<Color>,
)

data class AbilityUI(
    val description: String,
    val displayIcon: String,
    val displayName: String,
)

data class RoleUI(
    val displayIcon: String,
    val displayName: String
)
