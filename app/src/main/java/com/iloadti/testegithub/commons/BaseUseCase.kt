package com.iloadti.testegithub.commons

import androidx.annotation.StringRes
import com.iloadti.testegithub.R
import com.iloadti.testegithub.domain.SchedulerProvider
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import java.net.SocketTimeoutException

internal abstract class BaseUseCase<T>(
    private val scheduler: SchedulerProvider
) {

    private val disposables = CompositeDisposable()

    fun dispose() {
        disposables.clear()
    }

    protected fun Single<T>.executeUseCase(
        onSuccess: (T) -> Unit,
        onError: (Int) -> Unit
    ) {

        disposables.add(
            this
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe(onSuccess, {
                    onError(getMessageException(it))
                })
        )
    }

    @StringRes
    protected fun getMessageException(exception: Throwable): Int =
        if (exception is SocketTimeoutException) {
            R.string.error_connection
        } else {
            R.string.mr_error_list
        }
}