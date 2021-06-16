package com.shaadi.peopleinteractive.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Database(
    entities = [MatchEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class PIDatabase : RoomDatabase() {

    abstract fun matchesDao(): MatchesDao
}