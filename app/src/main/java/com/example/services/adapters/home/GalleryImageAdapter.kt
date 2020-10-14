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
import com.example.services.model.services.ServicesDetailResponse
import com.example.services.viewmodels.home.Subcat
import com.example.services.views.home.HomeFragment
import com.example.services.views.subcategories.ServiceDetailActivity
import com.example.services.views.subcategories.ServicesListActivity
import kotlin.collections.ArrayList


class GalleryImageAdapter(
    context: ServiceDetailActivity,
    addressList: ArrayList<ServicesDetailResponse.Gallery>) :
    RecyclerView.Adapter<GalleryImageAdapter.ViewHolder>() {
    private val mContext: ServiceDetailActivity
    private var viewHolder: ViewHolder? = null
    private var categoriesList: ArrayList<ServicesDetailResponse.Gallery>

    init {
        this.mContext = context
        this.categoriesList = addressList
    }

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.category_item,
            parent,
            false
        ) as CategoryItemBinding
        return ViewHolder(binding.root, viewType, binding, mContext, categoriesList)
    }

    override fun onBindViewHolder(@NonNull holder: ViewHolder, position: Int) {
        viewHolder = holder
        holder.binding!!.catHeader.setText(categoriesList[position].title)

        Glide.with(mContext)
            .load(categoriesList[position].mediaHttpUrl)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(20)))
            .placeholder(R.drawable.ic_category)
            .into(holder.binding.catImg)


        holder.binding.catImg.setOnClickListener {

            val intent = Intent(mContext, ServicesListActivity::class.java)
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
        val binding: CategoryItemBinding?,
        mContext: ServiceDetailActivity,
        addressList: ArrayList<ServicesDetailResponse.Gallery>
    ) : RecyclerView.ViewHolder(v) {
        /*init {
            binding.linAddress.setOnClickListener {
                mContext.deleteAddress(adapterPosition)
            }
        }*/
    }
}