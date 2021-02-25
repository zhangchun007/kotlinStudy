package com.jimmy.complex.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.jimmy.complex.bean.GoodsInfo
import com.jimmy.complex.fragment.BroadcastFragment

/**
 * @Description:
 * @Author:         纯仔
 * @CreateDate:     2020-10-15
 * @Version:        1.0
 */
class BroadcastPagerAdapter(fm: FragmentManager, private val goodsList: MutableList<GoodsInfo>) :
    FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return BroadcastFragment.newInstance(
            position,
            goodsList[position].pic,
            goodsList[position].desc
        )
    }

    override fun getCount(): Int = goodsList.size

    override fun getPageTitle(position: Int): CharSequence? = goodsList[position].name
}