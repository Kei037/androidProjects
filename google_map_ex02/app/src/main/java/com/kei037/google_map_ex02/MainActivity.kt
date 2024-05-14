package com.kei037.google_map_ex02

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapFragment
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.GroundOverlay
import com.google.android.gms.maps.model.GroundOverlayOptions
import com.google.android.gms.maps.model.LatLng

class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var googleMap: GoogleMap
    private lateinit var mapFragment: MapFragment
    private lateinit var mapList: ArrayList<GroundOverlay>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Google 지도 활용"

        mapFragment = fragmentManager.findFragmentById(R.id.map) as MapFragment
        mapFragment.getMapAsync(this)
        mapList = ArrayList()

    }
    override fun onMapReady(p0: GoogleMap?) {
        googleMap = p0!!
        googleMap.uiSettings.isZoomControlsEnabled = true
        googleMap.setOnMapClickListener { point ->
            val videoMark = GroundOverlayOptions().image(
                BitmapDescriptorFactory.fromBitmap(vectorToBitmap(this, R.drawable.icon_camera))
            ).position(point, 100f, 100f)
            // 마커를 지도와 리스트에 추가
            val tmp = googleMap.addGroundOverlay(videoMark)
            mapList.add(tmp)
        }

        googleMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(35.8299818185, 128.690180447), 15f))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menu.add(0, 1, 0, "위성 지도")
        menu.add(0, 2, 0, "일반 지도")
        menu.add(0, 3, 0, "바로전 CCTV 지우기")
        menu.add(0, 4, 0, "모든 CCTV 지우기")
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            1 -> {
                googleMap.mapType = GoogleMap.MAP_TYPE_HYBRID
            }
            2 -> {
                googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
            }
            3 -> {
                val last = mapList.size - 1
                mapList.get(last).remove()
                mapList.removeAt(last)
                return true
            }
            4 -> {
                mapList.forEach { i -> i.remove() } // 반복문으로 GroundOverlay 전부 삭제
                mapList.clear() // ArrayList에 든값 모두 삭제
                return true
            }

        }
        return false
    }

    private fun vectorToBitmap(context: Context, vectorResId: Int): Bitmap? {
        /* 벡터 이미지를 비트맵으로 변경 */
        val drawable: Drawable? = ContextCompat.getDrawable(context, vectorResId)
            ?.let { DrawableCompat.wrap(it).mutate() }
        drawable?.let {
            val bitmap =
                Bitmap.createBitmap(it.intrinsicWidth, it.intrinsicHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            it.setBounds(0, 0, canvas.width, canvas.height)
            it.draw(canvas)
            return bitmap
        }
        return null
    }
}