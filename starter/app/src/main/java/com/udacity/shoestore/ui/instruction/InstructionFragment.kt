package com.udacity.shoestore.ui.instruction

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentInstructionBinding
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.ui.login.LoginViewModel
import com.udacity.shoestore.utils.isInstructionCompleted

class InstructionFragment : Fragment() {

    companion object {
        fun newInstance() = InstructionFragment()
    }

    private val mBinding by lazy { FragmentInstructionBinding.inflate(layoutInflater) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpClickListeners()
    }

    private fun setUpClickListeners() {
        mBinding.btnStartShopping.setOnClickListener {
            isInstructionCompleted = true
            findNavController().navigate(InstructionFragmentDirections.actionInstructionFragmentToShoeListFragment())
        }
    }
}