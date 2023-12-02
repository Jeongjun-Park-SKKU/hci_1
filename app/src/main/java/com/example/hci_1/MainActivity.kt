package com.example.hci_1

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.hci_1.ui.theme.Hci_1Theme
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.Face
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetector
import com.google.mlkit.vision.face.FaceDetectorOptions
import java.util.concurrent.TimeUnit
import androidx.camera.view.PreviewView
import android.content.Context
import android.util.AttributeSet
import android.media.AudioManager
import android.os.Handler
import android.os.Looper


class MainActivity : ComponentActivity() {
    private lateinit var detector: FaceDetector
    private lateinit var viewFinder: PreviewView
    private lateinit var graphicOverlay: GraphicOverlay

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (allPermissionsGranted()) {
            startCamera()
        } else {
            ActivityCompat.requestPermissions(
                this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS
            )
        }

        setContent {
            Hci_1Theme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    CameraPreview()
                    CustomOverlay()
                }
            }
        }
        setupFaceDetector()
    }

    private fun adjustBrightness(level: Float) {
        val window = window
        val layoutParams = window.attributes
        layoutParams.screenBrightness = level  // 0.0f ~ 1.0f 사이의 값
        window.attributes = layoutParams
    }

    private fun adjustVolume(level: Int) {
        val audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, level, 0)
    }


    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(viewFinder.surfaceProvider)
                }

            val imageAnalyzer = ImageAnalysis.Builder()
                .build()
                .also {
                    it.setAnalyzer(ContextCompat.getMainExecutor(this), YourAnalyzer(this))
                }

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    this, CameraSelector.DEFAULT_FRONT_CAMERA, preview, imageAnalyzer
                )
            } catch (exc: Exception) {
                Log.e(TAG, "Use case binding failed", exc)
            }

        }, ContextCompat.getMainExecutor(this))
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            baseContext, it
        ) == PackageManager.PERMISSION_GRANTED
    }

    @Composable
    fun CameraPreview() {
        Box(modifier = Modifier.fillMaxSize()) {
            AndroidView(factory = { context ->
                PreviewView(context).also {
                    viewFinder = it
                }
            })
        }
    }

    @Composable
    fun CustomOverlay() {
        Box(modifier = Modifier.fillMaxSize()) {
            AndroidView(factory = { context ->
                GraphicOverlay(context, null).also {
                    graphicOverlay = it
                }
            })
        }
    }

    private fun setupFaceDetector() {
        val options = FaceDetectorOptions.Builder()
            .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_FAST)
            .setContourMode(FaceDetectorOptions.CONTOUR_MODE_ALL)
            .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
            .build()

        detector = FaceDetection.getClient(options)
    }

    private inner class YourAnalyzer(private val mainActivity: MainActivity) : ImageAnalysis.Analyzer {
        private var lastAnalyzedTimestamp = 0L
        private var lastBlinkState = false
        private var blinkStartTime = 0L
        private var blinkDuration = 0L
        private val handler = Handler(Looper.getMainLooper())
        private var currentBrightnessLevel = 1.0f // 초기 밝기 수준
        private var currentVolumeLevel = 8 // 초기 볼륨 수준
        private var isAdjustingBrightnessAndVolume = false

        private val adjustBrightnessAndVolumeRunnable = object : Runnable {
            override fun run() {
                if (currentBrightnessLevel > 0.1f) currentBrightnessLevel -= 0.1f
                if (currentVolumeLevel > 1) currentVolumeLevel -= 1

                mainActivity.runOnUiThread {
                    mainActivity.adjustBrightness(currentBrightnessLevel)
                    mainActivity.adjustVolume(currentVolumeLevel)
                }

                handler.postDelayed(this, 3000) // 3초 후에 다시 실행
                isAdjustingBrightnessAndVolume = true
            }
        }

        fun getLastBlinkState(): Boolean {
            return lastBlinkState
        }

        fun setLastBlinkState(newState: Boolean) {
            if (newState && !lastBlinkState) {
                // 눈을 감기 시작했을 때
                blinkStartTime = System.currentTimeMillis()
                if (!isAdjustingBrightnessAndVolume) {
                    handler.post(adjustBrightnessAndVolumeRunnable)
                }
            } else if (!newState && lastBlinkState) {
                // 눈을 뜰 때
                blinkDuration = System.currentTimeMillis() - blinkStartTime
                graphicOverlay.setBlinkDuration(blinkDuration)
                blinkDuration = 0 // 눈을 뜨면 시간을 0으로 초기화
                if (isAdjustingBrightnessAndVolume) {
                    handler.removeCallbacks(adjustBrightnessAndVolumeRunnable)
                    isAdjustingBrightnessAndVolume = false
                    // 밝기와 볼륨을 원하는 수준으로 설정
                    currentBrightnessLevel = 0.8f // 80% 밝기
                    currentVolumeLevel = 5 // 예시로, 30% 볼륨 (실제 값은 장치에 따라 다를 수 있음)
                    mainActivity.runOnUiThread {
                        mainActivity.adjustBrightness(currentBrightnessLevel)
                        mainActivity.adjustVolume(currentVolumeLevel)
                    }
                }
            }
            lastBlinkState = newState
        }

        @ExperimentalGetImage
        override fun analyze(imageProxy: ImageProxy) {
            val currentTime = System.currentTimeMillis()
            if (lastBlinkState) {
                val currentBlinkDuration = currentTime - blinkStartTime
                graphicOverlay.setBlinkDuration(currentBlinkDuration)

                if (!isAdjustingBrightnessAndVolume && currentBlinkDuration >= 3000) {
                    handler.post(adjustBrightnessAndVolumeRunnable)
                    blinkStartTime = currentTime // 타이머 리셋
                }
            } else {
                if (lastBlinkState) {
                    handler.removeCallbacks(adjustBrightnessAndVolumeRunnable)
                    isAdjustingBrightnessAndVolume = false
                    currentBrightnessLevel = 1.0f // 밝기 초기화
                    currentVolumeLevel = 10 // 볼륨 초기화
                    lastBlinkState = false
                }
                blinkDuration = 0
            }

            if (currentTime - lastAnalyzedTimestamp >= TimeUnit.SECONDS.toMillis(1)) {
                val mediaImage = imageProxy.image ?: return
                val rotationDegrees = imageProxy.imageInfo.rotationDegrees
                val image = InputImage.fromMediaImage(mediaImage, rotationDegrees)

                detector.process(image)
                    .addOnSuccessListener { faces: List<Face> ->
                        graphicOverlay.clear()
                        for (face in faces) {
                            val faceGraphic = FaceGraphic(graphicOverlay, face, this)
                            graphicOverlay.add(faceGraphic)
                        }
                        lastAnalyzedTimestamp = currentTime
                    }
                    .addOnFailureListener { e ->
                        Log.e("FaceDetection", "Face detection failed", e)
                    }
                    .addOnCompleteListener {
                        imageProxy.close()
                    }
            } else {
                imageProxy.close()
            }
        }
    }


    private class FaceGraphic(private val overlay: GraphicOverlay, private val face: Face, private val analyzer: YourAnalyzer) : GraphicOverlay.Graphic(overlay) {

        private val facePositionPaint: Paint = Paint().apply {
            color = Color.GREEN
            style = Paint.Style.STROKE
            strokeWidth = 4f
        }

        override fun draw(canvas: Canvas) {
            val isLeftEyeOpen = (face.leftEyeOpenProbability ?: 0f) > 0.5
            val isRightEyeOpen = (face.rightEyeOpenProbability ?: 0f) > 0.5

            if (!isLeftEyeOpen && !isRightEyeOpen) {
                if (!analyzer.getLastBlinkState()) {
                    analyzer.setLastBlinkState(true)
                    overlay.incrementBlinkCount()
                }
            } else {
                if (analyzer.getLastBlinkState()) {
                    overlay.resetBlinkCount()
                    analyzer.setLastBlinkState(false)
                }
            }

            facePositionPaint.color = if (isLeftEyeOpen && isRightEyeOpen) {
                Color.GREEN
            } else {
                Color.RED
            }

            val contours = face.allContours
            for (contour in contours) {
                for (point in contour.points) {
                    canvas.drawCircle(point.x, point.y, 4f, facePositionPaint)
                }
            }
        }
    }

    companion object {
        private const val TAG = "CameraXApp"
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    }
}

