package com.veh.demo.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.veh.demo.network.owner.OwnerResponse

class Converters {

    @TypeConverter
    fun fromOwner(owner: OwnerResponse.Data.Owner): String {
        return Gson().toJson(owner)
    }

//    @TypeConverter
//    fun toOwner(strOwner: String): OwnerResponse.Data.Owner {
//        return  Gson().fromJson(strOwner,OwnerResponse.Data.Owner)
//
//    }
}