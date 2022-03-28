package com.example.native_code_test

import android.content.ActivityNotFoundException
import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.annotation.NonNull
import co.poynt.os.model.Intents
import co.poynt.os.model.Payment
import co.poynt.os.model.PaymentStatus
import com.google.gson.Gson
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import java.text.NumberFormat
import java.util.*


class MainActivity : FlutterActivity() {
    private val COLLECT_PAYMENT_REQUEST = 13132
    var mResult:  MethodChannel.Result? = null

    private val CHANNEL = "smsAggregationChannel"
    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler { call, result ->
            mResult = result

            if (call.method == "startAggregation") {
                val arg1: String? = call.argument("arg1");
                val arg2: String? = call.argument("arg2");
                Log.d(
                    TAG,
                    "Received arg1("
                            + arg1)
//                Gson().fromJson(arg1, Array<Product>::class.java).asList();

                Gson() .fromJson(arg1, Array<Product>::class.java).asList()

                val topic: List<Product> =  Gson().fromJson(arg1, Array<Product>::class.java).asList();
//                    Gson().fromJson(arg1, Product::class.java)

//
                Log.d(
                    TAG,
                    "Received onPaymentAction("
                            + topic);


//                var jsonarray_info:JSONArray= json_contact.getJSONArray(call.argument("arg1"));
                launchPoyntPayment(10, result);

//                result.success(arg1 + ", " + arg2);
//                startNewActivity(arg1, arg1); // Method to start the SDK
            }
        }
    }

    private fun launchPoyntPayment(amount: Long, result: MethodChannel.Result) {
        val currencyCode = NumberFormat.getCurrencyInstance().currency.currencyCode
        val payment = Payment()
        val referenceId = UUID.randomUUID().toString()
        payment.referenceId = referenceId
        payment.amount = amount
        payment.currency = currencyCode


        // default to cash if the device has no card reader
//        if (!isPoyntTerminal()) {
//            payment.isCashOnly = true
//        }

        // start Payment activity for result

        try {
            val collectPaymentIntent = Intent(Intents.ACTION_COLLECT_PAYMENT)
            collectPaymentIntent.putExtra(Intents.INTENT_EXTRAS_PAYMENT, payment);
            startActivityForResult(collectPaymentIntent, COLLECT_PAYMENT_REQUEST);
        } catch (ex: ActivityNotFoundException) {
            Log.e(TAG, "Poynt Payment Activity not found - did you install PoyntServices?", ex);
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(
            TAG,
            "Received onActivityResult ($requestCode)"
        )
        // Check which request we're responding to
        // Check which request we're responding to
        if (requestCode == COLLECT_PAYMENT_REQUEST) {
            // Make sure the request was successful



            if (resultCode == RESULT_OK) {
                if (data != null) {
                    val payment: Payment? = data.getParcelableExtra(Intents.INTENT_EXTRAS_PAYMENT)
                    mResult!!.success(payment)
                    Log.d(
                        TAG,
                        "Received onPaymentAction from PaymentFragment w/ Status("
                                + payment!!.status + ")"
                    )
                    if (payment.status == PaymentStatus.COMPLETED) {
                        Toast.makeText(this, "Payment Completed", Toast.LENGTH_LONG).show()
                    } else if (payment.status == PaymentStatus.AUTHORIZED) {
                        Toast.makeText(this, "Payment Authorized", Toast.LENGTH_LONG).show()
                    } else if (payment.status == PaymentStatus.CANCELED) {
                        Toast.makeText(this, "Payment Canceled", Toast.LENGTH_LONG).show()
                    } else if (payment.status == PaymentStatus.FAILED) {
                        Toast.makeText(this, "Payment Failed", Toast.LENGTH_LONG).show()
                    } else if (payment.status == PaymentStatus.REFUNDED) {
                        Toast.makeText(this, "Payment Refunded", Toast.LENGTH_LONG).show()
                    } else if (payment.status == PaymentStatus.VOIDED) {
                        Toast.makeText(this, "Payment Voided", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this, "Payment Completed", Toast.LENGTH_LONG).show()
                    }
                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Payment Canceled", Toast.LENGTH_LONG).show()
            }
        }
    }

}
