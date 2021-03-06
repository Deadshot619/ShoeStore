package com.udacity.shoestore.ui.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentWelcomeBinding
import com.udacity.shoestore.utils.addLoginUserData
import com.udacity.shoestore.utils.showToast
import com.udacity.shoestore.utils.userLoggedInData

class WelcomeFragment : Fragment() {

    companion object {
        fun newInstance() = WelcomeFragment()
    }

    private val mBinding by lazy { FragmentWelcomeBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val user = userLoggedInData
        mBinding.tvUsername.text = user?.username

        setUpClickListeners()
    }


    private fun setUpClickListeners(){
        mBinding.run {
//           Goto Instruction
            btnGotoInstruction.setOnClickListener {
                findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToInstructionFragment())
            }
        }
    }
}