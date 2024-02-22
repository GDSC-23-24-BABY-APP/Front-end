package com.company.ait.tobemom

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.company.ait.tobemom.databinding.FragmentMypageBinding

class MypageFragment : Fragment() {

    lateinit var binding: FragmentMypageBinding

    val pickImage = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        // Callback is invoked after the user selects a media item or closes the photo picker.
        if (uri != null) {
            Log.d("PhotoPicker", "Selected URI: $uri")
            // 권한 부여
            val flag = Intent.FLAG_GRANT_READ_URI_PERMISSION
            requireContext().contentResolver.takePersistableUriPermission(uri, flag)
            // 이미지뷰에 선택한 이미지 설정
            changeProfileImage(uri)
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypageBinding.inflate(inflater, container, false)
        val view = binding.root

        //사용자 정보 설정
        binding.mypageProfileIv.setImageResource(R.drawable.ic_user_iv)  //추후 수정 예정
        binding.mypageNameTv.text = "Jihyun Hong"  //추후 수정 예정
        binding.mypageIdTv.text = "aster03"  //추후 수정 예정
        binding.mypageBirthdateTv.text = "20"
        if (binding.mypageBirthdateTv.text == "1") {
            binding.mypageStndrdthTv.text = "st"
        } else if (binding.mypageBirthdateTv.text == "2") {
            binding.mypageStndrdthTv.text = "nd"
        } else if (binding.mypageBirthdateTv.text == "3") {
            binding.mypageStndrdthTv.text = "rd"
        } else {
            binding.mypageStndrdthTv.text = "th"
        }
        binding.mypageBirthnameTv.text = "Sweety"


        //버튼 클릭 이벤트 처리
        clickBtn()
        //프로필 사진 변경
        changeProfileImage()
        //로그아웃
        startLogout()

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

    private fun changeProfileImage() {
        binding.mypageProfileIv.setOnClickListener {
            val context = context
            val dialog = CustomChangeImageDialog(
                context!!,
                {
                    // 확인 버튼 클릭 시 동작
                    viewImgPicker() // 프로필 사진 변경 메서드 호출
                },
                {
                    // 취소 버튼 클릭 시 동작
                }
            )
            dialog.show()
        }
    }

    private fun changeProfileImage(uri: Uri) {
        // 선택한 이미지를 프로필 이미지뷰에 설정
        binding.mypageProfileIv.setImageURI(uri)
    }

    private fun viewImgPicker() {
        binding.mypageProfileIv.setOnClickListener {
            //커스텀 갤러리 화면
            // Launch the photo picker and let the user choose images and videos.
            pickImage.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
        }
    }

    private fun startLogout() {
        binding.mypageLogoutBtn.setOnClickListener {
            Toast.makeText(requireContext(), "로그아웃하려면 뒤로가기 버튼을 한 번 더 눌러주세요.", Toast.LENGTH_SHORT).show()
        }
    }
}