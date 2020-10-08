package com.example.services.views.search

import android.content.Intent
import android.text.TextUtils
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
import com.uniongoods.adapters.SearchGridListAdapter
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : BaseActivity() {

    private var searchList: List<SearchResponse.ServiceDatum>? = null

    var activitySearchBinding: ActivitySearchBinding? = null

    var searchViewModel =  SearchViewModel()

    override fun initViews() {

        activitySearchBinding = viewDataBinding as ActivitySearchBinding

        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)



        activitySearchBinding!!.searchBtn.setOnClickListener{


            searchViewModel.search(searchContentEt.text.toString())


            searchViewModel!!.search().observe(this,
                Observer<SearchResponse> { searchResponse ->
                    this.stopProgressDialog()

                    if (searchResponse != null) {
                        val message = searchResponse.message

                        if (searchResponse.code == 200) {

                            searchList = (searchResponse.body!!.responseData!!.serviceData)
                            //showToastSuccess("Success")
                            initRecyclerView()

                        } else {
                            showToastError(message)
                        }
                    }
                })


        }

        activitySearchBinding!!.gvServices2.onItemClickListener =
            AdapterView.OnItemClickListener { parent, v, position, id ->
                searchList?.get(position)?.id?.let { this.callServiceDetail(it) }
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