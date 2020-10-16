package ma.sample.usertasks.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map

import ma.sample.usertasks.utils.Progress.Status.*

import kotlinx.coroutines.Dispatchers


/**
 * Created by Elmehdi Mellouk on 10/15/20.
 * elmehdi.mellouk@xpi.com
 */

fun <T, A> performGetOperation(databaseQuery: () -> LiveData<T>,
                               networkCall: suspend () -> Progress<A>,
                               saveCallResult: suspend (A) -> Unit): LiveData<Progress<T>> =
    liveData(Dispatchers.IO) {
        emit(Progress.loading())
        val source = databaseQuery.invoke().map { Progress.success(it) }
        emitSource(source)

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == SUCCESS) {
            saveCallResult(responseStatus.data!!)

        } else if (responseStatus.status == ERROR) {
            emit(Progress.error(responseStatus.message!!))
            emitSource(source)
        }
    }