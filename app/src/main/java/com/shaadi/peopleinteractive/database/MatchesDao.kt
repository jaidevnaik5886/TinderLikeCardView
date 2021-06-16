package com.shaadi.peopleinteractive.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface MatchesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(match: MatchEntity): Long

    @Query("SELECT * FROM matches")
    fun getAllMatches(): LiveData<List<MatchEntity>>

    @Query("DELETE FROM matches")
    suspend fun delete()
}