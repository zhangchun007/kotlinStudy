package com.jimmy.complex.fragment

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.jimmy.complex.R
import kotlinx.android.synthetic.main.fragment_broadcast.*

/**
 * @Description:
 * @Author:         纯仔
 * @CreateDate:     2020-10-15
 * @Version:        1.0
 */
class BroadcastFragment : Fragment() {

    private val colorNames = listOf<String>("红色", "黄色", "绿色", "青色", "蓝色")
    private val colorIds = intArrayOf(Color.RED, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE)

    private var ctx: Context? = null
    lateinit var sp_bg: Spinner
    lateinit var tv_spinner: TextView
    private var mPosition: Int = 0
    private var mImageId: Int = 0
    private var mDesc: String? = null
    private var mSeq = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ctx = activity
        if (arguments != null) {
            mPosition = arguments!!.getInt("position", 0)
            mImageId = arguments!!.getInt("image_id", 0)
            mDesc = arguments!!.getString("desc")
        }
        val view = inflater!!.inflate(R.layout.fragment_broadcast, container, false)
        val iv_pic = view.findViewById<ImageView>(R.id.iv_pic)
        val tv_desc = view.findViewById<TextView>(R.id.tv_desc)
        sp_bg = view.findViewById<Spinner>(R.id.sp_bg) as Spinner
        tv_spinner = view.findViewById<TextView>(R.id.tv_spinner)
        iv_pic.setImageResource(mImageId)
        tv_desc.text = mDesc
        return view
    }

    //初始化选择背景色的下拉框
    private fun initSpinner() {

        sp_bg.adapter =
            ArrayAdapter<String>(ctx!!, android.R.layout.simple_spinner_item, colorNames)

        sp_bg.visibility = View.VISIBLE
        sp_bg.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                tv_spinner.text = colorNames[position]
                mSeq = position
                //设置广播意图的名称为BroadcastFragment.EVENT
                val intent = Intent(EVENT)
                intent.putExtra("seq", position)
                intent.putExtra("color", colorIds[position])
                //已选择新颜色，则发送背景色变更的广播
                ctx!!.sendBroadcast(intent)
            }

        }


    }

    override fun onStart() {
        super.onStart()
        initSpinner()
    }

    companion object {
        //静态属性如果是个常量，则还要添加修饰符const
        const val EVENT = "com.jimmy.complex.fragment.BroadcastFragment"

        fun newInstance(position: Int, image_id: Int, desc: String): BroadcastFragment {
            val fragment = BroadcastFragment()
            val bundle = Bundle()
            bundle.putInt("position", position)
            bundle.putInt("image_id", image_id)
            bundle.putString("desc", desc)
            fragment.arguments = bundle
            return fragment
        }
    }
}