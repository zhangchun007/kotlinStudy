package com.jimmy.complex

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import com.jimmy.complex.adapter.MobilePagerAdapter
import com.jimmy.complex.bean.GoodsInfo
import kotlinx.android.synthetic.main.activity_fragment_dynamic.*
import kotlinx.android.synthetic.main.activity_fragment_dynamic.pts_tab
import kotlinx.android.synthetic.main.activity_fragment_dynamic.vp_content
import kotlinx.android.synthetic.main.activity_view_pager.*

class FragmentDynamicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_dynamic)
//注意PagerTabStrip不存在textSize属性，只能调用setTextSize方法设置文字大小
        pts_tab.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
        pts_tab.setTextColor(Color.RED)

        vp_content.adapter = MobilePagerAdapter(supportFragmentManager, GoodsInfo.defaultList)
        vp_content.currentItem = 0
    }
}
