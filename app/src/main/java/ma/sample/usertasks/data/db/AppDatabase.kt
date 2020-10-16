package ma.sample.usertasks.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ma.sample.usertasks.data.db.dao.TaskDao
import ma.sample.usertasks.data.db.dao.UserDao
import ma.sample.usertasks.data.entity.*


/**
 * Created by Elmehdi Mellouk on 10/14/20.
 * elmehdi.mellouk@xpi.com
 */


@Database(entities = [User::class,Task::class], version = 1,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun taskDao(): TaskDao


    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "userTask")
                .fallbackToDestructiveMigration()
                .build()
    }

}