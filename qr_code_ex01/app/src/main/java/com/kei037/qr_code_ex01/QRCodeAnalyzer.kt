package com.kei037.qr_code_ex01

import android.annotation.SuppressLint
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage

class QRCodeAnalyzer(val onDetectListener: OnDetectListener) : ImageAnalysis.Analyzer {
    private val scanner = BarcodeScanning.getClient() // 바코드 스캐닝 객체 생성

    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image
        if (mediaImage != null) {
            val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
            scanner.process(image)
                .addOnSuccessListener { qrCodes ->
                    for (qrCode in qrCodes) {
                        onDetectListener.onDetect(qrCode.rawValue ?: "")
                    }
                }
                .addOnFailureListener { // 실패 시
                    it.printStackTrace()
                }
                .addOnCompleteListener {
                    imageProxy.close()
                }
        }
    }
}