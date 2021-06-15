package com.shaadi.peopleinteractive.network

import android.content.Context
import com.google.gson.GsonBuilder
import com.shaadi.peopleinteractive.utils.Constants
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@Module
@InstallIn(ViewModelComponent::class)
class RetrofitClient @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val builder: OkHttpClient.Builder by lazy {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val builder = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor {
                val builder = it.request().newBuilder()
                it.proceed(builder.build())
            }
        builder.readTimeout(Constants.CONNECTION_TIME, TimeUnit.SECONDS)
            .writeTimeout(Constants.CONNECTION_TIME, TimeUnit.SECONDS)
            .connectTimeout(Constants.CONNECTION_TIME, TimeUnit.SECONDS)

        builder
    }

    public fun build(baseUrl: String): Retrofit {
        val gsonBuilder = GsonBuilder()
        val converter = GsonConverterFactory.create(gsonBuilder.create())
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converter)
            .client(builder.build())
            .build()
    }

    public fun build(): Retrofit {
        return build(Constants.BASE_URL)
    }
}