package com.example.services.views.settings

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.services.R
import com.example.services.databinding.ActivityMyAccountsBinding
import com.example.services.utils.BaseActivity
import com.example.services.viewmodels.MyAccountsViewModel

class MyAccountsActivity : BaseActivity() {
    lateinit var binding: ActivityMyAccountsBinding
    private var accountsViewModel: MyAccountsViewModel? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_my_accounts
    }

    override fun initViews() {
        binding = viewDataBinding as ActivityMyAccountsBinding
        accountsViewModel = ViewModelProviders.of(this).get(MyAccountsViewModel::class.java)
        binding.myaccountsViewModel = accountsViewModel
        binding.toolbarCommon.imgToolbarText.visibility= View.GONE
        accountsViewModel!!.isClick().observe(
            this, Observer<String>(function =
            fun(it: String?) {
                when (it) {


                    "termsCondition" -> {
                        val intent1 = Intent(this, WebViewActivity::class.java)
                        intent1.putExtra("title", getString(R.string.terms_condition))
                        startActivity(intent1)
                    }
                    "privacyPolicy" -> {
                        val intent1 = Intent(this, WebViewActivity::class.java)
                        intent1.putExtra("title", getString(R.string.privacy_policy))
                        startActivity(intent1)
                    }
                    "Faq" -> {
                        val intent1 = Intent(this, FAQListActivity::class.java)
                        intent1.putExtra("title", getString(R.string.faq))
                        startActivity(intent1)
                    }

                    "feedback" -> {
                        showToastSuccess("Coming Soon")
                    }
                    "contact_us" -> {
//                        val intent1 = Intent(this, ContactUsActivity::class.java)
//                        startActivity(intent1)
                        showToastSuccess("Coming Soon")
                    }
                    "rateThisApp" -> {
                        startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://play.google.com/store/apps/"/*details?id=$appPackageName*/)
                            )
                        )
                    }

                    "shareApp" -> {
                        try {
                            val shareIntent = Intent(Intent.ACTION_SEND)
                            shareIntent.type = "text/plain"
                            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Delicio App")
                            var shareMessage =
                                "\nLet me recommend you this application\n\n"
                            shareMessage = shareMessage+" Google.com\n"
                            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                            startActivity(Intent.createChooser(shareIntent, "choose one"))
                        } catch (e: Exception) {
                            //e.toString();
                        }
                    }
                }

            })
        )

    }

}
