package com.jimmy.complex.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.jimmy.complex.bean.GoodsInfo
import com.jimmy.complex.fragment.DynamicFragment

/**
 * @Description:
 * @Author:         纯仔
 * @CreateDate:     2020-10-14
 * @Version:        1.0
 */
class MobilePagerAdapter(fm: FragmentManager, private val goodsList: MutableList<GoodsInfo>) :
    FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        val item = goodsList[position]
        return DynamicFragment.newInstance(position, item.pic, item.desc, item.price)
    }

    override fun getCount(): Int = goodsList.size

    override fun getPageTitle(position: Int): CharSequence? {
        return goodsList[position].name
    }
}