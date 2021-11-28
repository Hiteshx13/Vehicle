package com.veh.demo.network.owner

import androidx.annotation.Keep

@Keep
data class OwnerResponse(
    val `data`: List<Data?>?
) {
    @Keep
    data class Data(
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