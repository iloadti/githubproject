package com.iloadti.testegithub.domain

import io.reactivex.Scheduler

internal interface SchedulerProvider {
    fun io(): Scheduler
    fun ui(): Scheduler
}