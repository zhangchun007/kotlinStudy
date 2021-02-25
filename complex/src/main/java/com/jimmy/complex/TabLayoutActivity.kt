package com.jimmy.complex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener
import com.google.android.material.tabs.TabLayout
import com.jimmy.complex.adapter.GoodsPagerAdapter
import kotlinx.android.synthetic.main.activity_tab_layout.*

class TabLayoutActivity : AppCompatActivity(), TabLayout.OnTabSelectedListener {


    private val titles = mutableListOf<String>("商品", "详情")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_layout)
        //使用自定义的工具栏替换系统默认的导航栏
        setSupportActionBar(tl_head)
        tl_head.setNavigationOnClickListener { finish() }
        initTabLayout()
        initTabViewPager()
    }

    //初始化头部的文本标签
    private fun initTabLayout() {
        tab_title.addTab(tab_title.newTab().setText(titles[0]))
        tab_title.addTab(tab_title.newTab().setText(titles[1]))
        tab_title.addOnTabSelectedListener(this)
    }

    //初始化页面主体的翻页视图
    private fun initTabViewPager() {
        vp_content.adapter = GoodsPagerAdapter(supportFragmentManager, titles)
        vp_content.addOnPageChangeListener(object : SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                tab_title.getTabAt(position)!!.select()
            }

        })
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    override fun onTabSelected(tab: TabLayout.Tab) {
        vp_content.currentItem = tab.position
    }
}
