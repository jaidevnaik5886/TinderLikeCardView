package com.shaadi.peopleinteractive.ui.matches

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shaadi.peopleinteractive.base.BaseViewModel
import com.shaadi.peopleinteractive.database.MatchEntity
import com.shaadi.peopleinteractive.network.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MatchViewModel @Inject constructor(
    val dataRepository: DataRepository
) : BaseViewModel() {

    val matches = MutableLiveData<List<MatchEntity>>()

    init {
        fetchMatches()
    }

    private fun fetchMatches() {
        launch {
            val matchesList = dataRepository.getMatches("10")
            dataRepository.deleteOldMatch()
            for (match in matchesList) {
                dataRepository.upsert(match.convertTo(MatchEntity::class.java)!!)
            }
        }
    }

    fun getPeopleMatches(): LiveData<List<MatchEntity>> = dataRepository.getMatches()

    fun onDeclineClicked(matchEntity: MatchEntity) {
        launch {
//            matchEntity.status =
//            dataRepository.upsert(matchEntity)
        }
    }

}

