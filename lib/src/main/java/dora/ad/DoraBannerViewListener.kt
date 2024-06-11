package dora.ad

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.ironsource.mediationsdk.ISBannerSize
import com.ironsource.mediationsdk.IronSource
import com.ironsource.mediationsdk.IronSourceBannerLayout
import com.ironsource.mediationsdk.sdk.LevelPlayBannerListener
import dora.util.LogUtils

class DoraBannerViewListener(private val bannerParentLayout: FrameLayout) : BannerViewListener {

    private var ironSourceBannerLayout: IronSourceBannerLayout? = null

    override fun loadBanner(activity: Activity, listener: LevelPlayBannerListener?) {
        destroyBanner(activity)
        val size = ISBannerSize.BANNER
        ironSourceBannerLayout = IronSource.createBanner(activity, size)
        ironSourceBannerLayout?.apply {
            val layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            bannerParentLayout.addView(this, 0, layoutParams)
            levelPlayBannerListener = listener
            IronSource.loadBanner(this)
        }  ?: run {
            LogUtils.d("IronSource.createBanner returned null")
        }
    }

    fun getIronSourceBannerLayout() : IronSourceBannerLayout? {
        return ironSourceBannerLayout
    }

    fun getBannerParentLayout() : FrameLayout {
        return bannerParentLayout
    }

    override fun destroyBanner(activity: Activity) {
        IronSource.destroyBanner(ironSourceBannerLayout)
        bannerParentLayout.removeView(ironSourceBannerLayout)
        setBannerViewVisibility(View.GONE)
    }

    override fun setBannerViewVisibility(visibility: Int) {
        bannerParentLayout.visibility = visibility
    }
}