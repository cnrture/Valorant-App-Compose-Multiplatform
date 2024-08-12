package com.canerture.valorantcmp.di

import com.canerture.valorantcmp.data.remote.ValorantService
import com.canerture.valorantcmp.data.repository.ValorantRepositoryImpl
import com.canerture.valorantcmp.domain.repository.ValorantRepository
import com.canerture.valorantcmp.domain.usecase.agents.GetAgentDetailUseCase
import com.canerture.valorantcmp.domain.usecase.agents.GetAgentsUseCase
import com.canerture.valorantcmp.domain.usecase.maps.GetMapDetailUseCase
import com.canerture.valorantcmp.domain.usecase.maps.GetMapsUseCase
import com.canerture.valorantcmp.domain.usecase.tiers.GetTiersUseCase
import com.canerture.valorantcmp.domain.usecase.weapons.GetWeaponDetailUseCase
import com.canerture.valorantcmp.domain.usecase.weapons.GetWeaponsUseCase
import com.canerture.valorantcmp.presentation.agentdetail.AgentDetailViewModel
import com.canerture.valorantcmp.presentation.agents.AgentsViewModel
import com.canerture.valorantcmp.presentation.mapdetail.MapDetailViewModel
import com.canerture.valorantcmp.presentation.maps.MapsViewModel
import com.canerture.valorantcmp.presentation.tiers.TiersViewModel
import com.canerture.valorantcmp.presentation.weapondetail.WeaponDetailViewModel
import com.canerture.valorantcmp.presentation.weapons.WeaponsViewModel
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
    factoryOf(::GetTiersUseCase)
    factoryOf(::GetMapsUseCase)
    factoryOf(::GetMapDetailUseCase)
    factoryOf(::GetWeaponsUseCase)
    factoryOf(::GetWeaponDetailUseCase)
}

val screenModelsModule = module {
    factoryOf(::AgentsViewModel)
    factoryOf(::AgentDetailViewModel)
    factoryOf(::TiersViewModel)
    factoryOf(::MapsViewModel)
    factoryOf(::MapDetailViewModel)
    factoryOf(::WeaponsViewModel)
    factoryOf(::WeaponDetailViewModel)
}

fun initKoin() = startKoin { modules(dataModule, useCaseModule, screenModelsModule) }
