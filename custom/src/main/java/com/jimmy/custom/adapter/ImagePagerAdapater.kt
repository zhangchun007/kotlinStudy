package com.jimmy.custom.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.jimmy.custom.bean.GoodsInfo

/**
 * @Description:
 * @Author:         纯仔
 * @CreateDate:     2020-11-03
 * @Version:        1.0
 */
class ImagePagerAdapater(
    private val context: Context,
    private val goodsList: MutableList<GoodsInfo>
) : PagerAdapter() {

    private val viewList = mutableListOf<ImageView>()

    init {
        for (item in goodsList) {
            val view = ImageView(context)
            view.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            view.setImageResource(item.pic)
            view.scaleType = ImageView.ScaleType.FIT_CENTER
            viewList.add(view)
        }
    }


    override fun isViewFromObject(view: View, `object`: Any): Boolean = view === `object`
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        container.addView(viewList[position])
        return viewList[position]
    }

    override fun getCount(): Int = goodsList.size

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(viewList[position])
    }

    override fun getPageTitle(position: Int): CharSequence = goodsList[position].name
}