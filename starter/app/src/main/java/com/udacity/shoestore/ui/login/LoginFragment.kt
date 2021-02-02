package com.udacity.shoestore.ui.login

import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.utils.*

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private val mBinding by lazy { FragmentLoginBinding.inflate(layoutInflater) }
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
                   else -> findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(username = tempUserData.username))
               }
           }

           //Cancel
           btnCancel.setOnClickListener {

           }

           //New Login/User
           btnNewLogin.setOnClickListener {
               if (mViewModel.userDetail.username.trim().isEmpty() || mViewModel.userDetail.password.trim().isEmpty()){
                    showToast("Enter Username & Password")
               } else if (isUserAlreadyPresent(mViewModel.userDetail.username)){
                   showToast("User already exists")
               } else {
                   addLoginUserData(mViewModel.userDetail)
                   findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(username = mViewModel.userDetail.username))
               }
           }
       }
   }
}