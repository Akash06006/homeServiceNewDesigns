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
import com.example.services.constants.GlobalConstants
import com.example.services.databinding.OrderServiceItemBinding
import com.example.services.model.orders.OrdersListResponse
import com.example.services.utils.BaseActivity
import com.example.services.utils.Utils
import com.example.services.views.chat.ChatActivity
import com.example.services.views.ratingreviews.AddRatingReviewsListActivity

class OrderServicesListAdapter(
    context: BaseActivity,
    addressList: ArrayList<OrdersListResponse.Suborders>?,
    addressList1: ArrayList<OrdersListResponse.Body>,
    var activity: Context
) :
        RecyclerView.Adapter<OrderServicesListAdapter.ViewHolder>() {
    private val mContext: BaseActivity
    private var viewHolder: ViewHolder? = null
    private var addressList: ArrayList<OrdersListResponse.Suborders>?
    private var addressList1: ArrayList<OrdersListResponse.Body>?
    private var orderListActivity = activity

    init {
        this.mContext = context
        this.addressList = addressList
        this.addressList1 = addressList1
    }

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.order_service_item,
                parent,
                false
        ) as OrderServiceItemBinding
        return ViewHolder(binding.root, viewType, binding, mContext, addressList)
    }

    override fun onBindViewHolder(@NonNull holder: ViewHolder, position: Int) {
        viewHolder = holder

        holder.binding!!.tvOrderOn.text = Utils(mContext).getDate(
            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            addressList1?.get(position)!!.createdAt,
            "HH:mm yyyy-MM-dd"
        )
        holder.binding!!.tvServiceOn.text = Utils(mContext).getDate(
            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            addressList1?.get(position)!!.serviceDateTime,
            "HH:mm yyyy-MM-dd"
        )

        /*if (orderListActivity != null && addressList[position].trackStatus!!.equals("1")) {
            holder.binding!!.tvTrack.visibility = View.VISIBLE
        } else {
            holder.binding!!.tvTrack.visibility = View.GONE
        }*/
        holder.binding!!.tvTotal.setText(GlobalConstants.Currency + " " + addressList1!![position].totalOrderPrice)
////0-Pending/Not Confirmed, 1-> Confirmed , 2->Cancelled , 3->Processing,4//cancelled by company, 5->Completed
        /*if (addressList1?.get(position)!!.cancellable.equals("true")) {
            holder.binding!!.tvCancel.setText("Cancel Order"*//*addressList[position].progressStatus*//*)
            holder.binding!!.tvCancel.isEnabled = true
        } else {

            if (addressList1?.get(position)!!.progressStatus.equals("0")) {
                holder.binding!!.tvCancel.setText("Pending"*//*addressList[position].progressStatus*//*)
                holder.binding!!.tvCancel.isEnabled = true
                holder.binding!!.tvCancel.setBackgroundTintList(
                    mContext.getResources().getColorStateList(
                        R.color.colorStatus
                    )
                )

            } else if (addressList1?.get(position)!!.progressStatus.equals("1")) {
                holder.binding!!.tvCancel.setText("Confirmed"*//*addressList[position].progressStatus*//*)
                holder.binding!!.tvCancel.isEnabled = true
                holder.binding!!.tvCancel.setBackgroundTintList(
                    mContext.getResources().getColorStateList(
                        R.color.colorStatus
                    )
                )

            } else if (addressList1?.get(position)!!.progressStatus.equals("2")) {
                holder.binding!!.tvCancel.setText("Cancelled"*//*addressList[position].progressStatus*//*)
                holder.binding!!.tvCancel.isEnabled = false
            } else if (addressList1?.get(position)!!.progressStatus.equals("3")) {
                holder.binding!!.tvCancel.isEnabled = true
                holder.binding!!.tvCancel.setBackgroundTintList(
                    mContext.getResources().getColorStateList(
                        R.color.colorStatus
                    )
                )

                holder.binding!!.tvCancel.setText("Processing"*//*addressList[position].progressStatus*//*)
            } else if (addressList1?.get(position)!!.progressStatus.equals("4")) {
                holder.binding!!.tvCancel.isEnabled = false
                holder.binding!!.tvCancel.setText("Cancelled by company"*//*addressList[position].progressStatus*//*)
            } else if (addressList1?.get(position)!!.progressStatus.equals("5")) {
                holder.binding!!.tvCancel.isEnabled = false
                holder.binding!!.tvCancel.setText("Completed"*//*addressList[position].progressStatus*//*)
                holder.binding!!.tvCancel.setBackgroundTintList(
                    mContext.getResources().getColorStateList(
                        R.color.colorSuccess
                    )
                )

            }
        }*/

        /*holder.binding!!.tvCancel.setOnClickListener {
            if (addressList1?.get(position)!!.cancellable.equals("true")) {
                if (orderListActivity != null)
                    orderListActivity.cancelOrder(position)
            } else {
                orderListActivity!!.completeOrder(position)
            }
        }*/

        holder.binding!!.tvOrderOn.text = Utils(mContext).getDate(
            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            addressList1?.get(position)?.createdAt,
            "HH:mm yyyy-MM-dd"
        )
        holder.binding!!.tvServiceOn.text = Utils(mContext).getDate(
            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            addressList1?.get(position)?.serviceDateTime,
            "HH:mm yyyy-MM-dd"
        )

        /*if (orderListActivity != null && addressList[position].trackStatus!!.equals("1")) {
            holder.binding!!.tvTrack.visibility = View.VISIBLE
        } else {
            holder.binding!!.tvTrack.visibility = View.GONE
        }*/
        holder.binding!!.tvTotal.setText(GlobalConstants.Currency + " " + addressList1?.get(position)!!.totalOrderPrice)
////0-Pending/Not Confirmed, 1-> Confirmed , 2->Cancelled , 3->Processing,4//cancelled by company, 5->Completed
        /*if (addressList1?.get(position)?.cancellable.equals("true")) {
            holder.binding!!.tvCancel.setText("Cancel Order"*//*addressList[position].progressStatus*//*)
            holder.binding!!.tvCancel.isEnabled = true
        } else {

            if (addressList1!![position].progressStatus.equals("0")) {
                holder.binding!!.tvCancel.setText("Pending"*//*addressList[position].progressStatus*//*)
                holder.binding!!.tvCancel.isEnabled = true
                holder.binding!!.tvCancel.setBackgroundTintList(
                    mContext.getResources().getColorStateList(
                        R.color.colorStatus
                    )
                )

            } else if (addressList1!![position].progressStatus.equals("1")) {
                holder.binding!!.tvCancel.setText("Confirmed"*//*addressList[position].progressStatus*//*)
                holder.binding!!.tvCancel.isEnabled = true
                holder.binding!!.tvCancel.setBackgroundTintList(
                    mContext.getResources().getColorStateList(
                        R.color.colorStatus
                    )
                )

            } else if (addressList1!![position].progressStatus.equals("2")) {
                holder.binding!!.tvCancel.setText("Cancelled"*//*addressList[position].progressStatus*//*)
                holder.binding!!.tvCancel.isEnabled = false
            } else if (addressList1!![position].progressStatus.equals("3")) {
                holder.binding!!.tvCancel.isEnabled = true
                holder.binding!!.tvCancel.setBackgroundTintList(
                    mContext.getResources().getColorStateList(
                        R.color.colorStatus
                    )
                )

                holder.binding!!.tvCancel.setText("Processing"*//*addressList[position].progressStatus*//*)
            } else if (addressList1!![position].progressStatus.equals("4")) {
                holder.binding!!.tvCancel.isEnabled = false
                holder.binding!!.tvCancel.setText("Cancelled by company"*//*addressList[position].progressStatus*//*)
            } else if (addressList1!![position].progressStatus.equals("5")) {
                holder.binding!!.tvCancel.isEnabled = false
                holder.binding!!.tvCancel.setText("Completed"*//*addressList[position].progressStatus*//*)
                holder.binding!!.tvCancel.setBackgroundTintList(
                    mContext.getResources().getColorStateList(
                        R.color.colorSuccess
                    )
                )

            }
        }*/

        /*holder.binding!!.tvCancel.setOnClickListener {
            if (addressList1!![position].cancellable.equals("true")) {
                if (orderListActivity != null)
                    orderListActivity.cancelOrder(position)
            } else {
                orderListActivity!!.completeOrder(position)
            }
        }*/


        holder.binding!!.ordersRate.setOnClickListener {
            val intent = Intent(mContext, AddRatingReviewsListActivity::class.java)
            intent.putExtra("orderId", addressList?.get(position)?.serviceId.toString())
            intent.putExtra("from", "list")
            mContext.startActivity(intent)
        }

        holder.binding!!.tvServiceName.text = addressList!![position].service?.name
        holder.binding!!.tvQuantity.setText(mContext.resources.getString(R.string.quantity) + ": " + addressList!![position].quantity)
        // holder.binding!!.tvTime.setText(": " + addressList!![position].Timing)
        // holder.binding!!.tvDate.setText(": " + addressList!![position].created_at)

        Glide.with(mContext)
                .load(addressList!![position].service?.icon)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(20)))
                .placeholder(R.drawable.ic_category)
                .into(holder.binding!!.imgService!!)

    }


    override fun getItemCount(): Int {
        return addressList!!.count()
    }

    inner class ViewHolder//This constructor would switch what to findViewBy according to the type of viewType
    (
            v: View, val viewType: Int, //These are the general elements in the RecyclerView
            val binding: OrderServiceItemBinding?,
            mContext: BaseActivity,
            addressList: ArrayList<OrdersListResponse.Suborders>?
    ) : RecyclerView.ViewHolder(v) {
        /*init {
            binding.linAddress.setOnClickListener {
                mContext.deleteAddress(adapterPosition)
            }
        }*/
    }
}