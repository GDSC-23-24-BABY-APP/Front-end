package com.company.ait.tobemom

import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.company.ait.tobemom.databinding.ActivityDiaryViewBinding

class DiaryViewActivity: AppCompatActivity() {

    lateinit var binding: ActivityDiaryViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiaryViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showDialog()
    }

    private fun showDialog() {
        val momDialog = CustomDiaryContentDialog(binding.diaryviewMommycontentTv.context)
        val dadDialog = CustomDiaryContentDialog(binding.diaryviewDaddycontentTv.context)
        momDialog.setContentView(R.layout.dialog_view_diary_content)
        dadDialog.setContentView(R.layout.dialog_view_diary_content)

        momDialog.show()
        dadDialog.show()
    }
}