package com.veh.demo.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.veh.demo.network.owner.OwnerResponse

@Dao
interface OwnerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(owner:OwnerResponse.Data):Long

    @Query("SELECT * FROM  ownerdata")
    fun getAllOwners():LiveData<List<OwnerResponse.Data>>

}