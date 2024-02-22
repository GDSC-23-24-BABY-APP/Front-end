package com.company.ait.tobemom

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.util.Locale

class FrequentlyAskedQuestions : Fragment(), TextToSpeech.OnInitListener {

    private lateinit var faqBackBtn: ImageButton
    private lateinit var questionContent: TextView
    private lateinit var previousWeekButton: ImageButton
    private lateinit var nextWeekButton: ImageButton
    private lateinit var PregnancyWeek: TextView
    private lateinit var FaqVolumeBtn: ImageButton

    private var currentWeek: Int = 1

    private var tts: TextToSpeech? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_frequently_asked_questions, container, false)

        faqBackBtn = view.findViewById(R.id.FAQ_back_btn)
        questionContent = view.findViewById(R.id.question_content)
        previousWeekButton = view.findViewById(R.id.previous_week_button)
        nextWeekButton = view.findViewById(R.id.next_week_button)
        PregnancyWeek = view.findViewById(R.id.pregnancy_week)
        FaqVolumeBtn = view.findViewById(R.id.faq_btn_volume)

        //초기 값 설정
        updateUIForCurrentWeek()

        //Text To Speech
        FaqVolumeBtn.setOnClickListener {
            FaqVolumeBtn!!.isEnabled = false
            tts = TextToSpeech(requireContext(), this)

            FaqVolumeBtn!!.setOnClickListener { speakOut() }
        }

        faqBackBtn.setOnClickListener{
            navigateToOtherFragment()
        }

        //버튼에 클릭 리스너 설정
        previousWeekButton.setOnClickListener {
            //이전 주 버튼을 처리하는 로직 추가 (필요시)
            if(currentWeek > 1){
                currentWeek--
                updateUIForCurrentWeek()
            }
        }

        nextWeekButton.setOnClickListener {
            // 다음 주 버튼을 처리하는 로직 호출
            if(currentWeek < 3){
                currentWeek++
                updateUIForCurrentWeek()
            }
        }
        return view
    }
    private fun navigateToOtherFragment() {
        // 이동하고 싶은 Fragment를 생성
        val newsFragment = NewsFragment()
        val transaction = requireActivity().supportFragmentManager.beginTransaction()

        // 다른 Fragment로 교체하고 back stack에 추가
        transaction.replace(R.id.FAQfragment, newsFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun updateUIForCurrentWeek(){
        // 임신 시기에 따라 UI 업데이트
        when (currentWeek) {
            1 -> {
                PregnancyWeek.text = getString(R.string.early_pregnancy_title)
                questionContent.text = getString(R.string.early_pregnancy_content)
            }
            2 -> {
                PregnancyWeek.text = getString(R.string.mid_pregnancy_title)
                questionContent.text = getString(R.string.mid_pregnancy_content)
            }
            3 -> {
                PregnancyWeek.text = getString(R.string.late_pregnancy_title)
                questionContent.text = getString(R.string.late_pregnancy_content)
            }
        }

    }

    //TTS
    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts!!.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The Language specified is not supported!")
            } else {
                FaqVolumeBtn!!.isEnabled = true
            }
        } else {
            Log.e("TTS", "Initialization Failed!")
        }
    }

    private fun speakOut() {
        val text = questionContent!!.text.toString()
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    override fun onDestroy() {
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }

}