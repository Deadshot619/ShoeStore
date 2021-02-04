package com.udacity.shoestore.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.udacity.shoestore.MainActivity
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ActivityLoginBinding
import com.udacity.shoestore.databinding.ActivitySplashBinding
import com.udacity.shoestore.ui.login.LoginActivity
import com.udacity.shoestore.utils.userLoggedInData

class SplashActivity : AppCompatActivity() {

    private val mBinding by lazy { ActivitySplashBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
    }

    override fun onResume() {
        super.onResume()

        val timer = object : CountDownTimer(3000, 1000){
            override fun onTick(p0: Long) {
            }

            override fun onFinish() {
                checkIfUserIsLoggedIn()
            }

        }
        timer.start()
    }

    fun checkIfUserIsLoggedIn(){
        if (userLoggedInData == null)
            startActivity(Intent(this, LoginActivity::class.java))
        else
            startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}