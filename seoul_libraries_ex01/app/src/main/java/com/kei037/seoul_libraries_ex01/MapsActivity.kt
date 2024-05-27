package com.kei037.seoul_libraries_ex01

import android.content.Intent
import android.net.Uri
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
import com.google.maps.android.clustering.ClusterManager
import com.kei037.seoul_libraries_ex01.databinding.ActivityMapsBinding
import com.kei037.seoul_libraries_ex01.retrofit.LibraryResponse
import com.kei037.seoul_libraries_ex01.retrofit.LibraryService
import com.kei037.seoul_libraries_ex01.retrofit.RetrofitConnection
import com.kei037.seoul_libraries_ex01.retrofit.Row
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var clusterManager: ClusterManager<Row>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 서울 공공 데이터 API를 통해 서울시 도서관 정보를 받아옴
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

        // 클러스터 매니저 세팅
        clusterManager = ClusterManager(this, mMap)
        mMap.setOnCameraIdleListener(clusterManager) // 카메라가 멈추면 클러스터링 시작
        mMap.setOnMarkerClickListener(clusterManager) // 마커 클릭 시 클러스터링 시작
        loadLibrary()

        // 해당 마커의 홈페이지로 이동
//        mMap.setOnMarkerClickListener {
//            if (it.tag != null) { // 마커의 tag가 null이 아니면
//                var url = it.tag as String // tag를 String으로 변환
//                if (!url.startsWith("http")) { // http로 시작하지 않으면 붙여줌
//                    url = "http://$url"
//                }
//                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)) // 해당 url로 이동
//                startActivity(intent)
//            }
//            true
//        }
    }
    private fun loadLibrary() {
        val my_api_key = "48704b64446a756e3637476d684e61"

        // Retrofit 객체 생성
        val retrofitAPI = RetrofitConnection.getInstance().create(LibraryService::class.java)
        retrofitAPI.getLibrary(my_api_key).enqueue(object : Callback<LibraryResponse> { // 서버로부터 데이터를 가져옴
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

        for (library in libraryResponse.SeoulPublicLibraryInfo.row) {
            clusterManager.addItem(library)

            val position = LatLng(library.XCNTS.toDouble(), library.YDNTS.toDouble())
//            val marker = MarkerOptions().position(position).title(library.LBRRY_NAME)
//            mMap.addMarker(marker)

//            val obj = mMap.addMarker(marker)
//            obj?.tag = library.HMPG_URL

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