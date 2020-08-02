package com.rapsealk.gomsandang

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rapsealk.gomsandang.data.AppItem

/**
 * Created by rapsealk on 2020/08/02.
 */
class AppItemAdapter : RecyclerView.Adapter<AppItemAdapter.AppItemViewHolder>() {

    companion object {
        private val TAG = AppItemAdapter::class.java.simpleName
    }

    private val items: MutableList<AppItem> = mutableListOf()

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppItemViewHolder {
        return AppItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_application, parent, false))
    }

    override fun onBindViewHolder(holder: AppItemViewHolder, position: Int) {
        val applicationItem = items[position]
        holder.applicationIcon.setImageDrawable(applicationItem.icon)
        holder.applicationName.text = applicationItem.name
        holder.applicationPackageName.text = applicationItem.packageName
    }

    fun addItems(newItems: List<AppItem>) {
        items.addAll(newItems)
    }

    inner class AppItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val applicationIcon: ImageView = view.findViewById(R.id.application_icon)
        val applicationName: TextView = view.findViewById(R.id.application_name)
        val applicationPackageName: TextView = view.findViewById(R.id.application_package_name)
    }
}