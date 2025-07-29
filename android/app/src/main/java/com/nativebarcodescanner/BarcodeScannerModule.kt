package com.nativebarcodescanner

import android.app.Activity
import android.content.Intent
import com.facebook.react.bridge.*

class BarcodeScannerModule(private val reactContext: ReactApplicationContext) :
    ReactContextBaseJavaModule(reactContext), ActivityEventListener {

    private var successCallback: Callback? = null
    private val BARCODE_REQUEST = 1001

    init {
        reactContext.addActivityEventListener(this)
    }

    override fun getName(): String = "BarcodeScanner"

    @ReactMethod
    fun openScanner(callback: Callback) {
        val currentActivity = currentActivity
        if (currentActivity != null) {
            successCallback = callback
            val intent = Intent(currentActivity, ScannerActivity::class.java)
            currentActivity.startActivityForResult(intent, BARCODE_REQUEST)
        } else {
            callback.invoke("No current activity available")
        }
    }

    override fun onActivityResult(activity: Activity, requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == BARCODE_REQUEST && successCallback != null) {
            if (resultCode == Activity.RESULT_OK) {
                val result = data?.getStringExtra("barcode") ?: "No data"
                successCallback?.invoke(result)
            } else {
                successCallback?.invoke("Scan cancelled")
            }
        }
    }

    override fun onNewIntent(intent: Intent) {}
}
