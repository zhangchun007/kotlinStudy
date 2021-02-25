package com.jimmy.complex

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.jimmy.complex.adapter.ImagePagerAdapater
import com.jimmy.complex.bean.GoodsInfo
import kotlinx.android.synthetic.main.activity_view_pager.*

class ViewPagerActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {


    private var goodsList = GoodsInfo.defaultList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)
        //注意PagerTabStrip不存在textSize属性，只能调用setTextSize方法设置文字大小
        pts_tab.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
        pts_tab.setTextColor(Color.RED)

        vp_content.adapter = ImagePagerAdapater(this, goodsList)
        vp_content.currentItem = 0
        vp_content.addOnPageChangeListener(this)
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        Toast.makeText(this, "您翻到的手机品牌是：${goodsList[position].name}", Toast.LENGTH_SHORT).show()
    }
}
