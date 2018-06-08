package com.example.nguyenngocsonc.myapplication.signin

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.nguyenngocsonc.myapplication.R
import com.example.nguyenngocsonc.myapplication.newfeed.NewFeedActivity
import com.example.nguyenngocsonc.myapplication.signup.SignupActivity
import kotlinx.android.synthetic.main.activity_signup.*

class SigninActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        btnSignUp.setOnClickListener {
            startActivity(Intent(this@SigninActivity, SignupActivity::class.java))
            finish()
        }

        btnSignIn.setOnClickListener {
            startActivity(Intent(this@SigninActivity, NewFeedActivity::class.java))
            finish()
        }
    }
}
