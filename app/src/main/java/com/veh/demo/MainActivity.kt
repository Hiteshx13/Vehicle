package com.veh.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.veh.demo.R
import com.veh.demo.owners.OwnerRepository
import com.veh.demo.owners.OwnerViewModel
import com.veh.demo.owners.OwnerViewModelProviderFactory

class MainActivity : AppCompatActivity() {
     lateinit var viewModel: OwnerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ownerRepository = OwnerRepository()
        val viewModelProviderFactory = OwnerViewModelProviderFactory(ownerRepository)
        viewModel =
            ViewModelProvider(this, viewModelProviderFactory)[OwnerViewModel::class.java]


    }
}