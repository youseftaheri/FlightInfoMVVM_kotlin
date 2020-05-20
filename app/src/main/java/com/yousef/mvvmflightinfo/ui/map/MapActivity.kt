package com.yousef.mvvmflightinfo.ui.map

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.material.snackbar.Snackbar
import com.yousef.mvvmflightinfo.BR
import com.yousef.mvvmflightinfo.R
import com.yousef.mvvmflightinfo.databinding.ActivityMapBinding
import com.yousef.mvvmflightinfo.factory.ViewModelProviderFactory
import com.yousef.mvvmflightinfo.ui.base.BaseActivity
import com.yousef.mvvmflightinfo.ui.schedules.SchedulesActivity
import com.yousef.mvvmflightinfo.utils.CommonUtils
import java.util.*
import javax.inject.Inject

class MapActivity : BaseActivity<ActivityMapBinding, MapViewModel?>(), MapNavigator , OnMapReadyCallback {
    @JvmField
    @Inject
    var factory: ViewModelProviderFactory? = null
    private var mActivityMapBinding: ActivityMapBinding? = null
    private var mMapViewModel: MapViewModel? = null
    private var mMap: GoogleMap? = null
    var circleDrawable: Drawable? = null
    var markerIcon: BitmapDescriptor? = null
    var dblOrgLat: Double? = null
    var dblOrgLong: Double? = null
    var dblDstLat: Double? = null
    var dblDstLong: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mMapViewModel!!.setNavigator(this)
        mActivityMapBinding = viewDataBinding
        mMapViewModel!!.coordinates
    }

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.activity_map

    override val viewModel: MapViewModel
        get() {
            mMapViewModel = ViewModelProvider(this, factory!!).get(MapViewModel::class.java)
            return mMapViewModel!!
        }

    override fun handleError(exception: String?) {
        Snackbar.make(mActivityMapBinding!!.root, exception!!, Snackbar.LENGTH_LONG).show()
    }

    override fun showData() {
        showMarkers()
    }

    fun showMarkers() {
        //Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = (supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment?)!!
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        circleDrawable = ContextCompat.getDrawable(this, R.drawable.map_marker)
        markerIcon = getMarkerIconFromDrawable(circleDrawable)
        mMap!!.uiSettings.isZoomControlsEnabled = true
        dblOrgLat = mMapViewModel!!.getOrgLat()!!.toDouble()
        dblOrgLong = mMapViewModel!!.getOrgLng()!!.toDouble()
        dblDstLat = mMapViewModel!!.getDstLat()!!.toDouble()
        dblDstLong = mMapViewModel!!.getDstLng()!!.toDouble()
        // Add a marker move the camera
        val origin = LatLng(dblOrgLat!!, dblOrgLong!!)
        val destination = LatLng(dblDstLat!!, dblDstLong!!)
        mMap!!.addMarker(MarkerOptions().position(origin).icon(markerIcon)
                .draggable(false).visible(true).title("Origin: " + mMapViewModel!!.getOrigin()))
        mMap!!.addMarker(MarkerOptions().position(destination).icon(markerIcon)
                .draggable(false).visible(true).title("Destination: " + mMapViewModel!!.getDestination())).showInfoWindow()
        val builder = LatLngBounds.Builder()
        //the include method will calculate the min and max bound.
        builder.include(origin)
        builder.include(destination)
        val bounds = builder.build()
        val width = resources.displayMetrics.widthPixels
        val height = resources.displayMetrics.heightPixels
//        val padding = (width * 0.2).toInt()
        val padding = ((if(height < width) width else height) * 0.25).toInt()
        mMap!!.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding))

        //Polyline options
        val options = PolylineOptions()
        val pattern = Arrays.asList(Dash(30F), Gap(10F))
        mMap!!.addPolyline(options.width(10f).color(R.color.colorPrimary).geodesic(false).pattern(pattern).add(origin).add(destination))
    }

    override fun btBackClick() {
        onBackPressed()
    }

    private fun getMarkerIconFromDrawable(drawable: Drawable?): BitmapDescriptor {
        val height = 150
        val width = 100
        val canvas = Canvas()
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        canvas.setBitmap(bitmap)
        drawable!!.setBounds(0, 0, width, height)
        drawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    companion object {
        fun newIntent(context: Context?): Intent {
            return Intent(context, MapActivity::class.java)
        }
    }
}