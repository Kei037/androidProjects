package com.kei037.qr_code_ex01

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.google.common.util.concurrent.ListenableFuture
import com.kei037.qr_code_ex01.databinding.ActivityMainBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>
    private var PERMISSION_REQUEST_CODE = 1 // 권한 요청 코드
    private var PERMISSIONS_REQUIRED = arrayOf(Manifest.permission.CAMERA) // 요청할 권한 목록

    private var isDetected = false // QR 코드 검출 여부

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!hasPermissions(this)) { // 카메라 권한을 요청
            // 권한이 없을 경우, 권한 요청
            requestPermissions(PERMISSIONS_REQUIRED, PERMISSION_REQUEST_CODE)
        } else { // 권한이 있을 경우, 카메라 시작
            startCamera()
        }
    }

    // 이미지 분석이 실시간으로 계속해서 이루어지기 때문에 onDetect()가 계속 호출됨으로 멈춤
    override fun onResume() {
        super.onResume()
        isDetected = false
    }

    fun hasPermissions(context: Context) = PERMISSIONS_REQUIRED.all {
        // 권한 유뮤 확인
        ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
    }

    // 권한 요청 함수
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        /* 권한 요청 콜백 함수 */
        if (requestCode == PERMISSION_REQUEST_CODE) { // 권한 요청 코드가 일치할 경우
            if (PackageManager.PERMISSION_GRANTED == grantResults.firstOrNull()) { // 권한 요청이 승인되었을 경우
                Toast.makeText(applicationContext, "권한 요청이 승인되었습니다.", Toast.LENGTH_LONG).show()
                startCamera()
            } else {
                Toast.makeText(applicationContext, "권한 요청이 거부되었습니다.", Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }

    // 이미지 분석 객체 생성 함수
    fun getImageAnalyzer() : ImageAnalysis {
        val cameraExecutor : ExecutorService = Executors.newSingleThreadExecutor() // 이미지 분석 스레드 생성
        val imageAnalysis = ImageAnalysis.Builder().build()

        // 이미지 분석 객체 생성 및 설정
        imageAnalysis.setAnalyzer(cameraExecutor,
            QRCodeAnalyzer(object : OnDetectListener { // 이미지 분석 결과 콜백 함수
                override fun onDetect(msg: String) {
                    if (!isDetected) {
                        isDetected = true // 데이터가 감지되었으므로 true로 변경

                        val intent = Intent(applicationContext, ResultActivity::class.java)
                        intent.putExtra("msg", msg)
                        startActivity(intent)
                    }
//                    Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
                }
            })
        )
        return imageAnalysis
    }

    // 카메라 시작 함수
    fun startCamera() {
        cameraProviderFuture = ProcessCameraProvider.getInstance(this) // 카메라 프로바이더 객체 생성
        cameraProviderFuture.addListener(Runnable { // 카메라 프로바이더 객체 생성 완료 시
            // 카메라 프로바이더 객체 가져오기
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            val preview = getPreview() // 미리보기 객체 생성
            val imageAnalysis = getImageAnalyzer() // 이미지 분석 객체 생성
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA // 후면 카메라 선택

            cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageAnalysis) // 라이프사이클에 바인딩
        }, ContextCompat.getMainExecutor(this)) // 메인 스레드에서 실행
    }

    // 미리보기 객체 생성 함수
    fun getPreview(): Preview {
        // 미리보기 객체 반환
        val preview : Preview = Preview.Builder().build()
        preview.setSurfaceProvider(binding.barcodePreview.getSurfaceProvider()) // 미리보기 화면 설정
        return preview
    }

}