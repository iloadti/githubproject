package com.iloadti.testegithub

import com.iloadti.testegithub.domain.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

internal class TestSchedulerProvider : SchedulerProvider {
    override fun ui(): Scheduler = Schedulers.single()
    override fun io(): Scheduler = Schedulers.single()
}