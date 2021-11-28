package com.veh.demo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.veh.demo.network.owner.OwnerResponse

@Database(
    entities = [OwnerResponse.Data::class],
    version = 1
)
abstract class Ownerdatabase : RoomDatabase() {

    abstract fun getOwnerDao(): OwnerDao

    companion object {
        @Volatile   // other threads can see changes if any thread change database instance
        private var instance: Ownerdatabase? = null
        private val LOCK = Any()

        /** create only single instance of database at once **/
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            Ownerdatabase::class.java,
            "vehicle_owner_db.db"
        ).build()
    }
}