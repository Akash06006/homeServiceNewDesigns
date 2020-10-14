package com.uniongoods.adapters

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.services.R
import com.example.services.adapters.home.AddImagesActivity
import com.example.services.databinding.ImageItemBinding

class ImagesListAdapter(
    addimageContext: AddImagesActivity?,
    addressList: ArrayList<String>,
    var activity: Context
) :
    RecyclerView.Adapter<ImagesListAdapter.ViewHolder>() {
    private val addImageContext: AddImagesActivity?
    private var viewHolder: ViewHolder? = null
    private var addressList: ArrayList<String>

    init {
        this.addImageContext = addimageContext
        this.addressList = addressList
    }

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.image_item,
            parent,
            false
        ) as ImageItemBinding
        return ViewHolder(binding.root, viewType, binding, addressList)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(@NonNull holder: ViewHolder, position: Int) {
        viewHolder = holder
        if (addImageContext != null) {
            Glide.with(addImageContext)
                .load(addressList[position])
                //.apply(RequestOptions.bitmapTransform(RoundedCorners(20)))
                .placeholder(R.drawable.ic_category)
                .into(holder.binding!!.imgProduct)

            holder.binding!!.imgCross.setOnClickListener {
                addImageContext.removeImage(position/*, holder.binding!!.imgCart.getText().toString()*/)
            }
        }

    }

    override fun getItemCount(): Int {

        return addressList.count()

    }

    inner class ViewHolder//This constructor would switch what to findViewBy according to the type of viewType
        (
        v: View, val viewType: Int, //These are the general elements in the RecyclerView
        val binding: ImageItemBinding?,
        addressList: ArrayList<String>
    ) : RecyclerView.ViewHolder(v) {
        /*init {
            binding.linAddress.setOnClickListener {
                mContext.deleteAddress(adapterPosition)
            }
        }*/
    }
}