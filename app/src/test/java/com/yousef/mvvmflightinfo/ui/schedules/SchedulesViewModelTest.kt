package com.yousef.mvvmflightinfo.ui.schedules

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.yousef.mvvmflightinfo.data.DataManager
import com.yousef.mvvmflightinfo.data.model.LuftSchedulesPOJO
import com.yousef.mvvmflightinfo.utils.RxImmediateSchedulerRule
import com.yousef.mvvmflightinfo.utils.rx.TestSchedulerProvider
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import junit.framework.Assert.assertEquals
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class SchedulesViewModelTest {

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()

    }

    @Mock
    var mSchedulesCallback: SchedulesNavigator? = null

    @Mock
    var mMockDataManager: DataManager? = null
    private var mSchedulesViewModel: SchedulesViewModel? = null
    private var mTestScheduler: TestScheduler? = null

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    @Throws(java.lang.Exception::class)
    open fun setUp(): Unit {
        mTestScheduler = TestScheduler()
        val testSchedulerProvider = TestSchedulerProvider(mTestScheduler!!)
        mSchedulesViewModel = SchedulesViewModel(mMockDataManager, testSchedulerProvider)
        mSchedulesViewModel!!.setNavigator(mSchedulesCallback)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        mTestScheduler = null
        mSchedulesViewModel = null
        mSchedulesCallback = null
    }

    @Test
    fun testRequestLuftSchedules() {
        val asList = arrayListOf<LuftSchedulesPOJO.Schedule>()
        val marketingCarrier = LuftSchedulesPOJO().MarketingCarrier("111")
        val flight = LuftSchedulesPOJO().Flight(marketingCarrier)
        val schedule = LuftSchedulesPOJO().Schedule(flight)
        asList.add(schedule)
        val schedulesResponse = LuftSchedulesPOJO(asList)

        doReturn(Single.just(schedulesResponse))
                .`when`(mMockDataManager)
                ?.requestLuftSchedules(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())
        mSchedulesViewModel?.schedules
        mTestScheduler!!.triggerActions()
        assertEquals(mSchedulesViewModel?.getScheduleItemsLiveData()?.value, schedulesResponse.scheduleResource?.Schedules)
        verify(mSchedulesCallback)?.setUp()
    }

    @Test
    fun testRequestLuftSchedulesWhenIsEmpty() {
        val asList = arrayListOf<LuftSchedulesPOJO.Schedule>()
        val schedulesResponse = LuftSchedulesPOJO(asList)

        doReturn(Single.just(schedulesResponse))
                .`when`(mMockDataManager)
                ?.requestLuftSchedules(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())
        mSchedulesViewModel?.schedules
        mTestScheduler!!.triggerActions()
        verify(mSchedulesCallback)?.handleError("NO FLIGHT FOUND")
        verify(mSchedulesCallback)?.btBackClick()
    }
}