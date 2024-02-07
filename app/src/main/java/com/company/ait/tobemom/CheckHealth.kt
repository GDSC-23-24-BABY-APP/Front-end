package com.company.ait.tobemom

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CheckHealth : AppCompatActivity() {

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_health)

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

        //날짜 표시
        displayCurrentDate()

    }
    private fun displayCurrentDate(){
        val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        dateTextView.text = currentDate
    }
}