package com.kei037.google_map_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapFragment
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng

class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    lateinit var googleMap: GoogleMap
    lateinit var mapFragment: MapFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Google 지도 활용"

        mapFragment = fragmentManager.findFragmentById(R.id.map) as MapFragment
        mapFragment.getMapAsync(this)
    }
    override fun onMapReady(p0: GoogleMap?) {
        googleMap = p0!!
//        googleMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
//        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(35.8299818185, 128.690180447), 15f))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menu.add(0, 1, 0, "위성 지도")
        menu.add(0, 2, 0, "일반 지도")
        val submenu = menu.addSubMenu("유명장소 바로가기")
        submenu.add(0, 3, 0, "월드컵 경기장")
        submenu.add(0, 4, 0, "두류 공원")
        submenu.add(0, 5, 0, "수성못")

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
                googleMap.moveCamera(
                    CameraUpdateFactory.newLatLngZoom(LatLng(35.8299818185, 128.690180447), 15f))
                return true
            }
            4 -> {
                googleMap.moveCamera(
                    CameraUpdateFactory.newLatLngZoom(LatLng(35.84740, 128.5578), 15f))
                return true
            }
            5 -> {
                googleMap.moveCamera(
                    CameraUpdateFactory.newLatLngZoom(LatLng(35.82833, 128.6176), 15f))
                return true
            }
        }
        return false
    }
}