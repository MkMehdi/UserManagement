package ma.sample.usertasks.data.api

import javax.inject.Inject


/**
 * Created by Elmehdi Mellouk on 10/15/20.
 * elmehdi.mellouk@xpi.com
 */
class UserTaskDataSource @Inject constructor(
    private val userTaskApi: UserTaskApi
): BaseDataSource() {

    suspend fun getUsers() = getResult { userTaskApi.getUsers() }

    suspend fun getUserTasks(userId:String) = getResult { userTaskApi.getUserTasks(userId) }
}