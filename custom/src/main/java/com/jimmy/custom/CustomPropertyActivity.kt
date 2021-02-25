package com.jimmy.custom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.jimmy.custom.adapter.ImagePagerAdapater
import com.jimmy.custom.bean.GoodsInfo
import kotlinx.android.synthetic.main.activity_custom_property.*

class CustomPropertyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_property)
        val goodsList = GoodsInfo.defaultList
        vp_content.adapter = ImagePagerAdapater(this, goodsList)
        vp_content.currentItem = 0
        vp_content.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                Toast.makeText(
                    this@CustomPropertyActivity,
                    "您翻到的手机品牌是：${goodsList[position].name}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}
