package com.jimmy.complex.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.jimmy.complex.fragment.AppliancesFragment
import com.jimmy.complex.fragment.ClothesFragment

/**
 * @Description:
 * @Author:         纯仔
 * @CreateDate:     2020-10-19
 * @Version:        1.0
 */
class ChannelPagerAdapter(fm: FragmentManager, private val titles: MutableList<String>) :
    FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment = when (position) {
        0 -> ClothesFragment()
        1 -> AppliancesFragment()
        else -> ClothesFragment()
    }

    override fun getCount(): Int = titles.size

    override fun getPageTitle(position: Int): CharSequence = titles[position]

    companion object {
        const val EVENT = "com.jimmy.complex.adapter.ChannelPagerAdapter"
    }
}