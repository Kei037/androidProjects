package com.kei037.deagufacility

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
import com.kei037.deagufacility.databinding.ActivityMapsBinding
import com.kei037.deagufacility.retrofit.FacilityResponse
import com.kei037.deagufacility.retrofit.FacilityService
import com.kei037.deagufacility.retrofit.RetrofitConnection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.awaitResponse

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

        loadFacility()
    }

    private fun loadFacility() {
        // 위도, 경도, 서비스키 설정 (위도,경도는 2.28 공원 기준)
        val lat = 35.86921437
        val lot = 128.5971838
        val serviceKey = "eClc8eKTygrd+ejSIWt/bEA7ZN4R7xuAnGOIVXmUfHlDlCC/jZtjwaiFe1mjMj770Rwkd9mGI26k9nMogdJoeg=="

        // Retrofit 객체 생성
        val retrofitAPI = RetrofitConnection.getInstance().create(FacilityService::class.java)

        retrofitAPI.getFacility(serviceKey, lat, lot,1, 200, "json", 20).enqueue(object : Callback<FacilityResponse> { // 서버로부터 데이터를 가져옴
            override fun onResponse(
                call: Call<FacilityResponse>,
                response: Response<FacilityResponse>
            ) {
                showFacility(response.body() as FacilityResponse)
            }

            override fun onFailure(call: Call<FacilityResponse>, t: Throwable) {
                Toast.makeText(baseContext, "서버에서 데이터를 가져올 수 없습니다.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showFacility(facilityResponse: FacilityResponse) {
        // 마커의 영역을 저장하는 변수 생성
        val latLngBounds = LatLngBounds.Builder()

        for (facility in facilityResponse.body.items.item) {
            val position = LatLng(facility.lat.toDouble(), facility.lot.toDouble())
            val marker = MarkerOptions().position(position).title(facility.parkNm)
            mMap.addMarker(marker)

            // 지도에 마커를 추가한구 마커의 위치를 latLngBounds에 추가
            latLngBounds.include(position)
        }

        // 마커의 영역을 기반으로 카메라 이동
        val bounds = latLngBounds.build()
        val padding = 0
        val updated = CameraUpdateFactory.newLatLngBounds(bounds, padding)

        mMap.moveCamera(updated)
    }
}