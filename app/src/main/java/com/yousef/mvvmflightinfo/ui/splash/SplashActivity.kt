package com.yousef.mvvmflightinfo.ui.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.yousef.mvvmflightinfo.R
import com.yousef.mvvmflightinfo.data.model.TokenPOJO
import com.yousef.mvvmflightinfo.databinding.ActivitySplashBinding
import com.yousef.mvvmflightinfo.ui.base.BaseActivity
import com.yousef.mvvmflightinfo.utils.CommonUtils
import com.yousef.mvvmflightinfo.BR
import com.yousef.mvvmflightinfo.factory.ViewModelProviderFactory
import com.yousef.mvvmflightinfo.ui.main.MainActivity
import javax.inject.Inject

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel?>(), SplashNavigator {
    @JvmField
    @Inject
    var factory: ViewModelProviderFactory? = null
    private var mActivitySplashBinding: ActivitySplashBinding? = null
    private var mSplashViewModel: SplashViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSplashViewModel!!.setNavigator(this)
        mActivitySplashBinding = viewDataBinding
        mSplashViewModel!!.requestAccessTokenPost()
    }

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.activity_splash

    override val viewModel: SplashViewModel
        get() {
            mSplashViewModel = ViewModelProvider(this, factory!!).get(SplashViewModel::class.java)
            return mSplashViewModel!!
        }

    override fun handleError(exception: String?) {
        Snackbar.make(mActivitySplashBinding!!.root, exception!!, Snackbar.LENGTH_LONG).show()
    }

    override fun openMainActivity() {
        startNewActivity(MainActivity::class.java, isFinishAll = true, isCurrentFinish = true)
    }

    companion object {
        fun newIntent(context: Context?): Intent {
            return Intent(context, SplashActivity::class.java)
        }
    }
}