class GraphicOverlay(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val lock = Any()
    private val graphics: MutableList<Graphic> = mutableListOf()
    private var blinkCount = 0
    private var blinkDuration = 0L

    private fun drawBlinkCount(canvas: Canvas) {
        val textPaint = Paint().apply {
            color = Color.BLACK
            textSize = 40f
        }
        canvas.drawText("Blink Count: $blinkCount", width - 400f, 100f, textPaint)
    }

    abstract class Graphic(private val overlay: GraphicOverlay) {
        abstract fun draw(canvas: Canvas)
    }

    fun setBlinkDuration(duration: Long) {
        blinkDuration = duration
        postInvalidate()
    }

    fun resetBlinkCount() {
        synchronized(lock) {
            blinkCount = 0
        }
        postInvalidate()
    }

    fun add(graphic: Graphic) {
        synchronized(lock) {
            graphics.add(graphic)
        }
        postInvalidate()
    }

    fun clear() {
        synchronized(lock) {
            graphics.clear()
        }
        postInvalidate()
    }

    fun incrementBlinkCount() {
        synchronized(lock) {
            blinkCount++
        }
        postInvalidate()
    }

    private fun drawBlinkDuration(canvas: Canvas) {
        val textPaint = Paint().apply {
            color = Color.BLACK
            textSize = 40f
        }
        canvas.drawText("Blink Duration: ${blinkDuration / 1000f} seconds", width - 400f, 150f, textPaint)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        synchronized(lock) {
            for (graphic in graphics) {
                graphic.draw(canvas)
            }
        }
        drawBlinkCount(canvas)
        drawBlinkDuration(canvas)
    }
}