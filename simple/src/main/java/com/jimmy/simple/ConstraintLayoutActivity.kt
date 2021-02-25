package com.jimmy.simple

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle

import android.transition.TransitionManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

import kotlinx.android.synthetic.main.activity_constraint_layout.*

/**
 * Created by ouyangshen on 2017/8/27.
 */
@TargetApi(Build.VERSION_CODES.KITKAT)
class ConstraintLayoutActivity : AppCompatActivity() {
    private var isMoved = false
    private var lastViewId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint_layout)
        //6.2.3约束布局ConstraintLayout
        /**
         * 与控制方位有关的属性说明如下所示:
         * ●layout_constraintTop_toTopOf:该控件的顶部与另一个控件的顶部对齐。
         * ●layout_constraintTop_toBottompOf: 该控件的顶部与另一个控件的底部对齐。
         * ●layout_constraintBottom_toTopOf :该控件的底部与另一个控件的顶部对齐。
         * ●layout_constraintBottom_toBottomOf: 该控件的底部与另-个控件的底部对齐。
         * ●layout_constraintLeft_toLeftOf:该控件的左侧与另一个控件的左侧对齐
         * ●layout_constraintLeft_toRightOf :该控件的左侧与另一个控件的右侧对齐。
         * ●layout_constraintRight_toLefOf :该控件的右侧与另一个控件的左侧对齐。
         * ●layout_constraintRight_toRightOf :该控件的右侧与另一个控件的右侧对齐。
         *
         */

        //在代码中添加控件
        //若要利用代码给约束布局动态添加控件，则可照常调用addView方法。不同之处在于，新控件的布局参数必须使用约束布局的布局参数，即Constraintlayout.layoutParams,
        // 该参数通过setMargins/setMarginStart/setMarginEnd方法设置新控件与周围控件的间距。
        // 至于新控件与周围控件的位置约束关系，则参照ConstaintI ayout.LayoutParams的下列属性说明。
        /**
         * ●topToTop: 当前控件的顶部与指定ID的控件顶部对齐。
         * ●topToBottom: 当前控件的顶部与指定ID的控件底部对齐。
         * ●bottomToTop:当前控件的底部与指定ID的控件顶部对齐。
         * ●bottomToBottom:当前控件的底部与指定ID的控件底部对齐。
         * ●startToStart: 当前控件的左侧与指定ID的控件左侧对齐。
         * ●starToEnd:当前控件的左侧与指定ID的控件右侧对齐。
         * ●endToStart:当前控件的右侧与指定ID的控件左侧对齐。
         * ●endToEnd:当前控件的右侧与指定ID的控件右侧对齐。
         */


        lastViewId = cl_content.id
        btn_add_view.setOnClickListener { addNewView() }
        btn_move_hard.setOnClickListener { moveView() }
        btn_move_soft.setOnClickListener {
            //使用动画展示新旧约束关系的切换过程。如果删掉这行则不展示切换动画。该方法需要API19支持
            TransitionManager.beginDelayedTransition(cl_content)
            moveView()
        }

    }

    private fun addNewView() {
        val tv = TextView(this)
        tv.text = "长按删除该文本"
        val container = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        //设置控件左侧与另一个控件的左侧对齐
        //水平方向上只能使用start和end，因为left和right可能无法奏效
        container.startToStart = lastViewId
        //设置控件顶部与另一个控件的底部对齐
//        container.topToBottom = lastViewId
        container.topMargin = 100
        //左侧间距要使用Start，不能用Left，因为set.applyTo方法会清空Left的间距。marginStart需要API17支持
        container.marginStart = 30
        tv.layoutParams = container
        tv.setOnLongClickListener { vv -> cl_content.removeView(vv); true }
        lastViewId += 1000
        tv.id = lastViewId
        cl_content.addView(tv)
    }

    private fun moveView() {
        val margin = if (isMoved) 200 else 50
        //需要下载最新的constraint-layout，才能使用ConstraintSet
        val set = ConstraintSet()
        //复制原有的约束关系
        set.clone(cl_content)
        //清空该控件的约束关系
        //set.clear(tv_first.getId());
        //设置该控件的约束宽度
        //set.constrainWidth(tv_first.getId(), ConstraintLayout.LayoutParams.WRAP_CONTENT);
        //设置该控件的约束高度
        //set.constrainHeight(tv_first.getId(),ConstraintLayout.LayoutParams.WRAP_CONTENT);
        //设置该控件的顶部约束关系与间距
        //set.connect(tv_first.getId(), ConstraintSet.TOP, cl_content.getId(), ConstraintSet.BOTTOM, margin);
        //设置该控件的底部约束关系与间距
        //set.connect(tv_first.getId(), ConstraintSet.BOTTOM, cl_content.getId(), ConstraintSet.BOTTOM, margin);
        //设置该控件的左侧约束关系与间距
        set.connect(tv_first.id, ConstraintSet.START, cl_content.id, ConstraintSet.START, margin)
        //设置该控件的右侧约束关系与间距
        //set.connect(tv_first.getId(), ConstraintSet.END, cl_content.getId(), ConstraintSet.END, margin);
        //LEFT和RIGHT的margin不管用，只有START和END的margin才管用
        //set.setMargin(tv_init.getId(), ConstraintSet.START, 200);
        //启用新的约束关系
        set.applyTo(cl_content)
        isMoved = !isMoved
    }

}
