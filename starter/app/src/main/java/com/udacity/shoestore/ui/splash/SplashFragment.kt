package com.udacity.shoestore.ui.splash

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainActivity
import com.udacity.shoestore.databinding.ActivitySplashBinding
import com.udacity.shoestore.utils.isInstructionCompleted
import com.udacity.shoestore.utils.userLoggedInData

class SplashFragment : Fragment() {

    private val mBinding by lazy { ActivitySplashBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return mBinding.root
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
        when {
            userLoggedInData == null -> findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
            isInstructionCompleted -> findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToShoeListFragment())
            else -> findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToWelcomeFragment(""))
        }
    }
}