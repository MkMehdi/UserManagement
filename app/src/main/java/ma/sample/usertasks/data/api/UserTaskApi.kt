package ma.sample.usertasks.data.api


import ma.sample.usertasks.data.entity.Task
import ma.sample.usertasks.data.entity.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Created by Elmehdi Mellouk on 10/14/20.
 * elmehdi.mellouk@xpi.com
 */

interface UserTaskApi {

    @GET("/users")
    suspend fun getUsers(): Response<List<User>>

    @GET("/todos")
    suspend fun getUserTasks(@Query("userId") userId: String): Response<List<Task>>

}