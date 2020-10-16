package ma.sample.usertasks

import android.app.Application
import dagger.hilt.android.HiltAndroidApp



/**
 * Created by Elmehdi Mellouk on 8/7/20.
 * elmehdi.mellouk@xpi.com
 */

@HiltAndroidApp
class AppUserTask:Application(){

    override fun onCreate() {
        super.onCreate()
    }

}