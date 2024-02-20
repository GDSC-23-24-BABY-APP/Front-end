package com.company.ait.tobemom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction


class NewsFragment : Fragment(), View.OnClickListener {

    private lateinit var BabyDevelopment: TextView
    private lateinit var PregnancyTip: TextView
    private lateinit var FrequentlyAskedQuestions: TextView
    private lateinit var NearbyHospitals: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)

        BabyDevelopment = view.findViewById(R.id.BabyDevelopment)
        PregnancyTip = view.findViewById(R.id.PregnancyTip)
        FrequentlyAskedQuestions = view.findViewById(R.id.FrequentlyAskedQuestions)
        NearbyHospitals = view.findViewById(R.id.NearbyHospitals)

        return view

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 각 TextView에 클릭 리스너 설정
        BabyDevelopment.setOnClickListener(this)
        PregnancyTip.setOnClickListener(this)
        FrequentlyAskedQuestions.setOnClickListener(this)
        NearbyHospitals.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        // 클릭한 TextView에 따라서 다른 Fragment로 이동
        when (view.id) {
            R.id.BabyDevelopment -> replaceFragment(BabyDevelopment())
            R.id.PregnancyTip -> replaceFragment(PregnancyTip())
            R.id.FrequentlyAskedQuestions -> replaceFragment(FrequentlyAskedQuestions())
            R.id.NearbyHospitals -> replaceFragment(HospitalMap())
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        // Fragment를 교체하는 코드
        val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
        transaction.replace(R.id.newsFragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
