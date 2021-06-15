package com.shaadi.peopleinteractive.ui.matches

import androidx.lifecycle.MutableLiveData
import com.shaadi.peopleinteractive.base.BaseViewModel
import com.shaadi.peopleinteractive.network.DataRepository
import com.shaadi.peopleinteractive.responses.matches.MatchesResponse
import com.shaadi.peopleinteractive.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MatchViewModel @Inject constructor(
    val dataRepository: DataRepository
) : BaseViewModel() {

    val matches = MutableLiveData<Resource<MatchesResponse>>()
    var matchesResponse: MatchesResponse? = null

    init {
        getMatches()
    }

    private fun getMatches() {
        launch {
            matches.postValue(mapToResults(dataRepository.getMatches("10")))
        }
    }

    private fun mapToResults(matches: Response<MatchesResponse>): Resource<MatchesResponse> {
        if (matches.isSuccessful) {
            matches.body()?.let { resultResponse ->
                if (matchesResponse == null) {
                    matchesResponse = resultResponse
                }
                return Resource.Success(matchesResponse ?: resultResponse)
            }
        }
        return Resource.Error(matches.message())
    }


}
