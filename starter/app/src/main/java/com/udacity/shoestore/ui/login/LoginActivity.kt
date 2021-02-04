package com.udacity.shoestore.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainActivity
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ActivityLoginBinding
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.utils.*

class LoginActivity : AppCompatActivity() {

    private val mBinding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val mViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)


        mBinding.run {
            lifecycleOwner = this@LoginActivity
            viewModel = mViewModel
        }

        setUpClickListeners()
    }


    private fun setUpClickListeners(){
        mBinding.run {
//           Login
            btnLogin.setOnClickListener {
                if (mViewModel.userDetail.username.trim().isEmpty() || mViewModel.userDetail.password.trim().isEmpty()){
                    showToast("Please enter Username & Password")
                    return@setOnClickListener
                }

                val tempUserData = getUserLoginDetail(mViewModel.userDetail.username)
                when {
                    tempUserData == null -> showToast("User doesn't exist, click on 'New Login' to continue")
                    tempUserData.password != mViewModel.userDetail.password -> showToast("Incorrect Password")
                    else -> {
                        goToWelcomePage()
                    }//findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(username = tempUserData.username))
                }
            }

            //Cancel
            btnCancel.setOnClickListener {
                mBinding.etEmail.setText("")
                mBinding.etPassword.setText("")
            }

            //New Login/User
            btnNewLogin.setOnClickListener {
                if (mViewModel.userDetail.username.trim().isEmpty() || mViewModel.userDetail.password.trim().isEmpty()){
                    showToast("Enter Username & Password")
                } else if (isUserAlreadyPresent(mViewModel.userDetail.username)){
                    showToast("User already exists")
                } else {
                    addLoginUserData(mViewModel.userDetail)
//                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(username = mViewModel.userDetail.username))
                    goToWelcomePage()
                }
            }
        }
    }

    private fun goToWelcomePage(){
        userLoggedInData = mViewModel.userDetail
        startActivity(Intent(this, MainActivity::class.java))
    }

}