package com.yousef.mvvmflightinfo.ui.map

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.yousef.mvvmflightinfo.data.DataManager
import com.yousef.mvvmflightinfo.data.model.AirportLatLongPOJO
import com.yousef.mvvmflightinfo.utils.RxImmediateSchedulerRule
import com.yousef.mvvmflightinfo.utils.rx.TestSchedulerProvider
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MapViewModelTest {

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Mock
    var mMapCallback: MapNavigator? = null

    @Mock
    var mMockDataManager: DataManager? = null
    private var mMapViewModel: MapViewModel? = null
    private var mTestScheduler: TestScheduler? = null

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    @Throws(java.lang.Exception::class)
    open fun setUp(): Unit {
        mTestScheduler = TestScheduler()
        val testSchedulerProvider = TestSchedulerProvider(mTestScheduler!!)

//        val schedulerProvider = TrampolineSchedulerProvider()
        mMapViewModel = MapViewModel(mMockDataManager, testSchedulerProvider)
        mMapViewModel!!.setNavigator(mMapCallback)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        mTestScheduler = null
        mMapViewModel = null
        mMapCallback = null
    }

    @Test
    fun testRequestLuftMap() {
        val coordinate = AirportLatLongPOJO().Coordinate("123","456")
        val position = AirportLatLongPOJO().Position(coordinate)
        val airport = AirportLatLongPOJO().Airport(position)
        val airports = AirportLatLongPOJO().Airports(airport)
        val airportResource = AirportLatLongPOJO().AirportResource(airports)
        val mapResponse = AirportLatLongPOJO(airportResource)

        doReturn(Single.just(mapResponse))
                .`when`(mMockDataManager)
                ?.requestLatLong(any(), any())
        mMapViewModel?.coordinates
        mTestScheduler!!.triggerActions()
        verify(mMapCallback)?.showData()
    }
}