package com.jimmy.complex

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.jimmy.complex.adapter.RecyclerCollapseAdapter
import kotlinx.android.synthetic.main.activity_collapse_parallax.*

class CollapseParallaxActivity : AppCompatActivity() {
    private val years = arrayOf("鼠年", "牛年", "虎年", "兔年", "龙年", "蛇年", "马年", "羊年", "猴年", "鸡年", "狗年", "猪年")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collapse_parallax)
        tl_title.setBackgroundColor(Color.RED)
        setSupportActionBar(tl_title)
        ctl_title.title = getString(R.string.toolbar_name)
        rv_main.layoutManager = LinearLayoutManager(this)
        rv_main.adapter = RecyclerCollapseAdapter(this, years)

        /**
         * app:layout_collapseMode(折叠模式属性)
         *
         * pin: 固定模式。Toolbar固定不动，不受CollapsingToolbarLayout的折叠影响
         *
         * parallax:视差模式。随着CollapsingToolbarLayout的收缩与展开，Toolbar也跟着收缩与展开。折叠系数可以通过属性
         *          app:layout_collapseParallaxMultiplier=""配置，该属性为1.0时折叠效果同pin模式，即固定不动。该属性为0.0时
         *          折叠效果等同于none模式，即也跟着移动相同的距离
         *
         * none：默认值。CollapsingToolbarLayout折叠多少距离，toolbar 也随着移动多少距离
         */

        /**
         * app:layout_scrollFlags=""这个滚动标志属性来之于AppBarLayout，它用来定义下级控件的具体滚动行为
         *取值：
         * 1：scroll：头部与主体一起滚动
         * 2：enterAlways：头部与主体先一起滚动，头部滚动到位后，主体继续向上或者向下滚，该标志需要与scroll同时声明
         * 3：exitUntilCollapsed ：该标志保证页面上至少能看到最小化的工具栏，不会完全看不到工具栏。该标志需要与scroll同时声明
         * 4：enterAlwaysCollapsed：该标志与enterAlways的区别在于有折叠操作，而单独扽enterAlways没有折叠，该标志需要与scroll，enterAlways同时声明
         * 5：snap:在用户手指松开时，系统自行判断，接下来是全部向上滚到顶，还是全部向下展开，该标志需要与scroll同时声明
         */
    }
}
