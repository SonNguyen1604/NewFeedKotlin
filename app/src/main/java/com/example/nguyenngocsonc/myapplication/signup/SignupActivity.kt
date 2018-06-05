package com.example.nguyenngocsonc.myapplication.signup

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.nguyenngocsonc.myapplication.R
import com.example.nguyenngocsonc.myapplication.signin.SigninActivity
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        btn_sign_in.setOnClickListener {
            startActivity(Intent(this@SignupActivity, SigninActivity::class.java))
            finish()
        }
    }
}
