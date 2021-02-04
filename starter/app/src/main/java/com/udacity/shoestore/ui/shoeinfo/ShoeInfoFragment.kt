package com.udacity.shoestore.ui.shoeinfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeInfoBinding
import com.udacity.shoestore.utils.showToast


class ShoeInfoFragment : Fragment() {

    private val mBinding: FragmentShoeInfoBinding by lazy { FragmentShoeInfoBinding.inflate(layoutInflater) }
    private val mViewModel: MainViewModel by activityViewModels()

    var isEdit: Boolean? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mBinding.lifecycleOwner = viewLifecycleOwner
        mBinding.viewModel = mViewModel

        arguments?.let {
            isEdit = ShoeInfoFragmentArgs.fromBundle(it).isEdit
        }

        setUpClickListeners()
    }

    private fun setUpClickListeners() {
        mBinding.run {
//            Save/Add
            btnSaveAdd.setOnClickListener{
                isEdit?.let {
                    if (it)
                        showToast("Edit")
                    else {
                        mViewModel.addNewShoeListData()
                        findNavController().navigateUp()
                    }
                }
            }

//            Cancel
            btnCancel.setOnClickListener {
                findNavController().navigateUp()
            }

        }
    }
}