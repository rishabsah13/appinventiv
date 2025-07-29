package com.nativebarcodescanner

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView

class ScannerActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val textView = TextView(this)
        textView.text = "Tap to return barcode: 987654321"
        textView.textSize = 24f
        textView.setPadding(50, 200, 50, 200)

        textView.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("barcode", "987654321")
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

        setContentView(textView)
    }
}
