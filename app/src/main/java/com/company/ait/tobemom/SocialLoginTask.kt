package com.company.ait.tobemom

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

suspend fun socialLoginTask(): JSONObject? = withContext(Dispatchers.IO) {
    // API 엔드포인트 및 요청 파라미터 설정
    val apiUrl = "https://kauth.kakao.com/oauth/authorize"
    val clientId = "5739d24b3200adc30b4b7bce937f8352" // 클라이언트 ID
    val redirectUri = "http://34.64.44.183:8080/login/oauth2/code/kakao" // 리다이렉션 URI
    val responseType = "code" // 응답 유형
    val requestData = "client_id=$clientId&redirect_uri=$redirectUri&response_type=$responseType"

    try {
        // GET 요청 보내기
        val url = URL("$apiUrl?$requestData")
        val conn = url.openConnection() as HttpURLConnection
        conn.requestMethod = "GET"

        // 응답 처리
        val responseCode = conn.responseCode
        if (responseCode == HttpURLConnection.HTTP_OK) {
            // 응답 데이터 읽기
            val reader = BufferedReader(InputStreamReader(conn.inputStream))
            val response = StringBuilder()
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                response.append(line)
            }
            reader.close()
            JSONObject(response.toString())
        } else {
            null
        }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

// 소셜 로그인 함수 호출
fun socialLogin() {
    GlobalScope.launch {
        // 소셜 로그인 실행 및 결과 처리
        val result = socialLoginTask()
        if (result != null) {
            println("소셜 로그인 성공: $result")
            // 여기서 결과를 처리할 수 있습니다.
        } else {
            println("소셜 로그인 실패")
        }
    }
}