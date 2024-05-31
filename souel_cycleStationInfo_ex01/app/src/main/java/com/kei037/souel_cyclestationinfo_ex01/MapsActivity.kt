package com.kei037.souel_cyclestationinfo_ex01

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.kei037.souel_cyclestationinfo_ex01.databinding.ActivityMapsBinding
import com.kei037.souel_cyclestationinfo_ex01.retrofit.CycleResponse
import com.kei037.souel_cyclestationinfo_ex01.retrofit.CycleService
import com.kei037.souel_cyclestationinfo_ex01.retrofit.RetrofitConnection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

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

        loadCycle()
        mMap.setOnMarkerClickListener {
            if (it.tag != null) {
                var url = it.tag as String

                // 검색을위한 URL 파라미터값 한글사용 인코딩
                val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())

                // Google 검색 URL 생성
                val searchUrl = "https://www.google.com/search?q=$encodedUrl"

                Log.i("URL ==== ", searchUrl)
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(searchUrl))
                startActivity(intent)
            }
            true
        }
    }
    private fun loadCycle() {
        val my_api_key = "48704b64446a756e3637476d684e61"

        val retrofitAPI = RetrofitConnection.getInstance().create(CycleService::class.java)
        retrofitAPI.getCycle(my_api_key).enqueue(object : Callback<CycleResponse> {
            override fun onResponse(
                call: Call<CycleResponse>,
                response: Response<CycleResponse>
            ) {
                showCycle(response.body() as CycleResponse)
            }

            override fun onFailure(call: Call<CycleResponse>, t: Throwable) {
                Toast.makeText(baseContext, "서버에서 데이터를 가져올 수 없습니다.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showCycle(cycleResponse: CycleResponse) {
        val latLngBounds = LatLngBounds.Builder()

        for (cycle in cycleResponse.stationInfo.row) {
            // 위도와 경도가 0인 경우는 제외
            if (cycle.STA_LAT.toDouble() == 0.0 || cycle.STA_LONG.toDouble() == 0.0) {
                continue
            }

            // 위도와 경도를 LatLng 객체로 변환
            val positon = LatLng(cycle.STA_LAT.toDouble(), cycle.STA_LONG.toDouble())
            val marker = MarkerOptions().position(positon).title(cycle.RENT_NM)

            val obj = mMap.addMarker(marker)
            obj?.tag = cycle.STA_ADD1


            latLngBounds.include(positon) // LatLngBounds 객체에 포함
        }
        val bounds = latLngBounds.build()
        val padding = 0
        val updated = CameraUpdateFactory.newLatLngBounds(bounds, padding)

        mMap.moveCamera(updated)
    }
}