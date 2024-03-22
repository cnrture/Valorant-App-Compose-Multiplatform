package com.canerture.valorantcmp.domain.mapper

import com.canerture.valorantcmp.data.model.maps.Map
import com.canerture.valorantcmp.domain.model.MapUI

fun List<Map>?.mapToMapUI() = this?.map {
    MapUI(
        coordinates = it.coordinates.orEmpty(),
        displayIcon = it.displayIcon.orEmpty(),
        displayName = it.displayName.orEmpty(),
        narrativeDescription = it.narrativeDescription.orEmpty(),
        splash = it.splash.orEmpty(),
        listViewIconTall = it.listViewIconTall.orEmpty(),
        id = it.id.orEmpty()
    )
}.orEmpty()

fun Map?.mapToMapUI() = MapUI(
    coordinates = this?.coordinates.orEmpty(),
    displayIcon = this?.displayIcon.orEmpty(),
    displayName = this?.displayName.orEmpty(),
    narrativeDescription = this?.narrativeDescription.orEmpty(),
    splash = this?.splash.orEmpty(),
    listViewIconTall = this?.listViewIconTall.orEmpty(),
    id = this?.id.orEmpty()
)
