package com.uniongoods.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.services.R
import com.example.services.model.search.SearchResponse
import com.example.services.views.search.SearchActivity
import kotlin.collections.ArrayList

class SearchGridListAdapter(
    context: SearchActivity,
    addressList: List<SearchResponse.ServiceDatum>?,
    var activity: Context
) :
        ArrayAdapter<SearchGridListAdapter.ItemHolder>(activity, R.layout.category_item) {
    private val mContext: SearchActivity
    private var viewHolder: ItemHolder? = null
    private var categoriesList: List<SearchResponse.ServiceDatum>?

    init {
        this.mContext = context
        this.categoriesList = addressList
    }

    override fun getCount(): Int {
        return if (this.categoriesList != null) this.categoriesList!!.size else 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView

        val holder: ItemHolder
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.landing_home_category_item, null)
            holder = ItemHolder()
            holder.name = convertView!!.findViewById(R.id.cat_header)
            holder.icon = convertView.findViewById(R.id.cat_img)
            holder.topLayout = convertView.findViewById(R.id.topLayout)
            convertView.tag = holder
        } else {
            holder = convertView.tag as ItemHolder
        }

       // holder.topLayout!!.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#" + categoriesList[position].colorCode.trim()))/*mContext.getResources().getColorStateList(R.color.colorOrange)*/)
        holder.name!!.text = categoriesList?.get(position)!!.name

        Glide.with(mContext)
                .load(categoriesList?.get(position)!!.icon)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(20)))
                .placeholder(R.drawable.ic_category)
                .into(holder.icon!!)

        return convertView
    }

    class ItemHolder {
        var name: TextView? = null
        var icon: ImageView? = null
        var topLayout: LinearLayout? = null
    }

}