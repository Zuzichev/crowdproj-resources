package ru.otus.otuskotlin.marketplace.common.models

data class Resources(
    var id: ResourcesId = ResourcesId.NONE,
    var resourcesId: OtherResourcesId = OtherResourcesId.NONE,
    var scheduleId: ScheduleId = ScheduleId.NONE,
    var ownerId: ResourcesUserId = ResourcesUserId.NONE,
    var deleted: Boolean = false,
    var visible: ResourcesVisible = ResourcesVisible.NONE,
    val permissionsClient: MutableSet<ResourcesPermissionClient> = mutableSetOf()
)