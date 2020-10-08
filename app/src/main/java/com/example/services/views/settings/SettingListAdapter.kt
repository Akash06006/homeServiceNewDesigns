package com.uniongoods.adapters

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.services.R
import com.example.services.databinding.AddressItemBinding
import com.example.services.databinding.SettingListBinding
import com.example.services.model.address.AddressListResponse
import com.example.services.utils.DialogssInterface
import com.example.services.views.address.AddressListActivity
import com.example.services.views.cart.CheckoutAddressActivity

class SettingListAdapter(
        context: CheckoutAddressActivity,
        addressList: ArrayList<String>,
        var activity: Context
) :
        RecyclerView.Adapter<SettingListAdapter.ViewHolder>() {
    private val mContext: CheckoutAddressActivity
    private var viewHolder: ViewHolder? = null
    private var addressList: ArrayList<String>

    init {
        this.mContext = context
        this.addressList = addressList
    }

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.setting_list,
                parent,
                false
        ) as SettingListBinding
        return ViewHolder(binding.root, viewType, binding, mContext, addressList)
    }

    override fun onBindViewHolder(@NonNull holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return addressList.count()
    }

    inner class ViewHolder
    (
            v: View, val viewType: Int,
            val binding: SettingListBinding?,
            mContext: CheckoutAddressActivity,
            addressList: ArrayList<String>?
    ) : RecyclerView.ViewHolder(v) {

    }


}