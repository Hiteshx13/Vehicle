package com.veh.demo.vehicle

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.veh.demo.MainActivity
import com.veh.demo.R
import com.veh.demo.owners.OwnerViewModel
import com.veh.demo.util.AppUtils
import com.veh.demo.util.SettingsDialogClickListener

class FragmentMapUserVehicle : Fragment(R.layout.fragment_map_user_vehicle), OnMapReadyCallback {

    private lateinit var viewModel: OwnerViewModel
    private var isLocationAllowed = false
    private val args: FragmentMapUserVehicleArgs by navArgs()
    private lateinit var userid: String
    private var googleMap: GoogleMap? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userid = args.userId
        viewModel = (activity as MainActivity).viewModel
        setupMap()
        requestLocationPermission()
    }


    private fun setupMap() {
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }


    private fun requestLocationPermission() {
        val requestPermisisionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                isLocationAllowed = isGranted
                googleMap?.isMyLocationEnabled = isGranted
                if (!isGranted) {
                    showSettingsDialog()
                }
            }

        when {
            ContextCompat.checkSelfPermission(activity!!, Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED -> {
                googleMap?.isMyLocationEnabled = true
            }
            ActivityCompat.shouldShowRequestPermissionRationale(
                activity!!,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) -> {
                // Message
            }
            else -> {
                requestPermisisionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }


    }


    /**
     * show dialog to grant location  permission from application settings
     */
    private fun showSettingsDialog() {
        AppUtils.showDialog(activity!!, object : SettingsDialogClickListener {
            override fun onSettingClicked() {
                AppUtils.openAppSettings(activity!!)
            }

//            override fun onCancelClicked() {
//
//            }
        })
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
    }

}