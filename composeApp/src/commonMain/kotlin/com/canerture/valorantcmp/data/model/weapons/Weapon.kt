package com.canerture.valorantcmp.data.model.weapons

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Weapon(
    val category: String?,
    val displayIcon: String?,
    val displayName: String?,
    var skins: List<Skin>?,
    @SerialName("uuid") val id: String?,
    val weaponStats: WeaponStats?
)
