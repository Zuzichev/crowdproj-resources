package ru.otus.otuskotlin.marketplace.biz.validation

import ru.otus.otuskotlin.marketplace.common.helpers.errorValidation
import ru.otus.otuskotlin.marketplace.common.ResourcesContext
import ru.otus.otuskotlin.marketplace.common.helpers.fail
import ru.otus.otuskotlin.marketplace.cor.ICorChainDsl
import ru.otus.otuskotlin.marketplace.cor.worker

// TODO-validation-7: пример обработки ошибки в рамках бизнес-цепочки
fun ICorChainDsl<ResourcesContext>.validateOtherIdIsNumber(title: String) = worker {
    this.title = title
    on { isNumeric(resourceValidating.resourcesId.asString()) }
    handle {
        fail(
            errorValidation(
                field = "OtherResourcesId",
                violationCode = "noNumeric",
                description = "field must be numeric"
            )
        )
    }
}
private fun isNumeric(s: String): Boolean {
    return try {
        s.toDouble()
        true
    } catch (e: Exception) {
        false
    }
}