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


class PregnancyTip : Fragment(), TextToSpeech.OnInitListener {

    private lateinit var pregnancyTipBackBtn: ImageButton
    private lateinit var weekOfPregnancy: TextView
    private lateinit var pregnancyTips : TextView
    private lateinit var previousWeekBtn: ImageButton
    private lateinit var nextWeekBtn: ImageButton
    private lateinit var ptVolumeBtn: ImageButton

    private var currentWeekIndex: Int = 1

    private var tts: TextToSpeech? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_pregnancy_tip, container, false)

        pregnancyTipBackBtn = view.findViewById(R.id.Pregnancytip_back_btn)
        weekOfPregnancy = view.findViewById(R.id.week_of_pregnancy) //Title
        pregnancyTips = view.findViewById(R.id.pregnancy_tips) //Content
        previousWeekBtn = view.findViewById(R.id.previous_week_button)
        nextWeekBtn = view.findViewById(R.id.next_week_button)
        ptVolumeBtn = view.findViewById(R.id.pt_btn_volume)

        //초기 값 설정
        updateUIForCurrentWeek()

        //Text to Speech
        ptVolumeBtn.setOnClickListener {
            ptVolumeBtn!!.isEnabled = false
            tts = TextToSpeech(requireContext(), this)

            ptVolumeBtn!!.setOnClickListener { speakOut() }
        }

        pregnancyTipBackBtn.setOnClickListener{
            navigateToOtherFragment()
        }

        //버튼에 클릭 리스너 설정
        previousWeekBtn.setOnClickListener {
            //이전 주 버튼을 처리하는 로직 추가 (필요시)
            if(currentWeekIndex > 1){
                currentWeekIndex--
                updateUIForCurrentWeek()
            }
        }

        nextWeekBtn.setOnClickListener {
            // 다음 주 버튼을 처리하는 로직 호출
            if(currentWeekIndex < 8){
                currentWeekIndex++
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
        transaction.replace(R.id.newsFragment, newsFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun updateUIForCurrentWeek(){
        // 임신 시기에 따라 UI 업데이트
//        when (currentWeekIndex) {
//            1 -> {
//                weekOfPregnancy.text = getString(R.string.PTweek0_5)
//                pregnancyTips.text = getString(R.string.PTweek0_5_content)
//            }
//            2 -> {
//                weekOfPregnancy.text = getString(R.string.PTweek6_10)
//                pregnancyTips.text = getString(R.string.PTweek6_10_content)
//            }
//            3 -> {
//                weekOfPregnancy.text = getString(R.string.PTweek11_15)
//                pregnancyTips.text = getString(R.string.PTweek11_15_content)
//            }
//            4 -> {
//                weekOfPregnancy.text = getString(R.string.PTweek16_20)
//                pregnancyTips.text = getString(R.string.PTweek16_20_content)
//            }
//            5 -> {
//                weekOfPregnancy.text = getString(R.string.PTweek21_25)
//                pregnancyTips.text = getString(R.string.PTweek21_25_content)
//            }
//            6 -> {
//                weekOfPregnancy.text = getString(R.string.PTweek26_30)
//                pregnancyTips.text = getString(R.string.PTweek26_30_content)
//            }
//            7 -> {
//                weekOfPregnancy.text = getString(R.string.PTweek31_35)
//                pregnancyTips.text = getString(R.string.PTweek31_35_content)
//            }
//            8 -> {
//                weekOfPregnancy.text = getString(R.string.PTweek36_40)
//                pregnancyTips.text = getString(R.string.PTweek36_40_content)
//            }
//        }
    }

    //TTS
    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts!!.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The Language specified is not supported!")
            } else {
                ptVolumeBtn!!.isEnabled = true
            }
        } else {
            Log.e("TTS", "Initialization Failed!")
        }
    }

    private fun speakOut() {
        val qtext = weekOfPregnancy!!.text.toString()
        val atext = pregnancyTips!!.text.toString()
        tts!!.speak(qtext, TextToSpeech.QUEUE_FLUSH, null, "")
        tts!!.speak(atext, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    override fun onDestroy() {
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }
}
