package com.jimmy.simple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_image.*

/**
 * 图像视图ImageView
 */
class ImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        /**
         * setImageDrawable:设置图片扽Drawable对象
         * setImageResource:设置图形扽资源ID
         * setImageBitmap:设置图形扽位图对象
         */
        iv_scale.setImageResource(R.drawable.apple1)
        //设置图片的拉伸类型
        /**
         * 1：ScaleType.FIT_XY：拉伸图片使之正好填满视图
         * 2：ScaleType.FIT_START 拉伸图片使之位于视图上部
         * 3：ScaleType.FIT_CENTER 拉伸图片使之位于视图中间
         * 4：ScaleType.FIT_END  拉伸图片使之位于视图下部
         * 5：ScaleType.CENTER  保持图片原始尺寸，并使之位于视图中间
         * 5：ScaleType.CENTER_CROP  拉伸图片使之充满视图，并位于中间
         * 6：ScaleType.CENTER_INSIDE  使图片位于视图中间，当图片大于视图时，CENTER_INSIDE等同于fitCenter,当图片尺寸小于视图时，CENTER_INSIDE等同于center
         */

        btn_center.setOnClickListener { iv_scale.scaleType = ImageView.ScaleType.CENTER }
        btn_fitCenter.setOnClickListener { iv_scale.scaleType = ImageView.ScaleType.FIT_CENTER }
        btn_centerCrop.setOnClickListener { iv_scale.scaleType = ImageView.ScaleType.CENTER_CROP }
        btn_centerInside.setOnClickListener {
            iv_scale.scaleType = ImageView.ScaleType.CENTER_INSIDE
        }
        btn_fitXY.setOnClickListener { iv_scale.scaleType = ImageView.ScaleType.FIT_XY }
        btn_fitStart.setOnClickListener { iv_scale.scaleType = ImageView.ScaleType.FIT_START }
        btn_fitEnd.setOnClickListener { iv_scale.scaleType = ImageView.ScaleType.FIT_END }
    }
}
