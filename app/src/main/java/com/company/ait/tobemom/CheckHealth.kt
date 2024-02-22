package com.company.ait.tobemom

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.company.ait.tobemom.utils.RetrofitClient2
import com.company.ait.tobemom.utils.RetrofitObject
import com.squareup.okhttp.MediaType
import com.squareup.okhttp.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class CheckHealth : AppCompatActivity() {

    private lateinit var checkHealthBackBtn: ImageButton
    private lateinit var dateTextView: TextView
    private lateinit var WeightEdit: EditText
    private lateinit var Emoji1: ImageButton; private lateinit var Emoji2: ImageButton
    private lateinit var Emoji3: ImageButton; private lateinit var Emoji4: ImageButton
    private lateinit var Emoji5: ImageButton; private lateinit var Emoji6: ImageButton
    private lateinit var Emoji7: ImageButton; private lateinit var Emoji8: ImageButton
    private lateinit var Emoji9: ImageButton; private lateinit var Emoji10: ImageButton
    private lateinit var Emoji11: ImageButton; private lateinit var Emoji12: ImageButton
    private lateinit var Emoji13: ImageButton; private lateinit var Emoji14: ImageButton
    private lateinit var Emoji15: ImageButton; private lateinit var Emoji16: ImageButton
    private lateinit var Emoji17: ImageButton; private lateinit var Emoji18: ImageButton
    private lateinit var Emoji19: ImageButton; private lateinit var Emoji20: ImageButton
    private lateinit var Emoji21: ImageButton; private lateinit var Emoji22: ImageButton
    private lateinit var Emoji23: ImageButton; private lateinit var Emoji24: ImageButton
    private lateinit var ETWriteHealth: EditText
    private lateinit var SubmitBtn: AppCompatButton
    // STT
    private lateinit var MicBtn: ImageButton
    private var isRecording = false
    private var recordingThread: Thread? = null
    private var audioRecord: AudioRecord? = null
    private var recordingExecutor: ExecutorService? = null

    companion object {
        private const val TAG = "CheckHealth"
        private const val SAMPLE_RATE = 44100
        private const val CHANNEL_CONFIG = AudioFormat.CHANNEL_IN_MONO
        private const val AUDIO_FORMAT = AudioFormat.ENCODING_PCM_16BIT
        private const val RECORD_AUDIO_PERMISSION_REQUEST_CODE = 101
        private const val RECORD_AUDIO = android.Manifest.permission.RECORD_AUDIO
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_health)

        checkHealthBackBtn = findViewById(R.id.CheckHealth_back_btn)
        dateTextView = findViewById(R.id.dateTextView)
        WeightEdit = findViewById(R.id.weightEdit)
        Emoji1 = findViewById(R.id.emoji1); Emoji2 = findViewById(R.id.emoji2)
        Emoji3 = findViewById(R.id.emoji3); Emoji4 = findViewById(R.id.emoji4)
        Emoji5 = findViewById(R.id.emoji5); Emoji6 = findViewById(R.id.emoji6)
        Emoji7 = findViewById(R.id.emoji7); Emoji8 = findViewById(R.id.emoji8)
        Emoji9 = findViewById(R.id.emoji9); Emoji10 = findViewById(R.id.emoji10)
        Emoji11 = findViewById(R.id.emoji11); Emoji12 = findViewById(R.id.emoji12)
        Emoji13 = findViewById(R.id.emoji13); Emoji14 = findViewById(R.id.emoji14)
        Emoji15 = findViewById(R.id.emoji15); Emoji16 = findViewById(R.id.emoji16)
        Emoji17 = findViewById(R.id.emoji17); Emoji18 = findViewById(R.id.emoji18)
        Emoji19 = findViewById(R.id.emoji19); Emoji20 = findViewById(R.id.emoji20)
        Emoji21 = findViewById(R.id.emoji21); Emoji22 = findViewById(R.id.emoji22)
        Emoji23 = findViewById(R.id.emoji23); Emoji24 = findViewById(R.id.emoji24)
        ETWriteHealth = findViewById(R.id.EditwriteyourHealth)
        SubmitBtn = findViewById(R.id.submitBtn)

        //Speek To Text
        MicBtn = findViewById(R.id.btn_mic)
        // 마이크 권한 요청
        if (ContextCompat.checkSelfPermission(this, RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(RECORD_AUDIO), RECORD_AUDIO_PERMISSION_REQUEST_CODE)
        } else {
            // 마이크 권한이 이미 허용된 경우
            initializeAudioRecording()
        }
        startSTT()
        // 권한 요청
        requestPermission()

        //이미지 버튼 클릭 시의 버튼 배경색 변경
        val emojiButtons: Array<ImageButton> = Array(24) { index ->
            findViewById(resources.getIdentifier("emoji${index + 1}", "id", packageName))
        }

        for (emojiButton in emojiButtons) {
            emojiButton.setOnClickListener {
                emojiButton.isSelected = !emojiButton.isSelected
                // 배경색 변경 코드 추가
                updateButtonBackground(emojiButton)
            }
        }

        //날짜 표시
        displayCurrentDate()

        checkHealthBackBtn.setOnClickListener {
            val intent = Intent(this, ChecklistResult::class.java)
            startActivity(intent)
        }

        SubmitBtn.setOnClickListener {
            try {
                val weight = WeightEdit.text.toString().toFloat()
                val healthInfoList = getSelectedEmojis()
                val healthDiary = ETWriteHealth.text.toString().takeIf { it.isNotBlank() } ?: "No health diary provided"
                Log.d("checkHealth-diary", "${healthDiary}")

                // 사용자 입력 유효성 검사
                val checkHealthData = RetrofitClient2.CheckHealth(weight, healthInfoList, healthDiary)

                val token = getCurrentToken(this)
                val call = RetrofitObject.getRetrofitService.checkHealth("Bearer $token", checkHealthData)
                Log.d("currentToken","${token}")

                call.enqueue(object : Callback<RetrofitClient2.ResponseCheckHealth> {
                    override fun onResponse(
                        call: Call<RetrofitClient2.ResponseCheckHealth>,
                        response: Response<RetrofitClient2.ResponseCheckHealth>
                    ) {
                        Log.d("CheckHealth1", "${response.toString()}")
                        if (response.isSuccessful) {
                            val checkHealth = response.body()
                            // 성공적인 응답 처리
                            Log.d("CheckHealth2", "${checkHealth.toString()}")
                            moveToCheck()
                        } else {
                            // 서버 오류 처리
                            Log.e("서버 오류", response.errorBody()?.string() ?: "알 수 없는 오류")
                        }
                    }

                    override fun onFailure(
                        call: Call<RetrofitClient2.ResponseCheckHealth>,
                        t: Throwable
                    ) {
                        // 네트워크 실패 처리
                        Log.e("CheckHealth", "API 호출 실패: ${t.message}")
                    }
                })

            } catch (e: NumberFormatException) {
                // 무게가 유효한 float이 아닌 경우 처리
                Log.e("입력 오류", "유효하지 않은 무게 형식")
            }
        }
    }
    private fun getCurrentToken(context: Context): String?{
        val sharedPref = context.getSharedPreferences("TOBEMOM", Context.MODE_PRIVATE)
        val token = sharedPref.getString("token", null)
        Log.d("TokenDebug", "Token value: $token")
        return token

    }
    //배경색 변경 함수 추가
    private fun updateButtonBackground(button: ImageButton){
        if(button.isSelected){
            //선택된 경우 배경색 설정
            button.setBackgroundColor(ContextCompat.getColor(this, R.color.mainColor))
        }
        else{
            //선택이 해제된 경우 배경색 초기화
            button.setBackgroundColor(ContextCompat.getColor(this, R.color.subColor))
        }
    }
    private fun moveToCheck(){
        // submitBtn 클릭 시 checkFragment로 이동
        val checkCalFragment = CheckCalFragment()

        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.checkHealth,checkCalFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun getSelectedEmojis(): IntArray {
        val selectedEmojis = mutableListOf<Int>()

        //선택된 이모지 이미지 버튼들에 대한 로직을 추가
        //예를 들어, 선택된 경우 해당 이모지의 식별자를 배열에 추가
        if(Emoji1.isSelected) selectedEmojis.add(1)
        if(Emoji2.isSelected) selectedEmojis.add(2)
        if(Emoji3.isSelected) selectedEmojis.add(3)
        if(Emoji4.isSelected) selectedEmojis.add(4)
        if(Emoji5.isSelected) selectedEmojis.add(5)
        if(Emoji6.isSelected) selectedEmojis.add(6)
        if(Emoji7.isSelected) selectedEmojis.add(7)
        if(Emoji8.isSelected) selectedEmojis.add(8)
        if(Emoji9.isSelected) selectedEmojis.add(9)
        if(Emoji10.isSelected) selectedEmojis.add(10)
        if(Emoji11.isSelected) selectedEmojis.add(11)
        if(Emoji12.isSelected) selectedEmojis.add(12)
        if(Emoji13.isSelected) selectedEmojis.add(13)
        if(Emoji14.isSelected) selectedEmojis.add(14)
        if(Emoji15.isSelected) selectedEmojis.add(15)
        if(Emoji16.isSelected) selectedEmojis.add(16)
        if(Emoji17.isSelected) selectedEmojis.add(17)
        if(Emoji18.isSelected) selectedEmojis.add(18)
        if(Emoji19.isSelected) selectedEmojis.add(19)
        if(Emoji20.isSelected) selectedEmojis.add(20)
        if(Emoji21.isSelected) selectedEmojis.add(21)
        if(Emoji22.isSelected) selectedEmojis.add(22)
        if(Emoji23.isSelected) selectedEmojis.add(23)
        if(Emoji24.isSelected) selectedEmojis.add(24)

        return selectedEmojis.toIntArray()
    }

    private fun displayCurrentDate(){
        val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        dateTextView.text = currentDate
    }

    // STT
    private fun startSTT() {
        MicBtn.setOnClickListener {
            if (isRecording) {
                stopRecording()
            } else {
                startRecording()
            }
        }
    }

    private fun startRecording() {
        recordingExecutor = Executors.newSingleThreadExecutor()

        recordingExecutor?.execute {
            isRecording = true
            val minBufferSize = AudioRecord.getMinBufferSize(
                SAMPLE_RATE,
                CHANNEL_CONFIG,
                AUDIO_FORMAT
            )
            if (ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.RECORD_AUDIO
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: 권한이 없을 경우 처리
                return@execute
            }
            audioRecord = AudioRecord(
                MediaRecorder.AudioSource.MIC,
                SAMPLE_RATE,
                CHANNEL_CONFIG,
                AUDIO_FORMAT,
                minBufferSize
            )

            val audioData = ByteArrayOutputStream()

            try {
                audioRecord?.startRecording()
                val buffer = ByteArray(minBufferSize)
                while (isRecording) {
                    val bytesRead = audioRecord?.read(buffer, 0, minBufferSize) ?: -1
                    if (bytesRead > 0) {
                        audioData.write(buffer, 0, bytesRead)
                    }
                }
            } catch (e: IOException) {
                Log.e(TAG, "Error recording audio: ${e.message}")
            } finally {
                audioRecord?.stop()
                audioRecord?.release()
                audioData.close()
            }

            // Convert audio data to byte array
            val audioByteArray = audioData.toByteArray()

            // Convert byte array to RequestBody
            val requestBody = RequestBody.create(MediaType.parse("audio/*"), audioByteArray)

            // Send the audio data to the backend
            sendAudioToBackend(requestBody)
        }
    }

    private fun stopRecording() {
        isRecording = false
        recordingExecutor?.shutdown()
        recordingExecutor = null
    }

    private fun sendAudioToBackend(requestBody: RequestBody) {
//        val token = getCurrentToken(this)
//        val retrofitService = RetrofitObject.getRetrofitService
//
//        val call = retrofitService.speachToText(RetrofitClient2.sttResponse(requestBody.toByteArray()))
//
//        call.enqueue(object : Callback<String> {
//            override fun onResponse(call: Call<String>, response: Response<String>) {
//                if (response.isSuccessful) {
//                    val sttResult = response.body()
//                    // 서버로부터 받은 음성 인식 결과 처리
//                    Log.d("STT Result", sttResult ?: "No result received")
//                } else {
//                    // 서버 오류 처리
//                    Log.e("STT Error", "Failed to convert speech to text")
//                }
//            }
//
//            override fun onFailure(call: Call<String>, t: Throwable) {
//                // 네트워크 오류 처리
//                Log.e("STT Failure", "Failed to make STT request: ${t.message}")
//            }
//        })
    }

    private fun requestPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                RECORD_AUDIO
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(RECORD_AUDIO),
                RECORD_AUDIO_PERMISSION_REQUEST_CODE
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopRecording()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == RECORD_AUDIO_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 마이크 권한이 허용된 경우
                initializeAudioRecording()
            } else {
                // 마이크 권한이 거부된 경우
                // 권한이 거부되었음을 사용자에게 알리고 적절히 처리
            }
        }
    }

    private fun initializeAudioRecording() {
        // AudioRecord 초기화 및 녹음 시작
        // 위의 코드에서 AudioRecord 초기화 부분을 호출
    }
}