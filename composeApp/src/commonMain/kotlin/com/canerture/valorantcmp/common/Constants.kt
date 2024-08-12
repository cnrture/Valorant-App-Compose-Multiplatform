package com.canerture.valorantcmp.common

object Routes {
    const val SPLASH = "splash"
    const val AGENTS = "agents"
    const val AGENT_DETAIL = "agentDetail"
    const val AGENT_DETAIL_WITH_PARAM = "agentDetail/{agentId}"
    const val MAPS = "maps"
    const val MAP_DETAIL = "mapDetail"
    const val MAP_DETAIL_WITH_PARAM = "mapDetail/{mapId}"
    const val WEAPONS = "weapons"
    const val WEAPON_DETAIL = "weaponDetail"
    const val WEAPON_DETAIL_WITH_PARAM = "weaponDetail/{weaponId}"
    const val TIERS = "tiers"
}

object Params {
    const val AGENT_ID = "agentId"
    const val MAP_ID = "mapId"
    const val WEAPON_ID = "weaponId"
}

object Endpoints {
    const val AGENTS = "v1/agents?isPlayableCharacter=true"
    const val AGENT_DETAIL = "v1/agents"
    const val MAPS = "v1/maps"
    const val WEAPONS = "v1/weapons"
    const val TIERS = "v1/competitivetiers"
}