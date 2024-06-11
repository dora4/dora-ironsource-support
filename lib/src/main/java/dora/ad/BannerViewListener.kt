package dora.ad

import android.app.Activity
import android.view.View
import com.ironsource.mediationsdk.adunit.adapter.utility.AdInfo
import com.ironsource.mediationsdk.logger.IronSourceError
import com.ironsource.mediationsdk.sdk.LevelPlayBannerListener

interface BannerViewListener  {

    fun loadBanner(activity: Activity, listener: LevelPlayBannerListener? = object : LevelPlayBannerListener {
        override fun onAdLoaded(adInfo: AdInfo?) {
            setBannerViewVisibility(View.VISIBLE)
        }

        override fun onAdLoadFailed(error: IronSourceError?) {
        }

        override fun onAdClicked(adInfo: AdInfo?) {
        }

        override fun onAdLeftApplication(adInfo: AdInfo?) {
        }

        override fun onAdScreenPresented(adInfo: AdInfo?) {
        }

        override fun onAdScreenDismissed(adInfo: AdInfo?) {
        }
    })

    fun destroyBanner(activity: Activity)

    fun setBannerViewVisibility(visibility: Int)
}