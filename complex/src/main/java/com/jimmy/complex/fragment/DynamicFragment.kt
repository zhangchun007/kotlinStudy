package com.jimmy.complex.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.jimmy.complex.R
import kotlinx.android.synthetic.main.fragment_dynamic.*

/**
 * @Description:
 * @Author:         纯仔
 * @CreateDate:     2020-10-14
 * @Version:        1.0
 */
class DynamicFragment : Fragment() {

    private var ctx: Context? = null
    private var mPosition: Int = 0
    private var mImageId: Int = 0
    private var mDesc: String? = null
    private var mPrice: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        ctx = activity
        //碎片内部通过arguments获取外部的输入参数
        if (arguments != null) {
            mPosition = arguments!!.getInt("position")
            mImageId = arguments!!.getInt("image_id")
            mDesc = arguments!!.getString("desc")
            mPrice = arguments!!.getInt("price")
        }
        val view = inflater.inflate(R.layout.fragment_dynamic, container, false)
        val iv_pic = view.findViewById<ImageView>(R.id.iv_pic)
        val tv_desc = view.findViewById<TextView>(R.id.tv_desc)
        iv_pic.setImageResource(mImageId)
        tv_desc.text = "$mDesc\n售价：$mPrice"
        return view
    }

    companion object {
        fun newInstance(position: Int, image_id: Int, desc: String, price: Int): DynamicFragment {
            val fragment = DynamicFragment()
            val bundle = Bundle()
            bundle.putInt("position", position)
            bundle.putInt("image_id", image_id)
            bundle.putString("desc", desc)
            bundle.putInt("price", price)
            fragment.arguments = bundle
            return fragment
        }
    }
}