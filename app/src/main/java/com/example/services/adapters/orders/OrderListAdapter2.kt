package com.uniongoods.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.services.R
import com.example.services.databinding.OrderItem2Binding
import com.example.services.model.orders.OrdersListResponse
import com.example.services.utils.BaseActivity
import com.example.services.views.orders.OrdersListActivity
import java.util.*
import kotlin.collections.ArrayList

class OrderListAdapter2(
    context: BaseActivity,
    addressList: ArrayList<OrdersListResponse.Body>,
    activity: OrdersListActivity?
) :
    RecyclerView.Adapter<OrderListAdapter2.ViewHolder>() {
    private val mContext: BaseActivity
    private val orderListActivity: OrdersListActivity?
    private var viewHolder: ViewHolder? = null
    private var addressList: ArrayList<OrdersListResponse.Body>

    init {
        this.mContext = context
        this.addressList = addressList
        orderListActivity = activity
    }

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.order_item2,
            parent,
            false
        ) as OrderItem2Binding
        return ViewHolder(binding.root, viewType, binding, mContext, addressList)
    }

    override fun onBindViewHolder(@NonNull holder: ViewHolder, position: Int) {
        viewHolder = holder


        /*holder.binding!!.tvTrack.setOnClickListener {
            val mJsonObjectStartJob = JsonObject()
            mJsonObjectStartJob.addProperty(
                "orderId", addressList[position].id
            )
            mJsonObjectStartJob.addProperty(
                "lat", addressList[position].address?.latitude
            )
            mJsonObjectStartJob.addProperty(
                "lng", addressList[position].address?.longitude
            )
            mJsonObjectStartJob.addProperty(
                "destLat", addressList[position].companyAddress?.lat
            )
            mJsonObjectStartJob.addProperty(
                "destLong", addressList[position].companyAddress?.long
            )
            val intent = Intent(mContext, DriverTrackingActivity::class.java)
            intent.putExtra("data", mJsonObjectStartJob.toString())
            mContext.startActivity(intent)

        }*/


        /* if (orderListActivity == null) {
             holder.binding!!.tvCancel.visibility = View.GONE
         } else {
             holder.binding!!.tvCancel.visibility = View.VISIBLE
         }*/

        holder.binding


        val orderListAdapter =
            OrderServicesListAdapter2(mContext, addressList[position].suborders, addressList, mContext)
        val linearLayoutManager = LinearLayoutManager(mContext)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        holder.binding!!.rvOrderService.layoutManager = linearLayoutManager
        holder.binding!!.rvOrderService.adapter = orderListAdapter
        holder.binding!!.rvOrderService.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

            }
        })

    }

    fun getDaysAgo(daysAgo: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, daysAgo)

        return calendar.time
    }

    override fun getItemCount(): Int {
        return addressList.count()
    }

    inner class ViewHolder//This constructor would switch what to findViewBy according to the type of viewType
        (
        v: View, val viewType: Int, //These are the general elements in the RecyclerView
        val binding: OrderItem2Binding,
        mContext: BaseActivity,
        addressList: ArrayList<OrdersListResponse.Body>?
    ) : RecyclerView.ViewHolder(v) {
        /*init {
            binding.linAddress.setOnClickListener {
                mContext.deleteAddress(adapterPosition)
            }
        }*/
    }
}