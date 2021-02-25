package com.jimmy.complex.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jimmy.complex.R

/**
 * @Description:
 * @Author:         纯仔
 * @CreateDate:     2020-10-14
 * @Version:        1.0
 */
class BookCoverFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_book_cover, container, false)
    }
}