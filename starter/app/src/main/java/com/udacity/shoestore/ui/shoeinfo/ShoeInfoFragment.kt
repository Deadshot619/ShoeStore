package com.udacity.shoestore.ui.shoeinfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.udacity.shoestore.MainViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeInfoBinding
import com.udacity.shoestore.databinding.FragmentShoeListBinding


class ShoeInfoFragment : Fragment() {

    private val mBinding: FragmentShoeInfoBinding by lazy { FragmentShoeInfoBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return mBinding.root
    }
}