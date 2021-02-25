package com.jimmy.complex.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.jimmy.complex.fragment.BookCoverFragment
import com.jimmy.complex.fragment.BookDetailFragment

/**
 * @Description:
 * @Author:         纯仔
 * @CreateDate:     2020-10-14
 * @Version:        1.0
 */
class GoodsPagerAdapter(fm: FragmentManager, private val titles: MutableList<String>) :
    FragmentPagerAdapter(fm) {

    //根据位置序号分别指定不同扽Fragment碎片类
    override fun getItem(position: Int): Fragment =when(position){
        0 -> BookCoverFragment()
        1 -> BookDetailFragment()
        else -> BookCoverFragment()
    }

    override fun getCount(): Int = titles.size

    override fun getPageTitle(position: Int): CharSequence? = titles[position]
}