package com.uniongoods.adapters

import android.content.Context
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
import com.example.services.common.UtilsFunctions
import com.example.services.constants.GlobalConstants
import com.example.services.databinding.CartItemBinding
import com.example.services.model.cart.CartListResponse
import com.example.services.views.cart.CartListActivity

class CartListAdapter(
        context: CartListActivity,
        addressList: ArrayList<CartListResponse.Data>,
        var activity: Context
) :
        RecyclerView.Adapter<CartListAdapter.ViewHolder>() {
    private val mContext: CartListActivity
    private var viewHolder: ViewHolder? = null
    private var addressList: ArrayList<CartListResponse.Data>
    var cartId = "false"
    var quantityCount = 0
    var priceAmount = "false"
    var price = 0


    init {
        this.mContext = context
        this.addressList = addressList
    }

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.cart_item,
            parent,
            false
        ) as CartItemBinding
        return ViewHolder(binding.root, viewType, binding, mContext, addressList)
    }

    override fun onBindViewHolder(@NonNull holder: ViewHolder, position: Int) {
        viewHolder = holder
        holder.binding!!.tvCatName.text = addressList[position].service?.name
        //holder.binding!!.tvQuantity.setText(mContext.resources.getString(R.string.quantity) + ": " + addressList[position].quantity)
        holder.binding!!.tvOfferPrice.setText(
            GlobalConstants.Currency + " " + addressList[position].price.toString()
        )
        //holder.binding!!.rBar.setRating(addressList[position].rating?.toFloat())
        Glide.with(mContext)
            .load(addressList[position].service?.icon)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(20)))
            .placeholder(R.drawable.ic_category)
            .into(holder.binding.imgCart)
        //img_cat
        holder.binding!!.imgMinusNew.setOnClickListener {
            //intent.putExtra("orderId", cartList[position].id.toString())

            mContext.addRemoveToCart(position/*, holder.binding!!.imgCart.getText().toString()*/)

        }
        if (addressList[position].service?.timeSlotStatus.equals("false")) {
            holder.binding.tvNoSlotAvailable.visibility = View.VISIBLE
            holder.binding.serviceItem.setBackgroundColor(mContext.resources.getColor(R.color.colorGrey1))
        } else {
            holder.binding.tvNoSlotAvailable.visibility = View.GONE
        }

    }

    override fun getItemCount(): Int {
        return addressList.count()
    }

    inner class ViewHolder//This constructor would switch what to findViewBy according to the type of viewType
        (
        v: View, val viewType: Int, //These are the general elements in the RecyclerView
        val binding: CartItemBinding?,
        mContext: CartListActivity,
        addressList: ArrayList<CartListResponse.Data>?
    ) : RecyclerView.ViewHolder(v) {

        init {

            binding!!.imgMinusNew.setOnClickListener {

                if (quantityCount > 0) {
                    quantityCount--
                    price = quantityCount * priceAmount.toInt()
                    binding.tvOfferPrice.setText(GlobalConstants.Currency + " " + price.toString())
                    //callGetTimeSlotsApi()
                }
                if (quantityCount == 0) {
                    binding.tvQuantityNew.setText("0")

                }
                binding.tvQuantityNew.setText(quantityCount.toString())
            }

            binding!!.imgPlusNew.setOnClickListener {
                if (quantityCount <= 5) {
                    quantityCount++
                    // serviceDetailBinding.btnSubmit.isEnabled = false
                    binding.tvQuantityNew.setText(quantityCount.toString())
                    //   serviceDetailBinding.btnSubmit.visibility = View.VISIBLE
                    //callGetTimeSlotsApi()
                    price = quantityCount * priceAmount.toInt()
                    binding.tvOfferPrice.setText(GlobalConstants.Currency + " " + price.toString())
                }


            }

        }
    }
}
