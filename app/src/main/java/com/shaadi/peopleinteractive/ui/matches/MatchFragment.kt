package com.shaadi.peopleinteractive.ui.matches

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.shaadi.peopleinteractive.R
import com.shaadi.peopleinteractive.base.BaseEvent
import com.shaadi.peopleinteractive.base.BaseFragment
import com.shaadi.peopleinteractive.base.BaseViewModel
import com.shaadi.peopleinteractive.common.ListItem
import com.shaadi.peopleinteractive.common.RecyclerViewCallback
import com.shaadi.peopleinteractive.common.addDataSource
import com.shaadi.peopleinteractive.database.MatchEntity
import com.shaadi.peopleinteractive.databinding.FragmentMatchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchFragment : BaseFragment<FragmentMatchBinding>(R.layout.fragment_match) {


    val model: MatchViewModel by viewModels()

    override fun attachBinding() {
        binding.handler = this
        binding.vm = model
        model.getPeopleMatches().observe(viewLifecycleOwner, {
            binding.rvMatches.addDataSource(
                it?: emptyList(),
                R.layout.item_match,
                object : RecyclerViewCallback {
                    override fun onDeclineClicked(item: ListItem) {
                        model.onDeclineClicked(item as MatchEntity)
                    }

                    override fun onAcceptClicked(item: ListItem) {
                        model.onAcceptClicked(item as MatchEntity)
                    }
                },
                RecyclerView.HORIZONTAL,
                R.string.no_data_available
            )
        })
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView( binding.rvMatches)
    }

    override fun getVM(): BaseViewModel = model

    override fun handleEvent(event: BaseEvent) {
        super.handleEvent(event)
        when(event) {
            is RefreshEvent -> {
                binding.rvMatches.adapter!!.notifyDataSetChanged()
            }
        }
    }

}


