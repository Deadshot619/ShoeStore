package com.udacity.shoestore.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainActivity
import com.udacity.shoestore.databinding.ActivityLoginBinding
import com.udacity.shoestore.databinding.ActivitySplashBinding
import com.udacity.shoestore.utils.*

class LoginFragment: Fragment() {

    private val mBinding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val mViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mBinding.run {
            lifecycleOwner = viewLifecycleOwner
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
//        startActivity(Intent(requireActivity(), MainActivity::class.java))
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(""))
    }


    override fun onResume() {
        super.onResume()
        (activity as MainActivity)?.supportActionBar?.hide()

    }

    override fun onStop() {
        super.onStop()
        (activity as MainActivity)?.supportActionBar?.show()

    }

}