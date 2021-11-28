package com.veh.demo.owners

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.veh.demo.R
import com.veh.demo.network.owner.OwnerResponse
import kotlinx.android.synthetic.main.row_user_list.view.*

class OwnerListAdapter :
    RecyclerView.Adapter<OwnerListAdapter.ViewHolder>() {
    inner class ViewHolder( itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<OwnerResponse.Data>() {

        override fun areItemsTheSame(oldItem: OwnerResponse.Data, newItem: OwnerResponse.Data): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: OwnerResponse.Data, newItem: OwnerResponse.Data): Boolean {
            return oldItem == newItem
        }
    }

     val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_user_list,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ownerData: OwnerResponse.Data? = differ.currentList[position]
        holder.itemView.apply {
            ownerData?.let {
                tvOwnerName.text = ownerData.owner?.getFullName()
                setOnClickListener {
                    onItemClickListener?.let { it(ownerData) }
                }
            }
        }
    }

    private var onItemClickListener: ((OwnerResponse.Data) -> Unit)? = null

     fun setOnItemClickListener(listener: (OwnerResponse.Data) -> Unit) {
        onItemClickListener = listener
    }

}