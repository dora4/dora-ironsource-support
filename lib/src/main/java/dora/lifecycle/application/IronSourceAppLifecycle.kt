package dora.lifecycle.application

import android.app.Application
import android.content.Context
import com.ironsource.mediationsdk.IronSource
import dora.ad.DoraIronSource

class IronSourceAppLifecycle : ApplicationLifecycleCallbacks {

    override fun attachBaseContext(base: Context) {
    }

    override fun onCreate(application: Application) {
        IronSource.init(application, DoraIronSource.getAppKey())
    }

    override fun onTerminate(application: Application) {
    }
}