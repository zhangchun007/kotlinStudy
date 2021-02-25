package com.jimmy.simple

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import com.jimmy.simple.util.DateUtil
import com.jimmy.simple.util.ViewUtil
import kotlinx.android.synthetic.main.activity_act_first.*
import kotlinx.android.synthetic.main.activity_login_page.*

class LoginPageActivity : AppCompatActivity() {

    private val mRequestCode = 0
    private var bRemember = false
    private var mPassword = "111111"
    private var mVerifyCode: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)
        rg_login.setOnCheckedChangeListener { group, checkedId -> resetHint(checkedId) }
        ck_remember.setOnCheckedChangeListener { buttonView, isChecked -> bRemember = isChecked }
        et_phone.addTextChangedListener(HideTextWatcher(et_phone))
        et_password.addTextChangedListener(HideTextWatcher(et_password))
        btn_forget.setOnClickListener { doForget() }
        btn_login.setOnClickListener { doLogin() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == mRequestCode && data != null) {
            //用户密码已改为新密码
            mPassword = data.getStringExtra("new_password")
        }
    }

    private fun resetHint(checkedId: Int) {
        if (checkedId == R.id.rb_password) {
            tv_password.text = "登陆密码"
            et_password.hint = "请输入密码"
            btn_forget.text = "忘记密码"
            ck_remember.visibility = View.VISIBLE
        } else if (checkedId == R.id.rb_verifycode) {
            tv_password.text = "　验证码："
            et_password.hint = "请输入验证码"
            btn_forget.text = "获取验证码"
            ck_remember.visibility = View.INVISIBLE
        }
    }

    private fun doForget() {
        val phone = et_phone.text.toString()
        if (phone.isBlank() || phone.length < 11) {
            Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_LONG).show();
            return
        }
        if (rb_password.isChecked) {
            //携带手机号码跳到密码找回页面
            val intent = Intent(this, LoginForgetActivity::class.java)
            intent.putExtra("phone", phone)
            startActivityForResult(intent, mRequestCode)
        } else if (rb_verifycode.isChecked) {
            mVerifyCode = String.format("%06d", (Math.random() * 1000000 % 1000000).toInt())
            val builder = AlertDialog.Builder(this)
            builder.setTitle("请记住验证码")
            builder.setMessage("手机号$phone，本次验证码是$mVerifyCode，请输入验证码")
            builder.setPositiveButton("好的") { dialog, which -> }
            builder.create().show()
        }
    }

    private fun doLogin() {
        val phone = et_phone.text.toString()
        if (phone.isBlank() || phone.length < 11) {
            Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_LONG).show();
            return
        }
        if (rb_password.isChecked) {
            if (et_password.text.toString() != mPassword) {
                Toast.makeText(this, "请输入正确的密码", Toast.LENGTH_LONG).show();
                return
            } else {
                loginSuccess()
            }
        } else if (rb_verifycode.isChecked) {
            if (et_password.text.toString() != mVerifyCode) {
                Toast.makeText(this, "请输入正确的验证码", Toast.LENGTH_LONG).show();
                return
            } else {
                loginSuccess()
            }
        }
    }

    //从修改密码页面返回登录页面，要清空密码的输入框
    override fun onRestart() {
        et_password.setText("")
        super.onRestart()
    }


    private fun loginSuccess() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("登录成功")
        builder.setMessage("您的手机号码是${et_phone.text}，恭喜你通过登录验证，点击“确定”按钮返回上个页面")
        builder.setPositiveButton("确定返回") { dialog, which -> finish() }
        builder.setNegativeButton("我再看看") { dialog, which -> }
        builder.create().show()
    }

    private inner class HideTextWatcher(val mView: EditText) : TextWatcher {
        private val mMaxLength: Int = ViewUtil.getMaxLength(mView)
        private var mStr: CharSequence? = null

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            mStr = s
        }

        override fun afterTextChanged(s: Editable?) {
            if (mStr.isNullOrEmpty())
                return
            if (mStr!!.length == 11 && mMaxLength == 11 || mStr!!.length == 6 && mMaxLength == 6) {
                //隐藏输入法面板，ViewUtil类参见本书附录源码
                ViewUtil.hideOneInputMethod(this@LoginPageActivity, mView)
            }
        }


    }
}
