package com.company.ait.tobemom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.ActivityNavigator
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController


class NewsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)

        // 각 TextView에 클릭 리스너를 설정
        setClickListener(view.findViewById(R.id.BabyDevelopment), R.id.action_newsFragment_to_BabyDevelopment)
        setClickListener(view.findViewById(R.id.PregnancyTip), R.id.action_newsFragment_to_pregnancyTipsFragment)
        setClickListener(view.findViewById(R.id.FrequentlyAskedQuestions), R.id.action_newsFragment_to_frequentlyAskedQuestionsFragment)
        setClickListener(view.findViewById(R.id.HealthIssues), R.id.action_newsFragment_to_healthIssuesFragment)
        setClickListener(view.findViewById(R.id.NearbyHospitals), R.id.action_newsFragment_to_nearbyHospitalsFragment)

        return view
    }

    private fun setClickListener(textView: TextView, destinationId: Int) {
        textView.setOnClickListener {
            // 클릭된 TextView에 따라 다른 화면으로 이동
            findNavController().navigate(destinationId)
        }
    }
}
