package com.canerture.valorantcmp.di

import com.canerture.valorantcmp.data.remote.ValorantService
import com.canerture.valorantcmp.data.repository.ValorantRepositoryImpl
import com.canerture.valorantcmp.domain.repository.ValorantRepository
import com.canerture.valorantcmp.domain.usecase.agents.GetAgentDetailUseCase
import com.canerture.valorantcmp.domain.usecase.agents.GetAgentsUseCase
import com.canerture.valorantcmp.domain.usecase.competitivetiers.GetCompetitiveTiersUseCase
import com.canerture.valorantcmp.domain.usecase.maps.GetMapDetailUseCase
import com.canerture.valorantcmp.domain.usecase.maps.GetMapsUseCase
import com.canerture.valorantcmp.domain.usecase.weapons.GetWeaponDetailUseCase
import com.canerture.valorantcmp.domain.usecase.weapons.GetWeaponsUseCase
import com.canerture.valorantcmp.presentation.agentdetail.AgentDetailScreenModel
import com.canerture.valorantcmp.presentation.agents.AgentsScreenModel
import com.canerture.valorantcmp.presentation.competitivetiers.CompetitiveTiersScreenModel
import com.canerture.valorantcmp.presentation.mapdetail.MapDetailScreenModel
import com.canerture.valorantcmp.presentation.maps.MapsScreenModel
import com.canerture.valorantcmp.presentation.weapondetail.WeaponDetailScreenModel
import com.canerture.valorantcmp.presentation.weapons.WeaponsScreenModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val dataModule = module {
    single<ValorantService> { ValorantService() }
    single<ValorantRepository> { ValorantRepositoryImpl(get()) }
}

val useCaseModule = module {
    factoryOf(::GetAgentsUseCase)
    factoryOf(::GetAgentDetailUseCase)
    factoryOf(::GetCompetitiveTiersUseCase)
    factoryOf(::GetMapsUseCase)
    factoryOf(::GetMapDetailUseCase)
    factoryOf(::GetWeaponsUseCase)
    factoryOf(::GetWeaponDetailUseCase)
}

val screenModelsModule = module {
    factoryOf(::AgentsScreenModel)
    factoryOf(::AgentDetailScreenModel)
    factoryOf(::CompetitiveTiersScreenModel)
    factoryOf(::MapsScreenModel)
    factoryOf(::MapDetailScreenModel)
    factoryOf(::WeaponsScreenModel)
    factoryOf(::WeaponDetailScreenModel)
}

fun initKoin() = startKoin { modules(dataModule, useCaseModule, screenModelsModule) }
