package com.veh.demo.owners

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.veh.demo.network.owner.OwnerResponse
import com.veh.demo.util.AppUtils
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.row_user_list.view.*


class OwnerListAdapter :
    RecyclerView.Adapter<OwnerListAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<OwnerResponse.Data>() {

        override fun areItemsTheSame(
            oldItem: OwnerResponse.Data,
            newItem: OwnerResponse.Data
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: OwnerResponse.Data,
            newItem: OwnerResponse.Data
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                com.veh.demo.R.layout.row_user_list,
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
            ownerData?.owner.let { owner ->
                if (position != 1) {
                    tvOwnerName.text = owner?.getFullName()
                }
                setOnClickListener {
                    onItemClickListener?.let { it(ownerData!!) }
                }

                /** remove extra string from image url**/
                val formattedImageUrl = AppUtils.getFormattedImageUrl(owner?.foto.toString())

                if (formattedImageUrl.isNotEmpty()) {
                    Picasso.get().load(formattedImageUrl)
                        .placeholder(com.veh.demo.R.drawable.ic_user)
                        .resize(300, 300)
                        .transform(CropCircleTransformation())
                        .into(
                            ivProfile
                        )
                    ivProfile
                }
            }
        }
    }

    private var onItemClickListener: ((OwnerResponse.Data) -> Unit)? = null

    fun setOnItemClickListener(listener: (OwnerResponse.Data) -> Unit) {
        onItemClickListener = listener
    }

}