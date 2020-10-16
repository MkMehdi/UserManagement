package ma.sample.usertasks.data.repository

import ma.sample.usertasks.data.api.UserTaskDataSource
import ma.sample.usertasks.data.db.dao.TaskDao
import ma.sample.usertasks.data.db.dao.UserDao
import ma.sample.usertasks.utils.performGetOperation
import javax.inject.Inject


/**
 * Created by Elmehdi Mellouk on 10/15/20.
 * elmehdi.mellouk@xpi.com
 */

class UserTasksRepository @Inject constructor(
    private val userTaskDataSource: UserTaskDataSource,
    private val userDao: UserDao,
    private val taskDao: TaskDao
) {

    fun getUsers() = performGetOperation(
        databaseQuery = { userDao.getUsers() },
        networkCall = { userTaskDataSource.getUsers() },
        saveCallResult = { userDao.insertUsers(it) }
    )

    fun getUserTasks(userId:String) = performGetOperation(
        databaseQuery = { taskDao.getUserTasks(userId) },
        networkCall = { userTaskDataSource.getUserTasks(userId) },
        saveCallResult = { taskDao.insertAll(it) }
    )
}