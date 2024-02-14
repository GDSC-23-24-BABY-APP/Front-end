package com.company.ait.tobemom

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Button
import com.company.ait.tobemom.databinding.DialogViewDiaryContentBinding

class CustomDiaryContentDialog (
    context: Context,
) : Dialog(context) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_view_diary_content)

        val closeButton : Button = findViewById(R.id.dialog_close_btn)

        closeButton.setOnClickListener {
            dismiss()
        }
    }
}