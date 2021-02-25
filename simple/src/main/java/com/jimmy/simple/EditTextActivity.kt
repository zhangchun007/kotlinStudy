package com.jimmy.simple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_edit_text.*

class EditTextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_text)
        //InputType类型扽输入类型
        /**
         *1:InputType.TYPE_CLASS_NUMBER 只能是数字
         *2:InputType.TYPE_CLASS_TEXT 所有文本
         *3:InputType.TYPE_CLASS_DATETIME 只能是日期时间
         *4:InputType.TYPE_TEXT_VARIATION_NORMAL 正常显示
         *5:InputType.TYPE_TEXT_VARIATION_NORMAL 正常显示
         *6:InputType.TYPE_TEXT_VARIATION_PASSWORD 密文显示
         *7:InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD 明文显示
         */

        //⚠️不能直接给EditText控件的text属性赋值
        //否则会报错：Editable与string 类型不匹配
        //只能调用setText方法对Editext控件设置文本

        et_phone.setText("")

        //显示明文数字
        et_phone.inputType = InputType.TYPE_CLASS_NUMBER
        //明文显示
//        et_phone.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD

        //隐藏密码
//        et_phone.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

        et_phone.addTextChangedListener(EditWatcher())

    }

    private inner class EditWatcher : TextWatcher {


        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            var str = s.toString()
            //发现输入回车或者换行符
            if (str.indexOf("\r") >= 0 || str.indexOf("\n") >= 0) {
                //去掉会车符号和换行符号
                str = str.replace("\r", "").replace("\n", "")
            }
            if (str.length >= 11)
                tv_phone.text = "您输入扽手机号码是：$str"
        }

    }
}
