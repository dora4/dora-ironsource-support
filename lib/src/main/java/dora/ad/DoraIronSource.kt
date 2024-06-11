package dora.ad

import android.text.TextUtils
import java.lang.IllegalStateException

object DoraIronSource {

    private var appKey: String = ""

    /**
     * 官方的测试App Key。
     */
    const val DEFAULT_APP_KEY = "1eb150495"

    fun setAppKey(appKey: String) {
        this.appKey = appKey
    }

    fun getAppKey() : String {
        if (TextUtils.isEmpty(appKey)) {
            throw IllegalStateException("please invoke DoraIronSource's setAppKey() at attachBaseContext() first.")
        }
        return appKey
    }
}