package ma.sample.usertasks.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Created by Elmehdi Mellouk on 10/14/20.
 * elmehdi.mellouk@xpi.com
 */

@Entity(tableName = "task")
class Task(
    @PrimaryKey
    val userId:String = "",
    val title:String = "",
    val completed:Boolean = false)