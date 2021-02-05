package com.udacity.shoestore.ui.shoelist

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.LayoutShoeInfoBinding
import com.udacity.shoestore.utils.showToast


class ShoeListFragment : Fragment() {

    companion object {
        fun newInstance() = ShoeListFragment()
    }

    private val mBinding: FragmentShoeListBinding by lazy { FragmentShoeListBinding.inflate(
        layoutInflater
    ) }
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.run {
            shoeListData.observe(viewLifecycleOwner, Observer {
                it?.let {
                    for (data in it.reversed()) {
                        val shoeView = LayoutShoeInfoBinding.inflate(layoutInflater)
                        shoeView.data = data
                        shoeView.root.layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        )
                        mBinding.llListOfShoes.addView(shoeView.root)
                        mBinding.executePendingBindings()
                    }
                }
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.shoe_list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_add_shoe -> {
                findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeInfoFragment(isEdit = false))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}