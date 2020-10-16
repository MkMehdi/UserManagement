package ma.sample.usertasks.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import ma.sample.usertasks.data.entity.User


/**
 * Created by Elmehdi Mellouk on 10/14/20.
 * elmehdi.mellouk@xpi.com
 */

@Dao
interface UserDao {

    @Insert(onConflict = REPLACE)
    fun insertUsers(users: List<User>)

    @Delete
    fun deleteUser(user: User): Int

    @Query("select * from user")
    fun getUsers(): LiveData<List<User>>

}