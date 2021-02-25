package com.jimmy.complex.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.jimmy.complex.bean.GoodsInfo

/**
 * @Description:
 * @Author:         纯仔
 * @CreateDate:     2020-10-11
 * @Version:        1.0
 */
class ImagePagerAdapater(
    private val context: Context,
    private val goodsList: MutableList<GoodsInfo>
) : PagerAdapter() {

    private val views = mutableListOf<ImageView>()

    //初始化函数进行开发者额外的初始操作
    init {
        for (item in goodsList) {
            val view = ImageView(context)
            view.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            view.setImageResource(item.pic)
            view.scaleType = ImageView.ScaleType.FIT_CENTER
            views.add(view)
        }
    }

    //判断指定页面是否已加入适配器，注意这里用到了引用相等
    override fun isViewFromObject(view: View, `object`: Any): Boolean = (view === `object`)

    //获取页面数量，使用了简化函数
    override fun getCount(): Int = goodsList.size

    //回收页面
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(views[position])
    }

    //实例化每个页面
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        container.addView(views[position])
        return views[position]
    }

    //获得页面的标题，要跟PagerTabStrip配合使用
    override fun getPageTitle(position: Int): CharSequence? = goodsList[position].name
}