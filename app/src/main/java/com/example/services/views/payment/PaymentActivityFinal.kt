package com.example.services.views.payment

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.services.R
import com.example.services.application.MyApplication
import com.example.services.constants.GlobalConstants
import com.example.services.sharedpreference.SharedPrefClass
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONObject
import java.lang.Exception

class PaymentActivityFinal: Activity(), PaymentResultListener {

    val TAG:String = PaymentActivityFinal::class.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_final)
        /*
        * To ensure faster loading of the Checkout form,
        * call this method as early as possible in your checkout flow
        * */
        Checkout.preload(applicationContext)

        var button: Button = findViewById(R.id.btn_pay)
        button.setOnClickListener {
            startPayment()
        }
    }

    private fun startPayment() {
        /*
        *  You need to pass current activity in order to let Razorpay create CheckoutActivity
        * */
        val activity:Activity = this
        val co = Checkout()

        try {
            val options = JSONObject()
            options.put("name","Razorpay Corp")
            options.put("description","Demoing Charges")
            //You can omit the image option to fetch the image from dashboard
            options.put("image","https://s3.amazonaws.com/rzp-mobile/images/rzp.png")
            options.put("currency","INR")
            options.put("amount","100")

            val prefill = JSONObject()
            prefill.put("email","test@razorpay.com")
            prefill.put("contact", SharedPrefClass().getPrefValue(
                MyApplication.instance,
                getString(R.string.key_phone)
            ) as String)

            options.put("prefill",prefill)
            co.open(activity,options)
        }catch (e: Exception){
            Toast.makeText(activity,"Error in payment: "+ e.message,Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }

    override fun onPaymentError(errorCode: Int, response: String?) {
        try{
            Toast.makeText(this,"Payment failed $errorCode \n $response",Toast.LENGTH_LONG).show()
        }catch (e: Exception){
            Log.e(TAG,"Exception in onPaymentSuccess", e)
        }
    }

    override fun onPaymentSuccess(razorpayPaymentId: String?) {
        try{
            Toast.makeText(this,"Payment Successful $razorpayPaymentId",Toast.LENGTH_LONG).show()
        }catch (e: Exception){
            Log.e(TAG,"Exception in onPaymentSuccess", e)
        }
    }


}