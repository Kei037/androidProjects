package com.kei037.retrofit_ex01

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.core.content.ContextCompat

class LocationProvider(val context: Context) {

    // Location 클래스는 위도, 경도, 고도와 같이 위치에 관련된 전보를 가지고 있는 데이터 클래스
    private var location: Location? = null

    // Location Manager는 시스템 위치 서비스에 접근을 제공하는 클래스
    private var locationManager: LocationManager? = null

    init {
        // 초괴화 시에 위치를 가져옵니다.
        getLocation()
    }

    private fun getLocation(): Location? {
        try {
            // 먼저 위치 시스템 서비스를 가져옴
            locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

            var gpsLocation: Location? = null
            var networkLocation: Location? = null

            // GPS Provider와 Network Provider 활성화 되어있는지 확인
            val isGPSEnabled: Boolean =
                locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)
            val isNetworkEnabled: Boolean =
                locationManager!!.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

            if (!isGPSEnabled && !isNetworkEnabled) {
                // GPS와 네트워크 둘다 사용 불가능한 경우 null을 반환
                return null
            } else {
                // 권환 확인

                // ACCESS_COARSE_LOCATION 보다 더 정밀한 위치를 얻을 수 있음
                val hasFineLocationPermission = ContextCompat.checkSelfPermission(context,
                                                Manifest.permission.ACCESS_FINE_LOCATION)
                // 도시 Block 단위의 정밀도의 위치 정보를 얻을 수 있음
                val hasCoarseLocationPermission = ContextCompat.checkSelfPermission(context,
                                                Manifest.permission.ACCESS_COARSE_LOCATION)
                // 만약 위 두개 권한 없다면 null 반환
                if (hasFineLocationPermission != PackageManager.PERMISSION_GRANTED ||
                    hasCoarseLocationPermission != PackageManager.PERMISSION_GRANTED) {
                    return null
                }

                // 네트워크를 통환 위치 파악이 가능한 경우에 위치를 가져옴
                if (isNetworkEnabled) {
                    networkLocation =
                        locationManager?.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                }

                if (isGPSEnabled) {
                    gpsLocation = locationManager?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                }

                if (gpsLocation != null && networkLocation != null) {
                    // 만약 두 개 위치가 있다면 정확도 높은 것으로 선택
                    if (gpsLocation.accuracy > networkLocation!!.accuracy) {
                        location = gpsLocation
                        return location
                    } else {
                        location = networkLocation
                        return location
                    }
                } else {
                    // 만약 가능한 위치 정보가 한 개만 있는 경우
                    if (gpsLocation != null) {
                        location = gpsLocation
                    }
                    if (networkLocation != null) {
                        location = networkLocation
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return location
    }

    fun getLocationLatitude(): Double {
        // 위도 정보를 가져옴
        return location?.latitude ?: 0.0
    }

    fun getLocationLongitude(): Double {
        // 경도 정보를 가져옴
        return location?.longitude ?: 0.0
    }
}