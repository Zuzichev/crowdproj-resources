package com.crowdproj.resources.app.common

import com.crowdproj.resources.biz.ResourcesProcessor
import com.crowdproj.resources.common.ResourcesCorSettings
import com.crowdproj.resources.logging.common.MpLoggerProvider

interface IMkplAppSettings {
    val processor: ResourcesProcessor
    val corSettings: ResourcesCorSettings
    val logger: MpLoggerProvider
}