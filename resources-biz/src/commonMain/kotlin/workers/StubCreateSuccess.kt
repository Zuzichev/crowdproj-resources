package ru.otus.otuskotlin.marketplace.biz.workers

import ru.otus.otuskotlin.marketplace.cor.ICorChainDsl
import ru.otus.otuskotlin.marketplace.cor.worker
import ru.otus.otuskotlin.marketplace.common.ResourcesContext
import ru.otus.otuskotlin.marketplace.common.models.OtherResourcesId
import ru.otus.otuskotlin.marketplace.common.models.ResourcesState
import ru.otus.otuskotlin.marketplace.common.models.ResourcesVisible
import ru.otus.otuskotlin.marketplace.common.models.ScheduleId
import ru.otus.otuskotlin.marketplace.common.stubs.ResourcesStubs
import ru.otus.otuskotlin.marketplace.stubs.ResourcesStub

fun ICorChainDsl<ResourcesContext>.stubCreateSuccess(title: String) = worker {
    this.title = title
    on { stubCase == ResourcesStubs.SUCCESS && state == ResourcesState.RUNNING }
    handle {
        state = ResourcesState.FINISHING
        val stub = ResourcesStub.prepareResult {
            resourceRequest.resourcesId.takeIf { it != OtherResourcesId.NONE }?.also { this.resourcesId = it }
            resourceRequest.scheduleId.takeIf { it != ScheduleId.NONE }?.also { this.scheduleId = it }
            resourceRequest.visible.takeIf { it != ResourcesVisible.NONE }?.also { this.visible = it }
        }
        resourceResponse = stub
    }
}