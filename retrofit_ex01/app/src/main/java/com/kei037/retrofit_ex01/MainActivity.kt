package com.kei037.retrofit_ex01

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.kei037.retrofit_ex01.databinding.ActivityMainBinding
import com.kei037.retrofit_ex01.retrofit.AirQualityResponse
import com.kei037.retrofit_ex01.retrofit.AirQualityService
import com.kei037.retrofit_ex01.retrofit.RetrofitConnection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    // 런타임 권한 요청시 필요한 요청 코드
    private val PERMISSIONS_REQUEST_CODE = 100

    // 요청할 권한 리스트
    private var REQUIRED_PERMISSIONS = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    // 위치 서비스 요청시 필요한 런처.
    private lateinit var getGPSPermissionLauncher: ActivityResultLauncher<Intent>

    // 위도,경도를 가지고올 때 사용
    private lateinit var locationProvider: LocationProvider

    // 위도와 경도를 저장
    var latitude: Double = 0.0
    var longitude: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkAllPermissions() // 권한 확인
        updateUI()
        setRefreshButton()
    }

    private fun setRefreshButton() {
        Log.i("MainActivity", "setRefreshButton")
        binding.btnRefresh.setOnClickListener {
            updateUI()
        }
    }

    // 모든 권한을 확인하는 함수
    private fun checkAllPermissions() {
        if (!isLocationServicesAvailable()) {
            showDialogForLocationServiceSetting()
        } else {
            isRunTimePermissionsGranted()
        }
    }

    // GPS 사용 가능 여부 확인
    private fun isLocationServicesAvailable(): Boolean {
        val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
        // GPS, 네트워크 사용 가능 여부를 확인 후 하나라도 켜져있으면 반환
        return (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || // GPS 사용 가능 여부
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) // 네트워크 사용 가능 여부
    }

    /**
     * 런타임 권한이 허용되었는지 확인하는 함수
     * 허용되지 않았다면 권한 요청
     * 허용되었다면 위치 정보를 가져옴
     */
    private fun isRunTimePermissionsGranted() {
        val hasFineLocationPermission = ContextCompat.checkSelfPermission(
            this@MainActivity,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        val hasCoarseLocationPermission = ContextCompat.checkSelfPermission(
            this@MainActivity,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        if (hasFineLocationPermission != PackageManager.PERMISSION_GRANTED ||
            hasCoarseLocationPermission != PackageManager.PERMISSION_GRANTED) {
            // 권한이 한 개라도 없다면 퍼미션 요청
            ActivityCompat.requestPermissions(
                this@MainActivity,
                REQUIRED_PERMISSIONS,
                PERMISSIONS_REQUEST_CODE
            )
        }
    }

    /**
     * 사용자에게 위치 서비스를 켜라고 요청하는 대화상자를 띄움
     */
    private fun showDialogForLocationServiceSetting() {
        // 먼저 ActivityResultLauncher를 설정
        // 이 런처를 이용하여 결과 값을 리턴해야하는 인텐트를 실행할 수 있음
        getGPSPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) { // 사용자가 위치 서비스를 켰다면
                if (isLocationServicesAvailable()) {
                    isRunTimePermissionsGranted() // 런타임 권한 확인
                } else { // 사용자가 위치 서비스를 켜지 않았다면 앱 종료
                    Toast.makeText(this@MainActivity, "위치 서비스를 켜야합니다.", Toast.LENGTH_LONG).show()
                    finish()
                }
            }
        }

        // 사용자에게 의사를 물어보는 대화상자 생성
        val builder: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
        builder.setTitle("위치 서비스 비활성화")
        builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n위치 서비스를 켜시겠습니까?")
        builder.setCancelable(true) // 대화상자 창 바깥 터처시 창 닫힘

        // 확인 버튼 설정
        builder.setPositiveButton("설정", DialogInterface.OnClickListener { dialog, id ->
            val callGPSSettingIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            getGPSPermissionLauncher.launch(callGPSSettingIntent)
        })
        // 취소 버튼 설정
        builder.setNegativeButton("취소", DialogInterface.OnClickListener { dialog, id ->
            dialog.cancel()
            Toast.makeText(this@MainActivity, "위치 서비스를 켜야합니다.", Toast.LENGTH_LONG).show()
            finish()
        })
        builder.create().show()
    }

    /**
     * 위치 정보를 가져오는 함수
     * 위치 정보를 가져오는데 성공하면 위도, 경도를 가져옴
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSIONS_REQUEST_CODE &&
            grantResults.size == REQUIRED_PERMISSIONS.size) {
            // 요청코드가 PERMISSIONS_REQUEST_CODE 이고, 요청한 퍼미션 개수만큼 수신되었다면
            var checkResult = true

            // 모든 퍼미션을 허용했는지 체크
            for (result in grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    checkResult = false
                    break
                }
            }
            if (checkResult) {
                // 위치값을 가져올 수 있음
                updateUI()
            } else { // 퍼미션이 거부되었다면 앱을 종료
                Toast.makeText(
                    this@MainActivity,
                    "퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요.",
                    Toast.LENGTH_LONG).show()
                finish()
            }

        }
    }

    /**
     * 가져온 주소를 UI에 업데이트
     */
    private fun updateUI() {
        locationProvider = LocationProvider(this@MainActivity)

        // 위도, 경도 정보를 가져옴
        if (latitude == 0.0 || longitude == 0.0) {
            latitude = locationProvider.getLocationLatitude()
            longitude = locationProvider.getLocationLongitude()
        }
        // Emulator에서 작동시 주석 해제
//        latitude = 35.87222
//        longitude = 128.60250

        if (latitude != 0.0 || longitude != 0.0) {
            // 1. 현재 위치를 가져오고 UI 업데이트
            val address = getCurrentAddress(latitude, longitude)
            // 주소가 null이 아닐 경우 UI 업데이트
            address?.let {
                binding.textViewLocationTitle.text = "${it.thoroughfare}" // 예시: 역삼 1동
                binding.textViewLocationSubTitle.text = "${it.countryName} ${it.adminArea}" // 예시: 대한민국 서울특별시
            }

            // 2. 현재 미세먼지 농도 가져오고 UI 업데이트
            getAirQualityData(latitude, longitude)
            Log.i("updateUI", "updateUI() 호출됨")
        } else {
            Toast.makeText(
                this@MainActivity,
                "위치 정보를 가져오는데 실패했습니다.\n 새로고침을 눌러주세요.",
                Toast.LENGTH_LONG).show()
        }

    }

    /**
     * 미세먼지 정보를 가져오는 함수
     * @param latitude 위도
     * @param longitude 경도
     * @see AirQualityResponse
     * @see AirQualityService
     * @see RetrofitConnection
     * @see Callback
     */
    private fun getAirQualityData(latitude: Double, longitude: Double) {
        // 레트로핏 클래스를 이용하여 미세먼지 오염 정보를 가져옴
        // 레트로핏 객체를 이용하면 AirQualityService 인터페이스 구현체를 가져올 수 있음
        val retrofitAPI = RetrofitConnection.getInstance().create(AirQualityService::class.java)
        Log.i("getAirQualityData", "getAirQualityData() 호출됨")
        retrofitAPI.getAirQualityData(
            latitude.toString(),
            longitude.toString(),
            "98f39afa-a1b5-472e-ba16-8325d7a3b6f7"
        ).enqueue(object : Callback<AirQualityResponse> {
            override fun onResponse(
                call: Call<AirQualityResponse>,
                response: Response<AirQualityResponse>
            ) {
                // 정상적인 Response가 왔다면 UI 업데이트
                if (response.isSuccessful) {
                    Toast.makeText(this@MainActivity, "최신 정보 업데이트 완료!", Toast.LENGTH_SHORT).show()
                    // 만약 response.body()가 null이 아니라면 updateAirUI()
                    response.body()?.let { updateAirUI(it) }
                } else {
                    Toast.makeText(this@MainActivity, "업데이트에 실패했습니다.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<AirQualityResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "업데이트에 실패했습니다.", Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }
        })
    }

    /**
     * 가져온 미세먼지 정보를 UI에 업데이트
     * @param airQualityData 미세먼지 정보
     */
    private fun updateAirUI(airQualityData: AirQualityResponse) {
        Log.i("updateAirUI", "updateAirUI() 호출됨")
        // 가져온 데이터 정보를 바탕으로 화면을 업데이트
        val pollutionData = airQualityData.data.current.pollution

        // 수치 지정 (메인 화면 가운데 숫자)
        binding.textViewCount.text = pollutionData.aqius.toString()

        // 측정된 날짜 지정
        val dateTime =
            ZonedDateTime.parse(pollutionData.ts).withZoneSameInstant(ZoneId.of("Asia/Seoul"))
        val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

        binding.textViewCheckTime.text = dateTime.format(dateFormatter).toString()

        // UI 변경
        when (pollutionData.aqius) {
            in 0..50 -> {
                binding.textViewTitle.text = "좋음"
                binding.imageBg.setImageResource(R.drawable.bg_good)
            }
            in 51..150 -> {
                binding.textViewTitle.text = "보통"
                binding.imageBg.setImageResource(R.drawable.bg_soso)
            }
            in 151..200 -> {
                binding.textViewTitle.text = "나쁨"
                binding.imageBg.setImageResource(R.drawable.bg_bad)
            }
            else -> {
                binding.textViewTitle.text = "매우 나쁨"
                binding.imageBg.setImageResource(R.drawable.bg_worst)
            }
        }
    }

    /**
     * 위도, 경도를 기반으로 주소를 가져옴
     * @param latitude 위도
     * @param longitude 경도
     * @return Address 주소
     */
    fun getCurrentAddress(latitude: Double, longitude: Double): Address? {
        // 위도, 경도를 기준으로 실제 주소를 가져옴
        val geocoder = Geocoder(this@MainActivity, Locale.getDefault())
        // Address 객체는 주소와 관련된 여러 정보를 가지고 있음. android.location.Address 패키지 참고
        val addresses: List<Address>?

        addresses = try {
            // Geocoder 객체를 이용하여 위도와 경도로부터 리스트를 가져옴
            geocoder.getFromLocation(latitude, longitude, 7)
        } catch (ioException: IOException) {
            Toast.makeText(this, "지오코더 서비스 사용불가합니다.", Toast.LENGTH_LONG).show()
            return null
        } catch (illegalArgumentException: IllegalArgumentException) {
            Toast.makeText(this, "잘못된 GPS 좌표입니다.", Toast.LENGTH_LONG).show()
            return null
        }

        // 에러는 아니지만 주소가 발견되지 않은 경우
        if (addresses == null || addresses.size == 0) {
            Toast.makeText(this, "주소가 발견되지 않았습니다.", Toast.LENGTH_LONG).show()
            return null
        }

        return getCurrentAddressNullCheck(addresses)
    }

    /**
     * 주소 리스트 중에 thoroughfare가 null이 아닌 주소를 반환
     * @param addresses 주소 리스트
     * @return Address 주소
     */
    private fun getCurrentAddressNullCheck(addresses: List<Address>): Address {
        var address = addresses[0]
        for (item in addresses) {
            if (item.thoroughfare != null) {
                address = item
                break
            }
        }

        return address
    }
}