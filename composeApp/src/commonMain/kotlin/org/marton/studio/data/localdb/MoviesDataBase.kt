package org.marton.studio.data.localdb

import androidx.room.Database
import androidx.room.RoomDatabase
import org.marton.studio.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MoviesDataBase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao
}