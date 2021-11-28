package com.veh.demo.owners

import androidx.lifecycle.MutableLiveData
import com.veh.demo.network.RetrofitInstance
import com.veh.demo.network.owner.OwnerResponse

class OwnerRepository {
    private val ownerLiveData = MutableLiveData<OwnerResponse>()
    suspend fun getOwnerList(op: String) = RetrofitInstance.api.getOwnerList(op)

}