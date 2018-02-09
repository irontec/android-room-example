package com.irontec.roomexample.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.irontec.roomexample.R
import com.irontec.roomexample.database.entities.Provider
import kotlinx.android.synthetic.main.row_provider.view.*

/**
 * Created by axier on 8/2/18.
 */
class ProviderAdapter(private val mContext: Context, private val mData: MutableList<Provider>) : BaseAdapter() {

    fun addAll(customers: List<Provider>) {
        mData.clear()
        mData.addAll(customers)
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return mData.size
    }

    override fun getItem(position: Int): Any {
        return mData[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val viewHolder: ViewHolder
        if (view != null) {
            viewHolder = view.tag as ViewHolder
        } else {
            view = LayoutInflater.from(mContext)
                    .inflate(R.layout.row_provider, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        }

        val provider = mData[position]
        viewHolder.title.text = String.format("%s", provider.name)

        return view!!
    }

    internal class ViewHolder(view: View) {

        var title: TextView

        init {
            title = view.provider_title
        }
    }

}
