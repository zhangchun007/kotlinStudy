package com.jimmy.simple

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_relative_layout.*

class RelativeLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relative_layout)
        btn_add_left.setOnClickListener { v ->
            val rl_params = RelativeLayout.LayoutParams(100, 100)
            rl_params.addRule(RelativeLayout.ABOVE, v.id)
            rl_params.addRule(RelativeLayout.ALIGN_TOP, v.id)
            addNewView(rl_params)
        }
        btn_add_above.setOnClickListener { v ->
            val rl_params = RelativeLayout.LayoutParams(100, 100)
            rl_params.addRule(RelativeLayout.ABOVE, v.id)
            rl_params.addRule(RelativeLayout.ALIGN_LEFT, v.id)
            addNewView(rl_params)
        }
        btn_add_right.setOnClickListener { v ->
            val rl_params = RelativeLayout.LayoutParams(100, 100)
            rl_params.addRule(RelativeLayout.RIGHT_OF, v.id)
            rl_params.addRule(RelativeLayout.ALIGN_BOTTOM, v.id)
            addNewView(rl_params)
        }
        btn_add_below.setOnClickListener { v ->
            val rl_params = RelativeLayout.LayoutParams(100, 100)
            rl_params.addRule(RelativeLayout.BELOW, v.id)
            rl_params.addRule(RelativeLayout.ALIGN_RIGHT, v.id)
            addNewView(rl_params)
        }
        btn_add_center.setOnClickListener { v ->
            val rl_params = RelativeLayout.LayoutParams(100, 100)
            rl_params.addRule(RelativeLayout.CENTER_IN_PARENT)
            addNewView(rl_params)
        }
        btn_add_parent_left.setOnClickListener { v ->
            val rl_params = RelativeLayout.LayoutParams(100, 100)
            rl_params.addRule(RelativeLayout.ALIGN_PARENT_LEFT)
            rl_params.addRule(RelativeLayout.CENTER_VERTICAL)
            addNewView(rl_params)
        }
        btn_add_parent_top.setOnClickListener { v ->
            val rl_params = RelativeLayout.LayoutParams(100, 100)
            rl_params.addRule(RelativeLayout.ALIGN_PARENT_TOP)
            rl_params.addRule(RelativeLayout.CENTER_HORIZONTAL)
            addNewView(rl_params)
        }
        btn_add_parent_right.setOnClickListener { v ->
            val rl_params = RelativeLayout.LayoutParams(100, 100)
            rl_params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
            addNewView(rl_params)
        }
        btn_add_parent_bottom.setOnClickListener { v ->
            val rl_params = RelativeLayout.LayoutParams(100, 100)
            rl_params.addRule(RelativeLayout.ALIGN_BOTTOM)
            addNewView(rl_params)
        }
    }

    private fun addNewView(rl_params: RelativeLayout.LayoutParams) {
        var v = View(this)
        v.setBackgroundColor(Color.GREEN)
        v.layoutParams = rl_params
        v.setOnLongClickListener { vv -> rl_content.removeView(vv); true }
        rl_content.addView(v)
    }

    //根据参照物与方位类型添加下级视图
    private fun addNewView(align: Int, referId: Int) {
        var v = View(this)
        v.setBackgroundColor(Color.GREEN)
        val rl_params = RelativeLayout.LayoutParams(100, 100)
        rl_params.addRule(align, referId)
        v.layoutParams = rl_params
        v.setOnLongClickListener { vv -> rl_content.removeView(vv); true }
        rl_content.addView(v)
    }

}
