package com.jimmy.storage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.ViewUtils
import com.jimmy.storage.util.Preference
import com.jimmy.storage.util.ViewUtil
import kotlinx.android.synthetic.main.activity_login_share.*

class LoginShareActivity : AppCompatActivity() {

    private var phone: String by Preference(this, "phone", "")
    private var password: String by Preference(this, "password", "")
    private var mRequestCode = 0
    private var bRemember = false
    private var mPassword = "111111"
    private var mVerifyCode: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_share)

        rg_login.setOnCheckedChangeListener { group, checkedId -> resetHint(checkedId) }
        ck_remember.setOnCheckedChangeListener { buttonView, isChecked -> bRemember = isChecked }
        et_phone.addTextChangedListener(HideTextWatcher(et_phone))
        et_password.addTextChangedListener(HideTextWatcher(et_password))
        btn_forget.setOnClickListener { doForget() }
        btn_login.setOnClickListener { doLogin() }
        et_phone.setText(phone)
        et_password.setText(password)
    }


    private fun resetHint(checkedId: Int) {
        if (checkedId == R.id.rb_password) {
            tv_password.text = "登陆密码"
            et_password.hint = "请输入密码"
            btn_forget.text = "忘记密码"
            ck_remember.visibility = View.VISIBLE
        } else if (checkedId == R.id.rb_verifycode) {
            tv_password.text = "验证码"
            et_password.hint = "请输入验证码"
            btn_forget.text = "获取验证码"
            ck_remember.visibility = View.INVISIBLE
        }

    }

    private inner class HideTextWatcher(private val mView: EditText) : TextWatcher {
        private val mMaxLength: Int = ViewUtil.getMaxLength(mView)
        private var mStr: CharSequence? = null

        override fun afterTextChanged(s: Editable?) {
            if (mStr.isNullOrEmpty()) return
            if (mStr!!.length == 11 && mMaxLength == 11 || mStr!!.length == 6 && mMaxLength == 6) {
                ViewUtil.hideOneInputMethod(this@LoginShareActivity, mView)
            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            mStr = s
        }

    }

    private fun doForget() {
        val phone = et_phone.text.toString()
        if (phone.isBlank() || phone.length < 11) {
            Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show()
            return
        }
        if (rb_password.isChecked) {
            val intent = Intent(this, LoginForgetActivity::class.java)
            intent.putExtra("phone", phone)
            startActivityForResult(intent, mRequestCode)
        } else if (rb_verifycode.isChecked) {
            mVerifyCode = String.format("%06d", (Math.random() * 1000000 % 1000000).toInt())
            val builder = AlertDialog.Builder(this)
            builder.setMessage("\"手机号$phone，本次验证码是$mVerifyCode，请输入验证码\", \"请记住验证码\"")
            builder.setPositiveButton("好的") { dialog, which -> }
            builder.create().show()
        }

    }

    private fun doLogin() {
        val phone = et_phone.text.toString()
        if (phone.isBlank() || phone.length < 11) {
            Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show()
            return
        }
        if (rb_password.isChecked) {
            if (et_password.text.toString() != mPassword) {
                Toast.makeText(this, "请输入正确的密码", Toast.LENGTH_SHORT).show()
                return
            } else {
                loginSuccess()
            }
        } else if (rb_verifycode.isChecked) {
            if (et_password.text.toString() != mVerifyCode) {
                Toast.makeText(this, "请输入正确的验证码", Toast.LENGTH_SHORT).show()
                return
            }
        }
    }

    private fun loginSuccess() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("\"您的手机号码是${et_phone.text}，恭喜你通过登录验证，点击“确定”按钮返回上个页面\", \"登录成功\"")
        builder.setPositiveButton("确定返回") { dialog, which -> finish() }
        builder.setNegativeButton("我再看看") { dialog, which -> }
        builder.create().show()

        if (bRemember) {
            phone = et_phone.text.toString()
            password = et_password.text.toString()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == mRequestCode && data != null) {
            //用户密码已经改为新密码
            mPassword = data.getStringExtra("new_password")
        }
    }
}
