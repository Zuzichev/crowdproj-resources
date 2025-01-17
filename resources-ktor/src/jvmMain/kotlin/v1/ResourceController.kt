package ru.otus.otuskotlin.marketplace.app.v1

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.otus.otuskotlin.marketplace.api.v1.models.*
import ru.otus.otuskotlin.marketplace.biz.ResourcesProcessor
import ru.otus.otuskotlin.marketplace.common.ResourcesContext
import ru.otus.otuskotlin.marketplace.mappers.v1.*

suspend fun ApplicationCall.createResource(processor: ResourcesProcessor) {
    val request = receive<ResourceCreateRequest>()
    val context = ResourcesContext()
    context.fromTransport(request)
    processor.exec(context)
    respond(context.toTransportCreate())
}

suspend fun ApplicationCall.readResource(processor: ResourcesProcessor) {
    val request = receive<ResourceReadRequest>()
    val context = ResourcesContext()
    context.fromTransport(request)
    processor.exec(context)
    respond(context.toTransportRead())
}

suspend fun ApplicationCall.updateResource(processor: ResourcesProcessor) {
    val request = receive<ResourceUpdateRequest>()
    val context = ResourcesContext()
    context.fromTransport(request)
    processor.exec(context)
    respond(context.toTransportUpdate())
}

suspend fun ApplicationCall.deleteResource(processor: ResourcesProcessor) {
    val request = receive<ResourceDeleteRequest>()
    val context = ResourcesContext()
    context.fromTransport(request)
    processor.exec(context)
    respond(context.toTransportDelete())
}

suspend fun ApplicationCall.searchResource(processor: ResourcesProcessor) {
    val request = receive<ResourceSearchRequest>()
    val context = ResourcesContext()
    context.fromTransport(request)
    processor.exec(context)
    respond(context.toTransportSearch())
}
