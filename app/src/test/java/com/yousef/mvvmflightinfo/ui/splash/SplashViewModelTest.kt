package com.yousef.mvvmflightinfo.ui.splash

import com.yousef.mvvmflightinfo.data.DataManager
import com.yousef.mvvmflightinfo.data.model.TokenPOJO
import com.yousef.mvvmflightinfo.utils.RxImmediateSchedulerRule
import com.yousef.mvvmflightinfo.utils.rx.TestSchedulerProvider
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.ClassRule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class SplashViewModelTest {

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Mock
    var mSplashCallback: SplashNavigator? = null

    @Mock
    var mMockDataManager: DataManager? = null
    private var mSplashViewModel: SplashViewModel? = null
    private var mTestScheduler: TestScheduler? = null

    @Before
    @Throws(java.lang.Exception::class)
    open fun setUp(): Unit {
        mTestScheduler = TestScheduler()
        val testSchedulerProvider = TestSchedulerProvider(mTestScheduler!!)

        MockitoAnnotations.initMocks(this)
        mSplashViewModel = SplashViewModel(mMockDataManager, testSchedulerProvider)
        mSplashViewModel!!.setNavigator(mSplashCallback)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        mTestScheduler = null
        mSplashViewModel = null
        mSplashCallback = null
    }

    @Test
    fun testRequestAccessToken() {
        val splashResponse = TokenPOJO()
        splashResponse.setAccessToken("token")

        doReturn(Single.just(splashResponse))
                .`when`(mMockDataManager!!)
                ?.requestAccessTokenPost()
        mSplashViewModel!!.requestAccessTokenPost()
        mTestScheduler!!.triggerActions()
        verify(mSplashCallback)!!.openMainActivity()
    }

    @Test
    fun testRequestAccessTokenWhenThrowsError() {
        val splashResponse = TokenPOJO()
        `when`(mMockDataManager!!.requestAccessTokenPost()).thenReturn(Single.just(splashResponse))

        mSplashViewModel!!.requestAccessTokenPost()
        mTestScheduler!!.triggerActions()
        verify(mSplashCallback)!!.handleError("Error occurred")
    }
}