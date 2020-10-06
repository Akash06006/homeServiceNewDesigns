package com.uniongoods.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.services.R
import com.example.services.databinding.CategoryItemBinding
import com.example.services.databinding.TrandingItemBinding
import com.example.services.viewmodels.home.Subcat
import com.example.services.views.home.HomeFragment
import com.example.services.views.subcategories.ServicesListActivity
import kotlin.collections.ArrayList


class TrandingAdapter(
    context: HomeFragment,
    addressList: ArrayList<com.example.services.viewmodels.home.Trending>,
    var fragment: HomeFragment
) :
    RecyclerView.Adapter<TrandingAdapter.ViewHolder>() {
    private val mContext: HomeFragment
    private var viewHolder: ViewHolder? = null
    private var categoriesList: ArrayList<com.example.services.viewmodels.home.Trending>

    init {
        this.mContext = context
        this.categoriesList = addressList
    }

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.tranding_item,
            parent,
            false
        ) as TrandingItemBinding
        return ViewHolder(binding.root, viewType, binding, mContext, categoriesList)
    }

    override fun onBindViewHolder(@NonNull holder: ViewHolder, position: Int) {
        viewHolder = holder
        holder.binding!!.itemName.setText(categoriesList[position].name)

        Glide.with(mContext)
            .load(categoriesList[position].icon)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(20)))
            .placeholder(R.drawable.ic_category)
            .into(holder.binding!!.imgItem)


        holder.binding.imgItem.setOnClickListener {

            val intent = Intent(fragment.activity, ServicesListActivity::class.java)
            intent.putExtra("catId", categoriesList[position].id)
            mContext.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
        return categoriesList.count()
    }

    inner class ViewHolder//This constructor would switch what to findViewBy according to the type of viewType
        (
        v: View, val viewType: Int, //These are the general elements in the RecyclerView
        val binding: TrandingItemBinding?,
        mContext: HomeFragment,
        addressList: ArrayList<com.example.services.viewmodels.home.Trending>
    ) : RecyclerView.ViewHolder(v) {
        /*init {
            binding.linAddress.setOnClickListener {
                mContext.deleteAddress(adapterPosition)
            }
        }*/
    }
}