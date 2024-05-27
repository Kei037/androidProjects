package com.kei037.seoul_libraries_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.kei037.seoul_libraries_ex01.databinding.ActivityMapsBinding
import com.kei037.seoul_libraries_ex01.retrofit.LibraryResponse
import com.kei037.seoul_libraries_ex01.retrofit.LibraryService
import com.kei037.seoul_libraries_ex01.retrofit.RetrofitConnection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
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

        // Add a marker in Sydney and move the camera
//        val sydney = LatLng(-34.0, 151.0)
//        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        loadLibrary()
    }
    private fun loadLibrary() {
        val my_api_key = "48704b64446a756e3637476d684e61"

        val retrofitAPI = RetrofitConnection.getInstance().create(LibraryService::class.java)
        retrofitAPI.getLibrary(my_api_key).enqueue(object : Callback<LibraryResponse> {
            override fun onResponse(
                call: Call<LibraryResponse>,
                response: Response<LibraryResponse>
            ) {
                showLibrary(response.body() as LibraryResponse)
            }

            override fun onFailure(call: Call<LibraryResponse>, t: Throwable) {
                Toast.makeText(baseContext, "서버에서 데이터를 가져올 수 없습니다.", Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun showLibrary(libraryResponse: LibraryResponse) {
        // 마커의 영역을 저장하는 변수 생성
        val latLngBounds = LatLngBounds.Builder()

        for (lib in libraryResponse.SeoulPublicLibraryInfo.row) {
            val position = LatLng(lib.XCNTS.toDouble(), lib.YDNTS.toDouble())
            val marker = MarkerOptions().position(position).title(lib.LBRRY_NAME)
            mMap.addMarker(marker)

            // 지도에 마커를 추가한구 마커의 위치를 latLngBounds에 추가
            latLngBounds.include(marker.position)
        }

        // 마커의 영역을 기반으로 카메라 이동
        val bounds = latLngBounds.build()
        val padding = 0
        val updated = CameraUpdateFactory.newLatLngBounds(bounds, padding)

        mMap.moveCamera(updated)
    }
}