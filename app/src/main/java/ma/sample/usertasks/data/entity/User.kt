package ma.sample.usertasks.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Created by Elmehdi Mellouk on 10/14/20.
 * elmehdi.mellouk@xpi.com
 */

@Entity(tableName = "user")
class User(

    @PrimaryKey
    val id:String = "",
    val name:String = "",
    val username:String = "",
    val email:String = "",
    val phone:String = "",
    val website:String = ""
)