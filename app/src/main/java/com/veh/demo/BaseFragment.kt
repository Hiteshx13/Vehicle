package com.veh.demo

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

open class BaseFragment: Fragment() {
    lateinit var  mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext=context
    }

}