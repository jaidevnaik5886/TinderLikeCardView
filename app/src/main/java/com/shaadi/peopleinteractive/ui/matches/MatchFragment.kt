package com.shaadi.peopleinteractive.ui.matches

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.shaadi.peopleinteractive.R
import com.shaadi.peopleinteractive.base.BaseFragment
import com.shaadi.peopleinteractive.base.BaseViewModel
import com.shaadi.peopleinteractive.common.ListItem
import com.shaadi.peopleinteractive.common.RecyclerViewCallback
import com.shaadi.peopleinteractive.common.addDataSource
import com.shaadi.peopleinteractive.databinding.FragmentMatchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchFragment : BaseFragment<FragmentMatchBinding>(R.layout.fragment_match) {


    val model: MatchViewModel by viewModels()

    override fun attachBinding() {
        binding.handler = this
        binding.vm = model
        model.matches.observe(viewLifecycleOwner, {
            binding.rvMatches.addDataSource(
                it.data?.results?: emptyList(),
                R.layout.item_match,
                object : RecyclerViewCallback {
                    override fun onClick(item: ListItem) {

                    }
                },
                RecyclerView.HORIZONTAL,
                R.string.no_data_available
            )
        })
    }

    override fun getVM(): BaseViewModel = model


}

