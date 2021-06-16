package com.shaadi.peopleinteractive.network

import com.shaadi.peopleinteractive.database.MatchEntity
import com.shaadi.peopleinteractive.database.MatchesDao
import com.shaadi.peopleinteractive.database.PIDatabase
import com.shaadi.peopleinteractive.responses.matches.MatchResults
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.http.*
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class DataRepository @Inject constructor(private val client: RetrofitClient, private val matchesDao: MatchesDao) {


    suspend fun getMatches(results: String) : List<MatchResults>{
        return client.build().create(MatchesAPI::class.java)
            .getMatches(results).mapToResult()
    }

    suspend fun upsert(match: MatchEntity) = matchesDao.upsert(match)

    fun getMatches() = matchesDao.getAllMatches()

    suspend fun deleteOldMatch() = matchesDao.delete()

}

interface MatchesAPI {

    @GET(value = "api/")
    suspend fun getMatches(@Query("results") results: String): BaseResponse<List<MatchResults>>


}