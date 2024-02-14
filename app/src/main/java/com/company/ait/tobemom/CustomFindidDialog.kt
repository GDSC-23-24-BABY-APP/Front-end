package com.company.ait.tobemom

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.TextView

class CustomFindidDialog(
    context: Context,
    private val findid: String, // 생성자 매개변수로 받음 | 추후 수정 예정
    private val isExist: Boolean // 생성자 매개변수로 받음 | 아이디가 회원 정보에 있는지 확인, 없음을 기본값으로 설정, 추후 수정 예정
) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_findid)

        //다이얼로그 둥글게 만들기
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // 다이얼로그 바깥쪽 클릭시 종료되도록 함 (Cancel the dialog when you touch outside)
        setCanceledOnTouchOutside(true)

        // 취소 가능 유무
        setCancelable(true)

        val findidTextview: TextView = findViewById(R.id.dialog_findid_tv)

        if (!isExist) {
            findidTextview.text = "회원님의 ID는 $findid 입니다."
        } else {
            findidTextview.text = "존재하지 않는 회원 정보입니다.\n서비스 이용을 원하시면 회원가입을 해주세요."
        }
    }
}