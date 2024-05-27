package kr.shtmdgh0108.test240509frag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class RedFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, // XML 레이아웃 파일을 실제 뷰 객체로 변환하는 역할
        container: ViewGroup?, // 생성할 뷰(자식 뷰)가 들어갈 부모 뷰
        savedInstanceState: Bundle? // 이전 프래그먼트 객체에서 전달된 데이터(번들)
    ): View? {
        return inflater.inflate(R.layout.fragment_red, container, false)
    }
}