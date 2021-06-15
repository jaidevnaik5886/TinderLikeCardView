package com.shaadi.peopleinteractive.network

import com.shaadi.peopleinteractive.responses.matches.MatchesResponse
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Response
import retrofit2.http.*
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class DataRepository @Inject constructor(private val client: RetrofitClient) {


    suspend fun getMatches(results: String) : Response<MatchesResponse>{
        return client.build().create(MatchesAPI::class.java)
            .getMatches(results)
    }

}

interface MatchesAPI {

    @GET(value = "api/")
    suspend fun getMatches(@Query("results") results: String): Response<MatchesResponse>


}