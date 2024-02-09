package com.company.ait.tobemom

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter

class OnboardingVPAdapter(private val context: Context, private val images: List<Int>) : PagerAdapter() {

    // ViewPager에서 새로운 페이지를 생성하고 반환하는 메서드입니다.
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        // LayoutInflater를 사용하여 XML 레이아웃을 인플레이션합니다.
        val inflater = LayoutInflater.from(context)
        val layout = inflater.inflate(R.layout.item_onboarding_image, container, false) as ViewGroup

        // ImageView를 찾아 이미지를 설정합니다.
        val imageView = layout.findViewById<ImageView>(R.id.onboarding_image_iv)
        imageView.setImageResource(images[position])

        // ViewPager의 container에 레이아웃을 추가합니다.
        container.addView(layout)
        return layout
    }

    // 데이터 세트의 크기를 반환합니다.
    override fun getCount(): Int {
        return images.size
    }

    // 뷰가 현재 페이지를 나타내는지 여부를 반환합니다.
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    // 페이지를 삭제하는 메서드입니다.
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}