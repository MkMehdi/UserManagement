package ma.sample.usertasks.data.api

import android.util.Log
import ma.sample.usertasks.utils.Progress
import retrofit2.Response


/**
 * Created by Elmehdi Mellouk on 10/14/20.
 * elmehdi.mellouk@xpi.com
 */

abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Progress<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Progress.success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Progress<T> {
        Log.e("remoteDataSource", message)
        return Progress.error("Network call has failed for a following reason: $message")
    }

}