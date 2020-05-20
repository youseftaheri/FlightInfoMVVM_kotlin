package com.yousef.mvvmflightinfo.ui.base

import android.annotation.TargetApi
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import com.yousef.mvvmflightinfo.MyApplication
import com.yousef.mvvmflightinfo.utils.CommonUtils
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import dagger.android.support.HasSupportFragmentInjector
import androidx.databinding.ViewDataBinding
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper
import javax.inject.Inject
import com.yousef.mvvmflightinfo.utils.NetworkUtils.isNetworkConnected

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel<*>?> : DaggerAppCompatActivity(), HasSupportFragmentInjector {
    // TODO
    // this can probably depend on isLoading variable of BaseViewModel,
    // since its going to be common for all the activities

    @JvmField
    @Inject
    var utils: CommonUtils? = null
    var viewDataBinding: T? = null
        private set
    private var mViewModel: V? = null
    @JvmField
    var mContext: Context? = null

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract val bindingVariable: Int

    /**
     * @return layout resource id
     */
    @get:LayoutRes
    abstract val layoutId: Int

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract val viewModel: V
    override fun attachBaseContext(newBase: Context) {
        val context = (newBase.applicationContext as MyApplication)
        super.attachBaseContext(CalligraphyContextWrapper.wrap(context))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        mContext = this@BaseActivity
        performDataBinding()
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun hasPermission(permission: String?): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission!!) == PackageManager.PERMISSION_GRANTED
    }

    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun hideLoading() {
        utils!!.hideLoadingDialog(this)
    }

    val isNetworkConnected: Boolean
        get() = isNetworkConnected(applicationContext)

    fun openActivityOnTokenExpire() {
        //      startActivity(LoginActivity.newIntent(this));
        //      finish();
    }

    fun performDependencyInjection() {
        AndroidInjection.inject(this)
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun requestPermissionsSafely(permissions: Array<String?>?, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions!!, requestCode)
        }
    }

    fun showLoading() {
        hideLoading()
        utils!!.showLoadingDialog(this)
    }

    private fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, layoutId)
        mViewModel = if (mViewModel == null) viewModel else mViewModel
        viewDataBinding!!.setVariable(bindingVariable, mViewModel)
        viewDataBinding!!.executePendingBindings()
    }

    fun startNewActivity(activity: Class<*>?, isFinishAll: Boolean, isCurrentFinish: Boolean) {
        val i = Intent(mContext, activity)
        if (isFinishAll) i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(i)
        if (isCurrentFinish) finish()
        utils!!.gotoNextActivityAnimation(mContext!!)
    }
}