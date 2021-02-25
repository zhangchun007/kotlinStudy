package com.jimmy.custom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jimmy.custom.bean.Planet
import com.jimmy.custom.widget.PlanetAdapter
import kotlinx.android.synthetic.main.activity_measure_view.*

/**
 * 测量尺寸
 * (1)定义构造函数，读取自定义属性值并进行初始化设置。
 * (2)重写测量函数onMesure,计算该视图的宽高尺寸。
 * (3)重写绘图函数onDraw或者dispatchDraw,在当前视图内部绘制指定形状。
 */
class MeasureViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_measure_view)
        //lv_planet是系统自带的ListView，被ScrollView嵌套只能显示一行
        val adapter1 = PlanetAdapter(this, Planet.defaultList)
        lv_planet.adapter = adapter1
        lv_planet.onItemClickListener = adapter1
        lv_planet.onItemLongClickListener = adapter1
        //nslv_planet是自定义控件NoScrollListView，会显示所有行
        val adapter2 = PlanetAdapter(this, Planet.defaultList)
        nslv_planet.adapter = adapter2
        nslv_planet.onItemClickListener = adapter2
        nslv_planet.onItemLongClickListener = adapter2
    }
}
