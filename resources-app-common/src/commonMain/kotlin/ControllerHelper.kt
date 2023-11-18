package com.crowdproj.resources.app.common

import kotlinx.datetime.Clock
import com.crowdproj.resources.api.logs.mapper.toLog
import com.crowdproj.resources.common.ResourcesContext
import com.crowdproj.resources.common.helpers.asResourcesError
import com.crowdproj.resources.common.models.ResourcesState
import kotlin.reflect.KClass

suspend inline fun <T> IMkplAppSettings.controllerHelper(
    crossinline getRequest: suspend ResourcesContext.() -> Unit,
    crossinline toResponse: suspend ResourcesContext.() -> T,
    clazz: KClass<*>,
    logId: String,
): T {
    val logger = corSettings.loggerProvider.logger(clazz)
    val ctx = ResourcesContext(
        timeStart = Clock.System.now(),
    )
    return try {
        logger.doWithLogging(logId) {
            ctx.getRequest()
            processor.exec(ctx)
            logger.info(
                msg = "Request $logId processed for ${clazz.simpleName}",
                marker = "BIZ",
                data = ctx.toLog(logId)
            )
            ctx.toResponse()
        }
    } catch (e: Throwable) {
        logger.doWithLogging("$logId-failure") {
            ctx.state = ResourcesState.FAILING
            ctx.errors.add(e.asResourcesError())
            processor.exec(ctx)
            ctx.toResponse()
        }
    }
}