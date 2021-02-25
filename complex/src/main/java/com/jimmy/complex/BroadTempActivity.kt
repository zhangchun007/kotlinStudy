package com.jimmy.complex

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import com.jimmy.complex.adapter.BroadcastPagerAdapter
import com.jimmy.complex.bean.GoodsInfo
import com.jimmy.complex.fragment.BroadcastFragment
import kotlinx.android.synthetic.main.activity_broad_temp.*

class BroadTempActivity : AppCompatActivity() {


    private var bgChangeReceiver: BgChangeReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broad_temp)
        pts_tab.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)

        vp_content.adapter = BroadcastPagerAdapter(supportFragmentManager, GoodsInfo.defaultList)
        vp_content.currentItem = 0

    }

    override fun onStart() {
        super.onStart()
        bgChangeReceiver = BgChangeReceiver()
        //声明一个过滤器，明确只接收名称为BroadcastFragment.EVENT的广播
        val filer = IntentFilter(BroadcastFragment.EVENT)
        registerReceiver(bgChangeReceiver, filer)
    }

    override fun onStop() {
        unregisterReceiver(bgChangeReceiver)
        super.onStop()
    }


    private inner class BgChangeReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent != null) {
                //从广播消息中获取新颜色，并将背景色改成新颜色
                val color = intent.getIntExtra("color", Color.WHITE)
                ll_brd_temp.setBackgroundColor(color)
            }
        }

    }
}
