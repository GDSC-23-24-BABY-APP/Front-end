package com.company.ait.tobemom

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.company.ait.tobemom.databinding.FragmentHomeBinding
import com.company.ait.tobemom.databinding.FragmentMypageBinding

class MypageFragment : Fragment() {

    lateinit var binding: FragmentMypageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypageBinding.inflate(inflater, container, false)
        val view = binding.root

        //사용자 정보 설정
        binding.mypageProfileIv.setImageResource(R.drawable.ic_user_iv)  //추후 수정 예정
        binding.mypageNameTv.text = "홍지현"  //추후 수정 예정
        binding.mypageIdTv.text = "ASTER03"  //추후 수정 예정
        binding.mypageBirthnameTv.text = "꿈틀이"  //추후 수정 예정
        binding.mypageDdaycntTv.text = "23"  //추후 수정 예정

        //버튼 클릭 이벤트 처리
        clickBtn()

        return view
    }

    private fun clickBtn() {
        //MY INFO
        binding.mypageMyinfoBtn.setOnClickListener {
            val intent = Intent(activity, MyPageMyinfoActivity::class.java)
            startActivity(intent)
        }

        //BABY INFO
        binding.mypageBabyinfoBtn.setOnClickListener {
            val intent = Intent(activity, MyPageBabyinfoActivity::class.java)
            startActivity(intent)
        }

        //LINK
        binding.mypageLinkBtn.setOnClickListener {
            val intent = Intent(activity, MyPageLinkActivity::class.java)
            startActivity(intent)
        }

        //SETTINGS
        binding.mypageSettingsBtn.setOnClickListener {
            val intent = Intent(activity, MyPageSettingsActivity::class.java)
            startActivity(intent)
        }

        //HOW TO USE
        binding.mypageHowtouseBtn.setOnClickListener {
            val intent = Intent(activity, MyPageHowtouseActivity::class.java)
            startActivity(intent)
        }

        //NOTION
        binding.mypageNotionBtn.setOnClickListener {
            val intent = Intent(activity, MyPageNoticeActivity::class.java)
            startActivity(intent)
        }
    }
}