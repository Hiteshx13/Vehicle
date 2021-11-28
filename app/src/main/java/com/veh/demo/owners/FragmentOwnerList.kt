package com.veh.demo.owners

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.veh.demo.MainActivity
import com.veh.demo.R
import com.veh.demo.network.owner.OwnerResponse
import com.veh.demo.util.Resource
import kotlinx.android.synthetic.main.fragment_owner_list.*

class FragmentOwnerList : Fragment(R.layout.fragment_owner_list) {

    private lateinit var viewModel: OwnerViewModel
    private lateinit var ownerAdapter: OwnerListAdapter
    private val TAG = "OwnerListFragment"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

        ownerAdapter.setOnItemClickListener {

            val bundle = Bundle().apply {
                putString("user_id", it.userid.toString())
            }

            findNavController().navigate(
                R.id.action_fragmentUserList_to_fragmentMapUserVehicle,
                bundle
            )

//            var navController: NavController = Navigation.findNavController()
//            val action = FragmentOwnerListDirections.actionFragmentUserListToFragmentMapUserVehicle(id)
//            navController.navigate(action)
        }

        setObserver()
    }

    private fun setupRecyclerView() {
        ownerAdapter = OwnerListAdapter()


        rvOwners.apply {
            adapter = ownerAdapter
            layoutManager = LinearLayoutManager(activity)
        }


    }


    private fun setObserver() {
        viewModel.ownerResponse.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { ownerResponse ->
                        val data = ArrayList<OwnerResponse.Data>()
                        ownerResponse.data?.forEach { response ->
                            if (response?.owner != null) { // Removing empty data from response
                                data.add(response)
                            }
                        }
                        ownerAdapter.differ.submitList(data)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.d(TAG, "Error occured: $message ")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }

            }
        })
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }


}