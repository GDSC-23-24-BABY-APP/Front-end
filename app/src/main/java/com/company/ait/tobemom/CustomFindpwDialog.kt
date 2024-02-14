package com.company.ait.tobemom

import android.app.Dialog
import android.content.Context
import android.os.Bundle

class CustomFindpwDialog(
    context: Context
) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_findpw)


    }
}