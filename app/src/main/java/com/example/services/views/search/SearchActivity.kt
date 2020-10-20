package com.example.services.views.search

import android.content.Intent
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.services.R
import com.example.services.constants.GlobalConstants
import com.example.services.databinding.ActivitySearchBinding
import com.example.services.model.search.SearchResponse
import com.example.services.utils.BaseActivity
import com.example.services.viewmodels.search.SearchViewModel
import com.example.services.views.subcategories.ServiceDetailActivity
import com.example.services.views.vendor.VendorsListActivity
import com.google.gson.JsonObject
import com.uniongoods.adapters.SearchGridListAdapter
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : BaseActivity() {

    private var searchList: List<SearchResponse.ServiceDatum>? = null

    var activitySearchBinding: ActivitySearchBinding? = null

    var searchViewModel =  SearchViewModel()

    override fun initViews() {

        activitySearchBinding = viewDataBinding as ActivitySearchBinding
        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        activitySearchBinding!!.icMenu.setOnClickListener {
            finish()
        }
        activitySearchBinding!!.searchBtn.setOnClickListener{
            startProgressDialog()
            var jsonObject: JsonObject? = JsonObject()
            jsonObject!!.addProperty("search", searchContentEt.text.toString())
            searchViewModel.search(jsonObject)
            searchViewModel!!.search().observe(this,
                Observer<SearchResponse> { searchResponse ->
                    stopProgressDialog()

                    if (searchResponse != null) {
                        val message = searchResponse.message

                        if (searchResponse.code == 200) {

                            searchList = (searchResponse.body!!.responseData!!.serviceData)
                            //showToastSuccess("Success")

                            if (searchList!!.size > 0) {
                                activitySearchBinding!!.tvNoRecord.visibility = View.GONE
                                activitySearchBinding!!.gvServices2.visibility = View.VISIBLE
                                activitySearchBinding!!.tvNoRecord1.visibility = View.GONE
                                initRecyclerView()
                            } else {
                                activitySearchBinding!!.tvNoRecord.visibility = View.GONE
                                activitySearchBinding!!.gvServices2.visibility = View.GONE
                                activitySearchBinding!!.tvNoRecord1.visibility = View.VISIBLE
                            }

                        } else {
                            showToastError(message)
                            activitySearchBinding!!.tvNoRecord.visibility = View.GONE
                            activitySearchBinding!!.gvServices2.visibility = View.GONE
                            activitySearchBinding!!.tvNoRecord1.visibility = View.VISIBLE
                        }
                    }
                })

            activitySearchBinding!!.gvServices2.onItemClickListener =
                AdapterView.OnItemClickListener { parent, v, position, id ->
                    val intent = Intent(this, VendorsListActivity::class.java)
                    intent.putExtra("catId", searchList?.get(position)?.id)
                    intent.putExtra("name", searchList?.get(position)?.name)
                    GlobalConstants.CATEGORY_SELECTED = searchList?.get(position)?.id.toString()
                    GlobalConstants.CATEGORY_SELECTED_NAME = searchList?.get(position)?.name.toString()
                    startActivity(intent)
                }

        }


    }

    private fun initRecyclerView() {
        val adapter = SearchGridListAdapter(
            this,
            searchList,
            this
        )
        activitySearchBinding!!.gvServices2.adapter = adapter
    }

    fun callServiceDetail(serviceId: String) {
        val intent = Intent(this, ServiceDetailActivity::class.java)
        intent.putExtra("serviceId", serviceId)
        startActivity(intent)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_search
    }
}