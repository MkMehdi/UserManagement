package ma.sample.usertasks.utils


/**
 * Created by Elmehdi Mellouk on 10/14/20.
 * elmehdi.mellouk@xpi.com
 */

data class Progress<out T>(val status: Status, val data: T?, val message: String?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T): Progress<T> {
            return Progress(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(message: String, data: T? = null): Progress<T> {
            return Progress(
                Status.ERROR,
                data,
                message
            )
        }

        fun <T> loading(data: T? = null): Progress<T> {
            return Progress(
                Status.LOADING,
                data,
                null
            )
        }
    }
}