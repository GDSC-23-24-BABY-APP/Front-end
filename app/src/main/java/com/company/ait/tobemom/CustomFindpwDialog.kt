package com.company.ait.tobemom

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.company.ait.tobemom.databinding.DialogFindpwBinding

class CustomFindpwDialog(
    context: Context
) : Dialog(context) {

    private lateinit var binding: DialogFindpwBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var newpw: String
    private lateinit var checkpw: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogFindpwBinding.inflate(LayoutInflater.from(context))
        setContentView(R.layout.dialog_findpw)

        // SharedPreferences 초기화
        sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        //다이얼로그 둥글게 만들기
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // 다이얼로그 바깥쪽 클릭시 종료되도록 함 (Cancel the dialog when you touch outside)
        setCanceledOnTouchOutside(true)

        // 취소 가능 유무
        setCancelable(true)

        //취소 버튼 클릭 이벤트
        binding.dialogCancelBtn.setOnClickListener {
            dismiss() // 다이얼로그 닫기 (Close the dialog)
        }

        //비밀번호 저장하는 코드
        newpw = binding.dialogFindpwEt.text.toString()
        savePassword(newpw)

        //비밀번호 확인 코드
        checkpw = binding.dialogCheckpwEt.text.toString()
        pwvisibility()
    }

    private fun savePassword(password: String) {
        sharedPreferences.edit().putString("password", password).apply()
    }

    private fun pwvisibility() {
        //비밀번호 일치, 불일치 textview 보여주기
        if(isPwCorrect(checkpw) == true) {
            binding.dialogCorrectTv.visibility = View.VISIBLE
            binding.dialogIncorrectTv.visibility = View.GONE

            //확인 버튼 클릭 이벤트
            binding.dialogNextBtn.setOnClickListener {
                // interface를 이용해 다이얼로그를 호출한 곳에 값을 전달함
                //새로운 비밀번호 백으로 저장
                dismiss()
            }
        } else {
            binding.dialogCorrectTv.visibility = View.GONE
            binding.dialogIncorrectTv.visibility = View.VISIBLE
        }
    }

    private fun isPwCorrect(password: String): Boolean {
        //비밀번호가 맞는지 안맞는지 확인
        val savedPassword = sharedPreferences.getString("password", "")
        return savedPassword == password
    }
}