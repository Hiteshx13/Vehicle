package com.veh.demo.network.owner

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
data class OwnerResponse(
    val `data`: List<Data?>?
) {
    @Entity(
        tableName = "OwnerData"
    )
    @Keep
    data class Data(
        @PrimaryKey(autoGenerate = true)
        var id: Int?,
        val owner: Owner?,
        val userid: Int?,
        val vehicles: List<Vehicle?>?
    ) {
        @Keep
        data class Owner(
            val foto: String?,
            val name: String?,
            val surname: String?
        ) {
            fun getFullName(): String {
                return "$name $surname"
            }
        }

        @Keep
        data class Vehicle(
            val color: String?,
            val foto: String?,
            val make: String?,
            val model: String?,
            val vehicleid: Int?,
            val vin: String?,
            val year: String?
        )
    }
}