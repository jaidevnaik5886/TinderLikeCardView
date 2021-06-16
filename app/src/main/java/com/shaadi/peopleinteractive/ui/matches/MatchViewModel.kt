package com.shaadi.peopleinteractive.ui.matches

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shaadi.peopleinteractive.base.BaseEvent
import com.shaadi.peopleinteractive.base.BaseViewModel
import com.shaadi.peopleinteractive.database.MatchEntity
import com.shaadi.peopleinteractive.network.DataRepository
import com.shaadi.peopleinteractive.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MatchViewModel @Inject constructor(
    val dataRepository: DataRepository,
) : BaseViewModel() {

    val matches = MutableLiveData<List<MatchEntity>>()

    init {
        if (isInternetAvailable()) {
            fetchMatches()
        }
    }

    private fun isInternetAvailable(): Boolean {
//        val connectionManager = getApplication<BaseApplication>()
//            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            val activeNetwork = connectionManager.activeNetwork ?: return false
//            val capabilities =
//                connectionManager.getNetworkCapabilities(activeNetwork) ?: return false
//            return when {
//                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
//                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
//                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
//                else -> false
//            }
//        } else {
//            connectionManager.activeNetworkInfo.run {
//                return when (this!!.type) {
//                    TYPE_WIFI -> true
//                    TYPE_MOBILE -> true
//                    TYPE_ETHERNET -> true
//                    else -> false
//                }
//            }
//        }
       return false
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
            matchEntity.status = Constants.DECLINE
            dataRepository.upsert(matchEntity)
        }.apply {
            sendEvent(RefreshEvent())
        }
    }

    fun onAcceptClicked(matchEntity: MatchEntity) {
        launch {
            matchEntity.status = Constants.ACCEPT
            dataRepository.upsert(matchEntity)
        }.apply {
            sendEvent(RefreshEvent())
        }
    }

}

class RefreshEvent() : BaseEvent()


