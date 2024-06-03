package com.kei037.deaguapp_ex01

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.kei037.deaguapp_ex01.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    private val defaultLocation = LatLng(35.8714354, 128.601445)
    private val defaultZoomLevel = 15f

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    // 권한 요청 코드
    private val permissionsRequired = arrayOf(
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.ACCESS_COARSE_LOCATION
    )
    // 권한 요청 확인코드
    private val permissionsRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.btnMyLocation.setOnClickListener {
            initMap() // 현재 위치로 이동
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, defaultZoomLevel))

        if (hasPermissions()) { // 권한이 있을 경우
            initMap() // 현재 위치로 이동
        } else { // 권한이 없을 경우, 권한 요청
            ActivityCompat.requestPermissions(this, permissionsRequired, permissionsRequestCode)
        }
    }

    // 권한 확인 함수
    private fun hasPermissions(): Boolean {
        for (permission in permissionsRequired) {
            if (ActivityCompat.checkSelfPermission(this, permission)
                != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    // 권한 요청 결과 처리 함수
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == permissionsRequestCode) {
            if (PackageManager.PERMISSION_GRANTED == grantResults.firstOrNull()) {
                Toast.makeText(applicationContext, "권한 요청이 승인되었습니다.", Toast.LENGTH_LONG).show()
                initMap()
            } else {
                Toast.makeText(applicationContext, "권한 요청이 거부되었습니다.", Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }

    @SuppressLint("MissingPermission") // 권한이 있는 경우에만 호출
    private fun getMyLocation(): LatLng {
        /* 현재 위치를 반환 */
        val location: Location

        // 위치 서비스 객체를 불러옴.
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        // GPS와 네트워크를 통해 위치 정보를 가져옴.
        val locationNetwork: Location? =
            locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

        val locationGPS: Location? =
            locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)

        if (locationGPS != null && locationNetwork != null) {
            location = if (locationGPS.accuracy > locationNetwork.accuracy) {
                locationGPS
            } else {
                locationNetwork
            }
            return LatLng(location.latitude, location.longitude)
        } else if (locationGPS == null && locationNetwork == null) {
            return defaultLocation
        } else {
            return if (locationGPS == null) {
                LatLng(locationNetwork!!.latitude, locationNetwork.longitude)
            } else {
                LatLng(locationGPS.latitude, locationGPS.longitude)
            }
        }

    }

    private fun initMap() {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(getMyLocation(), defaultZoomLevel))
    }
}