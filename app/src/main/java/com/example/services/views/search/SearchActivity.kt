package com.example.services.views.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.services.R
import com.example.services.constants.GlobalConstants
import com.example.services.databinding.ActivityLandingBinding
import com.example.services.databinding.ActivitySearchBinding
import com.example.services.model.CommonModel
import com.example.services.sharedpreference.SharedPrefClass
import com.example.services.utils.BaseActivity
import com.example.services.viewmodels.home.HomeViewModel
import com.example.services.viewmodels.search.SearchViewModel
import com.example.services.views.authentication.LoginActivity
import com.example.services.views.home.DashboardViewModel
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : BaseActivity() {

    var activitySearchBinding: ActivitySearchBinding? = null

    var searchViewModel =  SearchViewModel()

    override fun initViews() {

        activitySearchBinding = viewDataBinding as ActivitySearchBinding

        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)



        activitySearchBinding!!.searchBtn.setOnClickListener{


            searchViewModel.search(searchContentEt.text.toString())


            searchViewModel!!.search().observe(this,
                Observer<CommonModel> { searchResponse ->
                    this.stopProgressDialog()

                    if (searchResponse != null) {
                        val message = searchResponse.message

                        if (searchResponse.code == 200) {


                            showToastSuccess("Success")

                        } else {
                            showToastError(message)
                        }
                    }
                })


        }



    }

    override fun getLayoutId(): Int {
        return R.layout.activity_search
    }
}