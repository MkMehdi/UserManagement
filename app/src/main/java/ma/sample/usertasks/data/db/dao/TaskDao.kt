package ma.sample.usertasks.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import ma.sample.usertasks.data.entity.Task


/**
 * Created by Elmehdi Mellouk on 10/14/20.
 * elmehdi.mellouk@xpi.com
 */

@Dao
interface TaskDao {

    @Insert(onConflict = REPLACE)
    fun insertAll(tasks: List<Task>)

    @Delete
    fun deleteTask(task: Task): Int

    @Query("select * from task where userId =:userId")
    fun getUserTasks(userId:String): LiveData<List<Task>>

}