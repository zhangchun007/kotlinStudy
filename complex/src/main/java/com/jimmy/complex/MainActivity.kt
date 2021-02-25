package com.jimmy.complex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //recyclerview (线性)
        btn_recycler_linear.setOnClickListener {
            val intent = Intent(this@MainActivity, RecyclerLinearActivity::class.java)
            startActivity(intent)
        }
        //recyclerview (网格)
        btn_recycler_grid.setOnClickListener {
            val intent = Intent(this@MainActivity, RecyclerGridActivity::class.java)
            startActivity(intent)
        }
        //recyclerview (瀑布流)
        btn_recycler_staggered.setOnClickListener {
            val intent = Intent(this@MainActivity, RecyclerStaggeredActivity::class.java)
            startActivity(intent)
        }

        //CoordinatorLayout（协调式布局）
        btn_coordinatorlayout.setOnClickListener {
            val intent = Intent(this@MainActivity, CoordinatorActivity::class.java)
            startActivity(intent)
        }
        //toolbar（菜单栏）
        btn_toolbar.setOnClickListener {
            val intent = Intent(this@MainActivity, ToolbarActivity::class.java)
            startActivity(intent)
        }

        //Appbarlayout（菜单栏）
        btn_appbarlayout.setOnClickListener {
            val intent = Intent(this@MainActivity, AppbarNestedActivity::class.java)
            startActivity(intent)
        }
        btn_appbarlayout2.setOnClickListener {
            val intent = Intent(this@MainActivity, AppbarRecyclerActivity::class.java)
            startActivity(intent)
        }

        //折叠式布局
        btn_collapsingToolbarLayout.setOnClickListener {
            val intent = Intent(this@MainActivity, CollapseParallaxActivity::class.java)
            startActivity(intent)
        }
        //仿照支付宝
        btn_ali_collapsingToolbarLayout.setOnClickListener {
            val intent = Intent(this@MainActivity, ScrollAlipayActivity::class.java)
            startActivity(intent)
        }
        //viewpage
        btn_viewpage.setOnClickListener {
            val intent = Intent(this@MainActivity, ViewPagerActivity::class.java)
            startActivity(intent)
        }
        //btn_fragment
        btn_fragment.setOnClickListener {
            val intent = Intent(this@MainActivity, FragmentDynamicActivity::class.java)
            startActivity(intent)
        }

        //btn_tablayout
        btn_tablayout.setOnClickListener {
            val intent = Intent(this@MainActivity, TabLayoutActivity::class.java)
            startActivity(intent)
        }
        //btn_BroadCast
        btn_BroadCast.setOnClickListener {
            val intent = Intent(this@MainActivity, BroadTempActivity::class.java)
            startActivity(intent)
        }

        //btn_swiprefresh
        btn_swiprefresh.setOnClickListener {
            val intent = Intent(this@MainActivity, SwipeRecyclerActivity::class.java)
            startActivity(intent)
        }

        //btn_depart
        btn_depart.setOnClickListener {
            val intent = Intent(this@MainActivity, DepartmentChannelActivity::class.java)
            startActivity(intent)
        }
    }
}
