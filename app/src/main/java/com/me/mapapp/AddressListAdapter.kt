package com.me.mapapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AddressViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

class AddressListAdapter(private val addresses: ArrayList<String>) :
    RecyclerView.Adapter<AddressViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AddressViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.address_list_item, parent, false) as TextView
        return AddressViewHolder(textView)
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        holder.textView.text = addresses[position]
    }

    override fun getItemCount() = addresses.size

}