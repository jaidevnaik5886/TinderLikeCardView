package com.shaadi.peopleinteractive.database

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent ::class)
@Module
class DatabaseModule {

    @Provides
    fun provideMatchesDao(appDatabase: PIDatabase): MatchesDao {
        return appDatabase.matchesDao()
    }
    @Provides
    @Singleton
    fun providePIDatabase(@ApplicationContext appContext: Context): PIDatabase {
        return Room.databaseBuilder(
            appContext,
            PIDatabase::class.java,
            "pi_shaadi.db"
        ).build()
    }
}