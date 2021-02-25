package com.jimmy.simple

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_alert_dialog.*
import kotlinx.android.synthetic.main.activity_login_forget.*

class LoginForgetActivity : AppCompatActivity() {

    private var mVerifyCode: String? = null
    private var mPhone: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_forget)
        btn_verifycode.setOnClickListener { getVerifycode() }
        btn_confirm.setOnClickListener { doConfirm() }
        mPhone = intent.getStringExtra("phone")
    }

    private fun getVerifycode() {
        if (mPhone == null || mPhone!!.length < 11) {
            Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_LONG).show();
            return
        }
        mVerifyCode = String.format("%06d", (Math.random() * 1000000 % 1000000).toInt())

        val builder = AlertDialog.Builder(this)
        builder.setMessage("\"手机号$mPhone，本次验证码是$mVerifyCode，请输入验证码\", \"请记住验证码\"")
        builder.setPositiveButton("好的") { dialog, which -> }
        builder.create().show()
    }

    private fun doConfirm() {
        val password_first = et_password_first.text.toString()
        val password_second = et_password_second.text.toString()
        if (password_first.isBlank() || password_first.length < 6 ||
            password_second.isBlank() || password_second.length < 6
        ) {
            Toast.makeText(this, "请输入正确的新密码", Toast.LENGTH_LONG).show();
            return
        } else if (password_first != password_second) {
            Toast.makeText(this, "两次输入的新密码不一致", Toast.LENGTH_LONG).show();
            return
        } else if (et_verifycode.text.toString() != mVerifyCode) {
            Toast.makeText(this, "请输入正确的验证码", Toast.LENGTH_LONG).show();
            return
        } else {
            Toast.makeText(this, "密码修改成功", Toast.LENGTH_LONG).show();
            //携带修改后的新密码返回主登录页面
            val intent = Intent().putExtra("new_password", password_first)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}
