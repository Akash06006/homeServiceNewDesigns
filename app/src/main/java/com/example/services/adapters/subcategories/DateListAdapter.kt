package com.uniongoods.adapters


import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.text.format.DateFormat.format
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.services.R
import com.example.services.constants.GlobalConstants
import com.example.services.databinding.DateItemBinding
import com.example.services.model.services.DateSlotsResponse
import com.example.services.utils.Utils
import com.example.services.views.cart.CheckoutAddressActivity
import okhttp3.internal.Util.format
import org.slf4j.helpers.MessageFormatter.format
import java.lang.String.format
import java.text.MessageFormat.format

class DateListAdapter(
        context: CheckoutAddressActivity,
        addressList: ArrayList<DateSlotsResponse.Body>,
        var activity: Context
) :
        RecyclerView.Adapter<DateListAdapter.ViewHolder>() {
    private val mContext: CheckoutAddressActivity
    private var viewHolder: ViewHolder? = null
    private var dateList: ArrayList<DateSlotsResponse.Body>
    private var day: ArrayList<DateSlotsResponse.Body>?=null
    private var month: ArrayList<DateSlotsResponse.Body>?=null
    private var year: ArrayList<DateSlotsResponse.Body>?=null

    init {
        this.mContext = context
        this.dateList = addressList
        day= addressList
        month=addressList
        year=addressList

    }

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.date_item,
                parent,
                false
        ) as DateItemBinding
        return ViewHolder(binding.root, viewType, binding, mContext, dateList)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(@NonNull holder: ViewHolder, position: Int) {
        viewHolder = holder


        dateList!!.get(position).date = Utils(mContext).getDateLocal(
            "EEE MMM dd HH:mm:ss zzzz yyyy",
            mContext.getDaysAgo(position).toString(),
            "dd-MMM-yyyy")
        val day = Utils(mContext).getDateLocal("EEE MMM dd HH:mm:ss zzzz yyyy", mContext.getDaysAgo(position).toString(), "dd")
        val month = Utils(mContext).getDateLocal("EEE MMM dd HH:mm:ss zzzz yyyy", mContext.getDaysAgo(position).toString(), "MMM")
        val year = Utils(mContext).getDateLocal("EEE MMM dd HH:mm:ss zzzz yyyy", mContext.getDaysAgo(position).toString(), "yyyy")

//        holder.binding!!.tvCatName.text =  "05\nSep\n2020"
//        holder.binding!!.tvCatName.text =  dateList!!.get(position).date
        holder.binding!!.tvCatName.text = day+"\n"+month+"\n"+year


        if (dateList[position].selected.equals("true")) {
            // holder.binding.topLay.setBackgroundResource(R.drawable.btn_bg_shape_colored)
            // holder.binding.tvCatName.setBackgroundColor(mContext.resources.getColor(R.color.btnBackground))
            holder.binding.tvCatName.background=mContext.resources.getDrawable(R.drawable.shape_orange_full_round)
            holder.binding.tvCatName.setTextColor(mContext.resources.getColor(R.color.white))
            /*mContext.getResources().getColorStateList(R.color.colorOrange)*/

         } else {
            // holder.binding.topLay.setBackgroundResource(R.drawable.shape_round_corner)
            holder.binding.tvCatName.background=mContext.resources.getDrawable(R.drawable.full_round_checkout_layout)
            holder.binding.tvCatName.setTextColor(mContext.resources.getColor(R.color.dateColor))
          //  holder.binding.tvCatName.setBackgroundTintList(mContext.getResources().getColorStateList(R.color.colorWhite))
        }


      /*  if (dateList[position].selected.equals("true")) {
            // holder.binding.topLay.setBackgroundResource(R.drawable.btn_bg_shape_colored)
            // holder.binding.tvCatName.setBackgroundColor(mContext.resources.getColor(R.color.btnBackground))
            holder.binding.tvCatName.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(GlobalConstants.COLOR_CODE))*//*mContext.getResources().getColorStateList(R.color.colorOrange)*//*)


            holder.binding.tvCatName.setTextColor(mContext.resources.getColor(R.color.colorWhite))
        } else {
            // holder.binding.topLay.setBackgroundResource(R.drawable.shape_round_corner)
            holder.binding.tvCatName.setTextColor(mContext.resources.getColor(R.color.colorBlack))
            holder.binding.tvCatName.setBackgroundColor(mContext.resources.getColor(R.color.btnBackgroundWhite))
            holder.binding.tvCatName.setBackgroundTintList(mContext.getResources().getColorStateList(R.color.colorWhite))

        }*/

        //old already comment
        /*  if(dateList[position].status.equals("Open")){
              holder.binding.tvCatName.isEnabled=true
              //  holder.binding.mainLayout.setBackgroundColor(mContext.resources.getColor(R.color.colorSuccess))
          }else{
              holder.binding.tvCatName.isEnabled=false
              holder.binding.tvCatName.setBackgroundColor(mContext.resources.getColor(R.color.colorGrey))
          }*/
        holder.binding!!.tvCatName.setOnClickListener {
            mContext.selectDatelot(position)
        }
    }

    fun dataFormate(position: Int,formate: String) {
        dateList[position].date=Utils(mContext).getDateLocal(
            "EEE MMM dd HH:mm:ss zzzz yyyy",
            mContext.getDaysAgo(position).toString(),
           formate)
    }

    override fun getItemCount(): Int {
        return dateList.count()
    }

    inner class ViewHolder//This constructor would switch what to findViewBy according to the type of viewType
    (
            v: View, val viewType: Int, //These are the general elements in the RecyclerView
            val binding: DateItemBinding?,
            mContext: CheckoutAddressActivity,
            addressList: ArrayList<DateSlotsResponse.Body>?
    ) : RecyclerView.ViewHolder(v) {
        /*init {
            binding.linAddress.setOnClickListener {
                mContext.deleteAddress(adapterPosition)
            }
        }*/
    }
}