package ru.otus.otuskotlin.marketplace.common

import kotlinx.datetime.Instant
import ru.otus.otuskotlin.marketplace.common.models.*
import ru.otus.otuskotlin.marketplace.common.stubs.ResourcesStubs

data class ResourcesContext(
    var command: ResourcesCommand = ResourcesCommand.NONE,
    var state: ResourcesState = ResourcesState.NONE,
    val errors: MutableList<ResourcesError> = mutableListOf(),

    var workMode: ResourcesWorkMode = ResourcesWorkMode.PROD,
    var stubCase: ResourcesStubs = ResourcesStubs.NONE,

    var requestId: ResourcesRequestId = ResourcesRequestId.NONE,
    var timeStart: Instant = Instant.NONE,
    var resourceRequest: Resources = Resources(),
    var resourceFilterRequest: ResourcesFilter = ResourcesFilter(),
    var resourceResponse: Resources = Resources(),
    var resourcesResponse: MutableList<Resources> = mutableListOf(),
)