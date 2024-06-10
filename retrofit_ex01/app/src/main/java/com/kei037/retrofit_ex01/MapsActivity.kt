package com.kei037.retrofit_ex01

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.kei037.retrofit_ex01.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityMapsBinding

    private lateinit var mMap: GoogleMap
    private var currentLat: Double = 0.0 // MainActivity에서 전달된 위도
    private var currentLng: Double = 0.0 // MainActivity에서 전달된 경도

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // MainActivity에서 전달된 위도, 경도를 받아옴
        currentLat = intent.getDoubleExtra("currentLat", 0.0)
        currentLng = intent.getDoubleExtra("currentLng", 0.0)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)

        binding.btnCheckHere.setOnClickListener {
            mMap?.let {
                val intent = Intent()
                intent.putExtra("latitude", it.cameraPosition.target.latitude)
                intent.putExtra("longitude", it.cameraPosition.target.longitude)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }

    override fun onMapReady(p0: GoogleMap) {
        // 지도가 준비되었을 때 실행되는 콜백
        mMap = p0

        // MainActivity에서 전달된 위도, 경도를 받아옴
        mMap?.let {
            val currentLocation = LatLng(currentLat, currentLng)
            it.setMaxZoomPreference(20.0f)
            it.setMinZoomPreference(12.0f)
            it.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 16f))
        }

        setMarker()

        // 현재 위치로 이동하는 FAB 버튼 클릭 시
        binding.fabCurrentLocation.setOnClickListener {
            val locationProvider = LocationProvider(this@MapsActivity)
            // 위도와 경도 정보를 가져옴
            val latitude = locationProvider.getLocationLatitude()
            val longitude = locationProvider.getLocationLongitude()
            mMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(latitude, longitude), 16f))
            setMarker()
        }
    }

    private fun setMarker() {
        // 마커 설정하는 함수
        mMap?.let {
            it.clear() // 지도에 있는 마커를 먼저 삭제
            val markerOptions = MarkerOptions()
            markerOptions.position(it.cameraPosition.target) // 마커 위치 설정
            markerOptions.title("마커 위치") // 마커 이름 설정
            val marker = it.addMarker(markerOptions) // 지도에 마커 추가, 마커 객체를 리턴
            it.setOnCameraMoveListener {
                marker?.let { marker ->
                    marker.setPosition(it.cameraPosition.target) // 마커 위치 변경
                }
            }
        }
    }
}