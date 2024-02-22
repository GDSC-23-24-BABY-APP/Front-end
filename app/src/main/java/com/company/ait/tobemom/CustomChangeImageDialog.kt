package com.company.ait.tobemom

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.TextView

class CustomChangeImageDialog(
    context: Context,
    private val onConfirm: () -> Unit,
    private val onCancel: () -> Unit
) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_change_image)

        //다이얼로그 둥글게 만들기
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // 다이얼로그 바깥쪽 클릭시 종료되도록 함 (Cancel the dialog when you touch outside)
        setCanceledOnTouchOutside(true)

        val setProfileImage: TextView = findViewById(R.id.dialog_profileset_tv)
        val cancel: TextView = findViewById(R.id.dialog_cancel_tv)

        setProfileImage.setOnClickListener {
            onConfirm.invoke()
            dismiss()
        }

        cancel.setOnClickListener {
            onCancel.invoke()
            dismiss()
        }
    }
}