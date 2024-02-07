package com.company.ait.tobemom

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.TextView

class CustomFindidDialog(
    context: Context
) : Dialog(context) {

    lateinit var findid : String  //추후 수정 예정
    private var isExist : Boolean = false  //아이디가 회원 정보에 있는지 확인, 없음을 기본값으로 설정, 추후 수정 예정

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_findid)

        val findidTextview: TextView = findViewById(R.id.dialog_findid_tv)

        if (!isExist) {
            findidTextview.text = "회원님의 ID는 ${findid}입니다."  //추후 수정 예정
        } else {
            findidTextview.text = "존재하지 않는 회원 정보입니다.\n서비스 이용을 원하시면 회원가입을 해주세요."
        }
    }
}