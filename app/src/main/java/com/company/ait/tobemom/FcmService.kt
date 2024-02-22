//package com.company.ait.tobemom
//
//import android.util.Log
//import com.google.firebase.messaging.FirebaseMessagingService
//import com.google.firebase.messaging.RemoteMessage
//
//class FcmService : FirebaseMessagingService() {
////    private var token: String
////        get() = MyApplication.prefs.getData(TOKEN_KEY, "")
////        set(value) {
////            MyApplication.prefs.setData(TOKEN_KEY, value)
////        }
//
//    override fun onNewToken(newToken: String) {
//        super.onNewToken(newToken)
//        Log.e(TAG, "onNewToken()")
//
//        //token = newToken // 새로운 토큰이 발급될 때마다 SharedPreferences에 갱신
//    }
//
//    override fun onMessageReceived(message: RemoteMessage) {
//        super.onMessageReceived(message)
//        Log.e(TAG, "onMessageReceived()")
//        // Notification 발생하는 코드 추가
//    }
//
//    companion object {
//        private val TAG = FcmService::class.simpleName
//    }
//}