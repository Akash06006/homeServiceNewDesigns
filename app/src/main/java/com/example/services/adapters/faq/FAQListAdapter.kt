package com.uniongoods.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.services.R
import com.example.services.adapters.faq.FAQDetailActivity
import com.example.services.databinding.FaqItemBinding
import com.example.services.databinding.NotificationItemBinding
import com.example.services.model.faq.FAQListResponse
import com.example.services.model.notificaitons.NotificationsListResponse
import com.example.services.views.notifications.NotificationsListActivity
import com.example.services.views.settings.FAQListActivity

class FAQListAdapter(
    context: FAQListActivity,
    addressList: ArrayList<FAQListResponse.Data>,
    var activity: Context
) :
    RecyclerView.Adapter<FAQListAdapter.ViewHolder>() {
    private val mContext: FAQListActivity
    private var viewHolder: ViewHolder? = null
    private var jobsList: ArrayList<FAQListResponse.Data>

    init {
        this.mContext = context
        this.jobsList = addressList
    }

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.faq_item,
            parent,
            false
        ) as FaqItemBinding
        return ViewHolder(binding.root, viewType, binding, mContext, jobsList)
    }

    override fun onBindViewHolder(@NonNull holder: ViewHolder, position: Int) {
        viewHolder = holder
        holder.binding!!.tvNotificationTitle.setText("Q." + (position + 1) + " " + jobsList[position].question)
       // holder.binding!!.tvNotificationDescription.text = jobsList[position].answer

        holder.binding.tvNotificationTitle.setOnClickListener {
            // mContext.showDescription(position)
            val intent = Intent(mContext, FAQDetailActivity::class.java)
            intent.putExtra("question", jobsList[position].question)
            intent.putExtra("ans", jobsList[position].answer)
            mContext.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return jobsList.count()
    }

    inner class ViewHolder//This constructor would switch what to findViewBy according to the type of viewType
        (
        v: View, val viewType: Int, //These are the general elements in the RecyclerView
        val binding: FaqItemBinding?,
        mContext: FAQListActivity,
        addressList: ArrayList<FAQListResponse.Data>?
    ) : RecyclerView.ViewHolder(v) {
        /*init {
            binding.linAddress.setOnClickListener {
                mContext.deleteAddress(adapterPosition)
            }
        }*/
    }
}