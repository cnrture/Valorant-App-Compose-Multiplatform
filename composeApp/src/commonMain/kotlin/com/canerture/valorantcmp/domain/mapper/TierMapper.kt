package com.canerture.valorantcmp.domain.mapper

import com.canerture.valorantcmp.data.model.competitivetiers.Tier
import com.canerture.valorantcmp.domain.model.TierUI

fun List<Tier>?.mapToTierUI(): List<TierUI> = this?.map {
    TierUI(
        backgroundColor = it.backgroundColor.orEmpty(),
        color = it.color.orEmpty(),
        division = it.division.orEmpty(),
        divisionName = it.divisionName.orEmpty(),
        largeIcon = it.largeIcon.orEmpty(),
        smallIcon = it.smallIcon.orEmpty(),
        tier = it.tier ?: 0,
        tierName = it.tierName.orEmpty()
    )
}.orEmpty().filter { it.largeIcon.isNotEmpty() && it.tierName != "UNRANKED" }
